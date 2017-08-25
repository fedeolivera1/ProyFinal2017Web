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
		ResultSet rs = null;
		Unidad Unidad = null;
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_UNI_X_ID);
			genType.setParam(id);
			rs = (ResultSet) runGeneric(conn, genType);
			if(rs.next()) {
				Unidad = new Unidad();
				Unidad.setIdUnidad(rs.getInt("id_unidad"));
				Unidad.setNombre(rs.getString("nombre"));
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerUnidadPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return Unidad;
	}
	
	@Override
	public List<Unidad> obtenerListaUnidad(Connection conn) throws PersistenciaException {
		ResultSet rs = null;
		List<Unidad> listaUnidad = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_UNI);
			rs = (ResultSet) runGeneric(conn, genType);
			while(rs.next()) {
				Unidad Unidad = new Unidad();
				Unidad.setIdUnidad(rs.getInt("id_unidad"));
				Unidad.setNombre(rs.getString("nombre"));
				listaUnidad.add(Unidad);
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaUnidad;
	}

	@Override
	public Integer guardarUnidad(Connection conn, Unidad unidad) throws PersistenciaException {
		GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_UNI);
		genExec.setParam(unidad.getIdUnidad());
		genExec.setParam(unidad.getNombre());
		genExec.setParam(unidad.getSinc().getAsChar());
		genExec.setParam(unidad.getEstado().getAsInt());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al guardarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	@Override
	public Integer modificarUnidad(Connection conn, Unidad unidad) throws PersistenciaException {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_UNI);
		genExec.setParam(unidad.getNombre());
		genExec.setParam(unidad.getSinc().getAsChar());
		genExec.setParam(unidad.getIdUnidad());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al modificarUnidad: " + e.getMessage(), e);
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
		GenSqlExecType genExec = new GenSqlExecType(QRY_DELETE_UNI);
		genExec.setParam(Unidad.getIdUnidad());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al eliminarUnidad: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Boolean checkExistUnidad(Connection conn, Integer id) throws PersistenciaException {
		ResultSet rs = null;
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_CHECK_EXIST_UNIDAD);
			genType.setParam(id);
			rs = (ResultSet) runGeneric(conn, genType);
			if(rs.next()) {
				return true;
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaEmpresasPorTipo: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return false;
	}

}
