package gpw.persistencia.pedido;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryPedido;
import gpw.db.generic.GenSqlExecType;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.dominio.util.Origen;
import gpw.dominio.util.Sinc;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.pedido.IPersPedido;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.persona.PersistenciaPersona;
import gpw.types.Fecha;

public class PersistenciaPedido extends Conector implements IPersPedido, CnstQryPedido {

	private static final Logger logger = Logger.getLogger(PersistenciaPedido.class);
	private ResultSet rs;
	
	
	@Override
	public List<Pedido> obtenerListaPedidoNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException {
		List<Pedido> listaPedido = new ArrayList<>();
		PersistenciaPersona pp = new PersistenciaPersona();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_PEDIDO_NO_SINC);
			genType.setParam(fechaDesde);
			genType.setParam(fechaHasta);
			rs = (ResultSet) runGeneric(conn, genType);
			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setPersona(pp.obtenerPersGenerico(conn, rs.getLong("id_persona")));
				pedido.setFechaHora(new Fecha(rs.getTimestamp("fecha_hora")));
				char[] estadoChar = new char[1];
				rs.getCharacterStream("estado").read(estadoChar);
				EstadoPedido estado = EstadoPedido.getEstadoPedidoPorChar(estadoChar[0]);
				pedido.setEstado(estado);
				Date fechaProg = rs.getDate("fecha_prog");
				if(!rs.wasNull()) {
					pedido.setFechaProg(new Fecha(fechaProg));
				}
				Time horaProg = rs.getTime("hora_prog");
				if(!rs.wasNull()) {
					pedido.setHoraProg(new Fecha(horaProg));
				}
				char[] origenChar = new char[1];
				rs.getCharacterStream("origen").read(origenChar);
				Origen origen = Origen.getOrigenPorChar(origenChar[0]);
				pedido.setOrigen(origen);
				pedido.setTotal(rs.getDouble("total"));
				pedido.setTotal(rs.getDouble("total"));
				char[] sincChar = new char[1];
				rs.getCharacterStream("sinc").read(sincChar);
				Sinc sinc = Sinc.getSincPorChar(sincChar[0]);
				pedido.setSinc(sinc);
				pedido.setUltAct(new Fecha(rs.getTimestamp("ult_act")));
				//
				listaPedido.add(pedido);
			}
		} catch (ConectorException | SQLException | IOException e) {
			logger.fatal("Excepcion al obtenerListaPedidoNoSinc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaPedido;
	}
	
	@Override
	public Integer guardarPedido(Connection conn, Pedido pedido) throws PersistenciaException {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_PEDIDO);
		Long idPersona = null;
		if(pedido.getPersona() instanceof PersonaFisica) {
			PersonaFisica pf = (PersonaFisica) pedido.getPersona();
			idPersona = pf.getDocumento();
		} else if(pedido.getPersona() instanceof PersonaJuridica) {
			PersonaJuridica pj = (PersonaJuridica) pedido.getPersona();
			idPersona = pj.getRut();
		}
		genExec.setParam(idPersona);
		genExec.setParam(pedido.getFechaHora());
		genExec.setParam(pedido.getEstado().getAsChar());
		genExec.setParam(pedido.getFechaProg());
		genExec.setParam(pedido.getHoraProg());
		genExec.setParam(pedido.getOrigen().getAsChar());
		genExec.setParam(pedido.getSubTotal());
		genExec.setParam(pedido.getIva());
		genExec.setParam(pedido.getTotal());
		genExec.setParam(pedido.getSinc().getAsChar());
		genExec.setParam(pedido.getUltAct());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.error("Excepcion al guardarPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer modificarPedido(Connection conn, Pedido pedido) throws PersistenciaException  {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_PEDIDO);
		Long idPersona = null;
		if(pedido.getPersona() instanceof PersonaFisica) {
			PersonaFisica pf = (PersonaFisica) pedido.getPersona();
			idPersona = pf.getDocumento();
		} else if(pedido.getPersona() instanceof PersonaJuridica) {
			PersonaJuridica pj = (PersonaJuridica) pedido.getPersona();
			idPersona = pj.getRut();
		}
		genExec.setParam(pedido.getEstado().getAsChar());
		genExec.setParam(pedido.getFechaProg());
		genExec.setParam(pedido.getHoraProg());
		genExec.setParam(pedido.getSubTotal());
		genExec.setParam(pedido.getIva());
		genExec.setParam(pedido.getTotal());
		genExec.setParam(pedido.getSinc().getAsChar());
		genExec.setParam(pedido.getUltAct());
		genExec.setParam(idPersona);
		genExec.setParam(pedido.getFechaHora());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.error("Excepcion al modificarPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer eliminarPedido(Connection conn, Pedido pedido) throws PersistenciaException  {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_DELETE_PEDIDO);
		Long idPersona = null;
		if(pedido.getPersona() instanceof PersonaFisica) {
			PersonaFisica pf = (PersonaFisica) pedido.getPersona();
			idPersona = pf.getDocumento();
		} else if(pedido.getPersona() instanceof PersonaJuridica) {
			PersonaJuridica pj = (PersonaJuridica) pedido.getPersona();
			idPersona = pj.getRut();
		}
		genExec.setParam(idPersona);
		genExec.setParam(pedido.getFechaHora());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.error("Excepcion al eliminarPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	@Override
	public Boolean checkExistPedido(Connection conn, Pedido pedido) throws PersistenciaException  {
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_CHK_EXIST_PEDIDO);
			genType.setParam(pedido.getPersona().getIdPersona());
			genType.setParam(pedido.getFechaHora());
			rs = (ResultSet) runGeneric(conn, genType);
			if(rs.next()) {
				return true;
			}
		} catch (ConectorException | SQLException e) {
			logger.error("Excepcion al checkExistePedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return false;
	}

}
