package gpw.persistencia.producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryUnidad;
import gpw.db.generic.GenSqlExecType;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.producto.Unidad;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.producto.IPersUnidad;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.persona.PersistenciaTipoDoc;

public class PersistenciaUnidad extends Conector implements IPersUnidad, CnstQryUnidad {

	private static final Logger logger = Logger.getLogger(PersistenciaTipoDoc.class);
	private Integer resultado;
	
	
	@Override
	public Unidad obtenerUnidadPorId(Connection conn, Integer id) throws PersistenciaException {
		Unidad unidad = null;
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_UNI_X_ID);
			genSel.setParam(id);
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				if(rs.next()) {
					unidad = new Unidad();
					unidad.setIdUnidad(rs.getInt("id_unidad"));
					unidad.setNombre(rs.getString("nombre"));
				}
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerUnidadPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtenerUnidadPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return unidad;
	}
	
	@Override
	public List<Unidad> obtenerListaUnidad(Connection conn) throws PersistenciaException {
		List<Unidad> listaUnidad = new ArrayList<>();
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_UNI);
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				while(rs.next()) {
					Unidad Unidad = new Unidad();
					Unidad.setIdUnidad(rs.getInt("id_unidad"));
					Unidad.setNombre(rs.getString("nombre"));
					listaUnidad.add(Unidad);
				}
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtenerListaUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaUnidad;
	}

	@Override
	public Integer guardarUnidad(Connection conn, Unidad unidad) throws PersistenciaException {
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_UNI);
			genExec.setParam(unidad.getIdUnidad());
			genExec.setParam(unidad.getNombre());
			genExec.setParam(unidad.getSinc().getAsChar());
			genExec.setParam(unidad.getEstado().getAsInt());
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al guardarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al guardarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	@Override
	public Integer modificarUnidad(Connection conn, Unidad unidad) throws PersistenciaException {
		Integer resultado = null;
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_UNI);
			genExec.setParam(unidad.getNombre());
			genExec.setParam(unidad.getSinc().getAsChar());
			genExec.setParam(unidad.getIdUnidad());
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al modificarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al modificarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

//	@Override
//	public Integer modificarSincUnidad(Connection conn, Unidad unidad) throws PersistenciaException {
//		Integer resultado = null;
//		GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_SINC_UNI);
//		genExec.setParam(unidad.getSinc().getAsChar());
//		genExec.setParam(unidad.getIdUnidad());
//		try {
//			resultado = (Integer) runGeneric(conn, genExec);
//		} catch (ConectorException e) {
//			logger.fatal("Excepcion al modificarUnidad: " + e.getMessage(), e);
//			throw new PersistenciaException(e);
//		}
//		return resultado;
//	}

	@Override
	public Integer eliminarUnidad(Connection conn, Unidad Unidad) throws PersistenciaException {
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_DELETE_UNI);
			genExec.setParam(Unidad.getIdUnidad());
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al eliminarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al eliminarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Boolean checkExistUnidad(Connection conn, Integer id) throws PersistenciaException {
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_CHECK_EXIST_UNIDAD);
			genSel.setParam(id);
			try (ResultSet rs = (ResultSet) runGeneric(conn, genSel)) {
				if(rs.next()) {
					return true;
				}
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al checkExistUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al checkExistUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return false;
	}

}
