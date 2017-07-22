package gpw.persistencia.persona;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryPersona;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.dominio.persona.Sexo;
import gpw.dominio.persona.TipoPersona;
import gpw.dominio.util.Origen;
import gpw.dominio.util.Sinc;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.persona.IPersPersona;
import gpw.persistencia.conector.Conector;
import gpw.types.Fecha;

public class PersistenciaPersona extends Conector implements IPersPersona, CnstQryPersona {

	private static final Logger logger = Logger.getLogger(PersistenciaPersona.class);
	private Integer resultado;
	private ResultSet rs;
	
	
	@Override
	public List<PersonaFisica> obtPersonaFisicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException {
		List<PersonaFisica> listaPf = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_PF_NOSINC);
			genType.setParam(fechaDesde);
			genType.setParam(fechaHasta);
			
			rs = (ResultSet) runGeneric(conn, genType);
			listaPf.addAll(cargarPfDesdeRs(conn, rs));
		} catch (ConectorException e) {
//			Conector.rollbackConn(conn);//TODO sacar
			logger.fatal("Excepcion al obtPersonaFisicaNoSinc: " + e.getMessage());
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaPf;
	}

	@Override
	public List<PersonaJuridica> obtPersonaJuridicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException {
		List<PersonaJuridica> listaPj = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_PJ_NOSINC);
			genType.setParam(fechaDesde);
			genType.setParam(fechaHasta);
			
			rs = (ResultSet) runGeneric(conn, genType);
			listaPj.addAll(cargarPjDesdeRs(conn, rs));
		} catch (ConectorException e) {
//			Conector.rollbackConn(conn);//TODO sacar
			logger.fatal("Excepcion al obtPersonaJuridicaNoSinc: " + e.getMessage());
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaPj;
	}
	
	@Override
	public Integer actualizarPersonaSinc(Connection conn, Long idPersona) throws PersistenciaException {
		try {
			GenSqlSelectType genExec = new GenSqlSelectType(QRY_UPDATE_PERS_SINC);
			genExec.setParam(Sinc.S.getAsChar());
			genExec.setParam(new Fecha(Fecha.AMDHMS));
			genExec.setParam(idPersona);
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
//			Conector.rollbackConn(conn);//TODO sacar
			logger.fatal("Excepcion al obtPersonaJuridicaNoSinc: " + e.getMessage());
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	/**
	 * metodo que recibe el resultset de la consulta, y carga la lista de personas fisica
	 * @param ResultSet rs
	 * @return lista persona fisica
	 * @throws PersistenciaException
	 */
	private List<PersonaFisica> cargarPfDesdeRs(Connection conn, ResultSet rs) throws PersistenciaException {
		List<PersonaFisica> listaPf = new ArrayList<>();
		PersistenciaTipoDoc ptd = new PersistenciaTipoDoc();
		PersistenciaDepLoc pdl = new PersistenciaDepLoc();
		try {
			while(rs.next()) {
				PersonaFisica pf = new PersonaFisica();
				pf.setDocumento(rs.getLong("documento"));
				pf.setTipoDoc(ptd.obtenerTipoDocPorId(conn, rs.getInt("id_tipo_doc")));
				pf.setApellido1(rs.getString("apellido1"));
				pf.setApellido2(rs.getString("apellido2"));
				pf.setNombre1(rs.getString("nombre1"));
				pf.setNombre2(rs.getString("nombre2"));
				pf.setFechaNac(rs.getDate("fecha_nac") != null ? new Fecha(rs.getDate("fecha_nac")) : null);
				char[] sexoChar = new char[1];
				rs.getCharacterStream("sexo").read(sexoChar);
				Sexo sexoE = Sexo.getSexoPorChar(sexoChar[0]);
				pf.setSexo(sexoE);
				//persona
				pf.setDireccion(rs.getString("direccion"));
				pf.setPuerta(rs.getString("puerta"));
				pf.setSolar(rs.getString("solar"));
				pf.setManzana(rs.getString("manzana"));
				pf.setKm(rs.getFloat("km"));
				pf.setComplemento(rs.getString("complemento"));
				pf.setTelefono(rs.getString("telefono"));
				pf.setCelular(rs.getString("celular"));
				pf.setEmail(rs.getString("email"));
				pf.setFechaReg(new Fecha(rs.getDate("fecha_reg")));
				char[] tipoChar = new char[1];
				rs.getCharacterStream("tipo").read(tipoChar);
				pf.setTipoPers(TipoPersona.getTipoPersonaPorChar(tipoChar[0]));
				pf.setLocalidad(pdl.obtenerLocalidadPorId(conn, rs.getInt("id_loc")));
				char[] origenChar = new char[1];
				rs.getCharacterStream("origen").read(origenChar);
				Origen origen = Origen.getOrigenPorChar(origenChar[0]);
				pf.setOrigen(origen);
				char[] sincChar = new char[1];
				rs.getCharacterStream("sinc").read(sincChar);
				Sinc sinc = Sinc.getSincPorChar(sincChar[0]);
				pf.setSinc(sinc);
				pf.setUltAct(new Fecha(rs.getTimestamp("ult_act")));
				
				listaPf.add(pf);
			}
		} catch (SQLException | PersistenciaException | IOException e) {
//			Conector.rollbackConn(conn);//TODO sacar
			logger.fatal("Excepcion al cargarRsConPf: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaPf;
	}
	
	/**
	 * metodo que recibe el resultset de la consulta, y carga la lista de persona juridica
	 * @param ResultSet rs
	 * @return lista persona juridica
	 * @throws PersistenciaException
	 */
	private List<PersonaJuridica> cargarPjDesdeRs(Connection conn, ResultSet rs) throws PersistenciaException {
		List<PersonaJuridica> listaPj = new ArrayList<>();
		PersistenciaDepLoc pdl = new PersistenciaDepLoc();
		try {
			while(rs.next()) {
				PersonaJuridica pj = new PersonaJuridica();
				pj.setRut(rs.getLong("rut"));
				pj.setNombre(rs.getString("nombre"));
				pj.setRazonSocial(rs.getString("razon_social"));
				pj.setBps(rs.getString("bps"));
				pj.setBse(rs.getString("bse"));
				char[] esProvChar = new char[1];
				rs.getCharacterStream("es_prov").read(esProvChar);
				pj.setEsProv((esProvChar[0] == S_CHAR) ? true : false);
				//persona
				pj.setDireccion(rs.getString("direccion"));
				pj.setPuerta(rs.getString("puerta"));
				pj.setSolar(rs.getString("solar"));
				pj.setManzana(rs.getString("manzana"));
				pj.setKm(rs.getFloat("km"));
				pj.setComplemento(rs.getString("complemento"));
				pj.setTelefono(rs.getString("telefono"));
				pj.setCelular(rs.getString("celular"));
				pj.setEmail(rs.getString("email"));
				pj.setFechaReg(new Fecha(rs.getDate("fecha_reg")));
				char[] tipoChar = new char[1];
				rs.getCharacterStream("tipo").read(tipoChar);
				pj.setTipoPers(TipoPersona.getTipoPersonaPorChar(tipoChar[0]));
				pj.setLocalidad(pdl.obtenerLocalidadPorId(conn, rs.getInt("id_loc")));
				char[] origenChar = new char[1];
				rs.getCharacterStream("origen").read(origenChar);
				Origen origen = Origen.getOrigenPorChar(origenChar[0]);
				pj.setOrigen(origen);
				char[] sincChar = new char[1];
				rs.getCharacterStream("sinc").read(sincChar);
				Sinc sinc = Sinc.getSincPorChar(sincChar[0]);
				pj.setSinc(sinc);
				pj.setUltAct(new Fecha(rs.getTimestamp("ult_act")));
				
				listaPj.add(pj);
			}
		} catch (SQLException | PersistenciaException | IOException e) {
//			Conector.rollbackConn(conn);//TODO sacar
			logger.fatal("Excepcion al cargarRsConPf: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaPj;
	}

}
