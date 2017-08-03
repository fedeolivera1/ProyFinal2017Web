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
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
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

}
