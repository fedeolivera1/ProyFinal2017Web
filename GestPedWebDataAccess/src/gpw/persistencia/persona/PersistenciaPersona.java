package gpw.persistencia.persona;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryPersona;
import gpw.db.generic.GenSqlExecType;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.persona.Persona;
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

	
	@Override
	public PersonaFisica obtenerPersFisicaPorId(Connection conn, Long id) throws PersistenciaException {
		PersonaFisica pf = null;
		try {
			PersistenciaTipoDoc ptd = new PersistenciaTipoDoc();
			PersistenciaDepLoc pdl = new PersistenciaDepLoc();
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_PF_XID);
			genSel.setParam(id);
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				if(rs.next()) {
					pf = new PersonaFisica();
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
				}
			}
		} catch (ConectorException | SQLException | IOException e) {
			logger.fatal("Excepcion al obtenerPersFisicaPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtenerPersFisicaPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return pf;
	}
	
	@Override
	public PersonaJuridica obtenerPersJuridicaPorId(Connection conn, Long id) throws PersistenciaException {
		PersonaJuridica pj = null;
		try {
			PersistenciaDepLoc pdl = new PersistenciaDepLoc();
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_PJ_XID);
			genSel.setParam(id);
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				if(rs.next()) {
					pj = new PersonaJuridica();
					pj.setRut(rs.getLong("rut"));
					pj.setNombre(rs.getString("nombre"));
					pj.setRazonSocial(rs.getString("razon_social"));
					pj.setBps(rs.getString("bps"));
					pj.setBse(rs.getString("bse"));
					char[] esProvChar = new char[1];
					rs.getCharacterStream("es_prov").read(esProvChar);
					pj.setEsProv(esProvChar[0] == S_CHAR ? true : false);
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
				}
			}
		} catch (ConectorException | SQLException | IOException e) {
			logger.fatal("Excepcion al obtenerPersJuridicaPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtenerPersJuridicaPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return pj;
	}
	
	@Override
	public List<PersonaFisica> obtPersonaFisicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException {
		List<PersonaFisica> listaPf = new ArrayList<>();
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_PF_NOSINC);
			genSel.setParam(fechaDesde);
			genSel.setParam(fechaHasta);
			
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				listaPf.addAll(cargarPfDesdeRs(conn, rs));
			}
		} catch (ConectorException e) {
			logger.fatal("Excepcion al obtPersonaFisicaNoSinc: " + e.getMessage());
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtPersonaFisicaNoSinc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaPf;
	}
	
	@Override
	public Integer guardarPersFisica(Connection conn, PersonaFisica pf) throws PersistenciaException {
		try {
			Long documento = pf.getDocumento();
			
			guardarPersona(conn, documento, pf.getDireccion(), pf.getPuerta(), pf.getSolar(), pf.getManzana(),
					pf.getKm(), pf.getComplemento(), pf.getTelefono(), pf.getCelular(), pf.getEmail(),
					pf.getFechaReg(), pf.getTipoPers(), pf.getLocalidad().getIdLocalidad(), pf.getOrigen(),
					pf.getSinc(), pf.getUltAct());
			
			GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_PF);
			genExec.setParam(documento);
			genExec.setParam(pf.getTipoDoc().getIdTipoDoc());
			genExec.setParam(pf.getApellido1());
			genExec.setParam(pf.getApellido2());
			genExec.setParam(pf.getNombre1());
			genExec.setParam(pf.getNombre2());
			genExec.setParam(pf.getFechaNac());
			genExec.setParam(pf.getSexo().getAsChar());
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al guardarPersFisica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al guardarPersFisica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	@Override
	public Integer modificarPersFisica(Connection conn, PersonaFisica pf) throws PersistenciaException {
		try {
			Long documento = pf.getDocumento();
			Long docAnt = pf.getDocumentoAnt() != null ? pf.getDocumentoAnt() : null;
			
			modificarPersona(conn, documento, docAnt, pf.getDireccion(), pf.getPuerta(), pf.getSolar(), pf.getManzana(),
					pf.getKm(), pf.getComplemento(), pf.getTelefono(), pf.getCelular(), pf.getEmail(),
					pf.getFechaReg(), pf.getLocalidad().getIdLocalidad(), pf.getOrigen(),
					pf.getSinc(), pf.getUltAct());
			
			GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_PF);
			genExec.setParam(pf.getTipoDoc().getIdTipoDoc());
			genExec.setParam(pf.getApellido1());
			genExec.setParam(pf.getApellido2());
			genExec.setParam(pf.getNombre1());
			genExec.setParam(pf.getNombre2());
			genExec.setParam(pf.getFechaNac());
			genExec.setParam(pf.getSexo().getAsChar());
			genExec.setParam(documento);
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al modificarPersFisica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al modificarPersFisica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public List<PersonaJuridica> obtPersonaJuridicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException {
		List<PersonaJuridica> listaPj = new ArrayList<>();
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_PJ_NOSINC);
			genSel.setParam(fechaDesde);
			genSel.setParam(fechaHasta);
			
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				listaPj.addAll(cargarPjDesdeRs(conn, rs));
			}
		} catch (ConectorException e) {
			logger.fatal("Excepcion al obtPersonaJuridicaNoSinc: " + e.getMessage());
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtPersonaJuridicaNoSinc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaPj;
	}
	
	@Override
	public Integer guardarPersJuridica(Connection conn, PersonaJuridica pj) throws PersistenciaException {
		try {
			guardarPersona(conn, pj.getRut(), pj.getDireccion(), pj.getPuerta(), pj.getSolar(), pj.getManzana(),
					pj.getKm(), pj.getComplemento(), pj.getTelefono(), pj.getCelular(), pj.getEmail(),
					pj.getFechaReg(), pj.getTipoPers(), pj.getLocalidad().getIdLocalidad(), pj.getOrigen(),
					pj.getSinc(), pj.getUltAct());
			
			GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_PJ);
			genExec.setParam(pj.getRut());
			genExec.setParam(pj.getNombre());
			genExec.setParam(pj.getRazonSocial());
			genExec.setParam(pj.getBps());
			genExec.setParam(pj.getBse());
			genExec.setParam(pj.getEsProv() ? S_CHAR : N_CHAR);
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al guardarPersJuridica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al guardarPersJuridica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	@Override
	public Integer modificarPersJuridica(Connection conn, PersonaJuridica pj) throws PersistenciaException {
		try {
			Long rut = pj.getRut();
			Long rutAnt = pj.getRutAnt() != null ? pj.getRutAnt() : null;
			
			modificarPersona(conn, rut, rutAnt, pj.getDireccion(), pj.getPuerta(), pj.getSolar(), pj.getManzana(),
					pj.getKm(), pj.getComplemento(), pj.getTelefono(), pj.getCelular(), pj.getEmail(),
					pj.getFechaReg(), pj.getLocalidad().getIdLocalidad(), pj.getOrigen(),
					pj.getSinc(), pj.getUltAct());
			
			GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_PJ);
			genExec.setParam(pj.getNombre());
			genExec.setParam(pj.getRazonSocial());
			genExec.setParam(pj.getBps());
			genExec.setParam(pj.getBse());
			genExec.setParam(pj.getEsProv() ? S_CHAR : N_CHAR);
			genExec.setParam(rut);
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al modificarPersJuridica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al modificarPersJuridica: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	@Override
	public Integer actualizarPersonaSinc(Connection conn, Long idPersona) throws PersistenciaException {
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_PERS_SINC);
			genExec.setParam(Sinc.S.getAsChar());
			genExec.setParam(new Fecha(Fecha.AMDHMS));
			genExec.setParam(idPersona);
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al actualizarPersonaSinc: " + e.getMessage());
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al actualizarPersonaSinc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	/***************************************************/
	/* METODOS GENERICOS */
	/***************************************************/
	
	@Override
	public Persona obtenerPersGenerico(Connection conn, Long idPersona) throws PersistenciaException {
		Persona persona = null;
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_PERS_GENERIC);
			genSel.setParam(idPersona);
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				if(rs.next()) {
					char[] tipoChar = new char[1];
					rs.getCharacterStream("tipo").read(tipoChar);
					TipoPersona tp = TipoPersona.getTipoPersonaPorChar(tipoChar[0]);
					if(tp.equals(TipoPersona.F)) {
						persona = obtenerPersFisicaPorId(conn, idPersona);
					} else if(tp.equals(TipoPersona.J)) {
						persona = obtenerPersJuridicaPorId(conn, idPersona);
					} else {
						throw new PersistenciaException("Tipo de Persona no soportado...");
					}
				}
			}
		} catch (ConectorException | SQLException | IOException e) {
			logger.fatal("Excepcion al obtenerPersGenerico: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtenerPersGenerico: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return persona;
	}
	
	/**
	 * metodo que recibe el resultset de la consulta, y carga la lista de personas fisica
	 * @param ResultSet rs
	 * @return lista persona fisica
	 * @throws PersistenciaException
	 */
	private List<PersonaFisica> cargarPfDesdeRs(Connection conn, ResultSet rs) throws PersistenciaException {
		List<PersonaFisica> listaPf = new ArrayList<>();
		try {
			PersistenciaTipoDoc ptd = new PersistenciaTipoDoc();
			PersistenciaDepLoc pdl = new PersistenciaDepLoc();
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
			logger.fatal("Excepcion al cargarPfDesdeRs: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al cargarPfDesdeRs: " + e.getMessage(), e);
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
		try {
			PersistenciaDepLoc pdl = new PersistenciaDepLoc();
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
			logger.fatal("Excepcion al cargarPjDesdeRs: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al cargarPjDesdeRs: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaPj;
	}
	
	private Integer guardarPersona(Connection conn, Long idPersona, String direccion, String puerta, String solar, String manzana,
			Float km, String complemento, String telefono, String celular, String email, Fecha fechaReg, TipoPersona tipoPers, 
			Integer idLoc, Origen origen, Sinc sinc, Fecha ultAct) throws PersistenciaException {
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_PERS);
			genExec.setParam(idPersona);
			genExec.setParam(direccion);
			genExec.setParam(puerta);
			genExec.setParam(solar);
			genExec.setParam(manzana);
			genExec.setParam(km);
			genExec.setParam(complemento);
			genExec.setParam(telefono);
			genExec.setParam(celular);
			genExec.setParam(email);
			genExec.setParam(fechaReg);
			genExec.setParam(tipoPers.getAsChar());
			genExec.setParam(idLoc);
			genExec.setParam(origen.getAsChar());
			genExec.setParam(sinc.getAsChar());
			genExec.setParam(ultAct);
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al guardarPersona: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al guardarPersona: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	private Integer modificarPersona(Connection conn, Long idPersona, Long idPersonaAnt, String direccion, String puerta, String solar, String manzana,
			Float km, String complemento, String telefono, String celular, String email, Fecha fechaReg, Integer idLoc,
			Origen origen, Sinc sinc, Fecha ultAct) throws PersistenciaException {
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_PERS);
			genExec.setParam(idPersona);
			genExec.setParam(direccion);
			genExec.setParam(puerta);
			genExec.setParam(solar);
			genExec.setParam(manzana);
			genExec.setParam(km);
			genExec.setParam(complemento);
			genExec.setParam(telefono);
			genExec.setParam(celular);
			genExec.setParam(email);
			genExec.setParam(fechaReg);
			genExec.setParam(idLoc);
			genExec.setParam(origen.getAsChar());
			genExec.setParam(sinc.getAsChar());
			genExec.setParam(ultAct);
			genExec.setParam(idPersonaAnt != null ? idPersonaAnt : idPersona);
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al modificarPersona: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al modificarPersona: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

}
