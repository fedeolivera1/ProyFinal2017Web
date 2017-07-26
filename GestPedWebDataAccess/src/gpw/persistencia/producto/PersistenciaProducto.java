package gpw.persistencia.producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryProducto;
import gpw.db.generic.GenSqlExecType;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.producto.Producto;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.producto.IPersProducto;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.persona.PersistenciaPersona;

public class PersistenciaProducto extends Conector implements IPersProducto, CnstQryProducto {

	private static final Logger logger = Logger.getLogger(PersistenciaPersona.class);
	private Integer resultado;
	private ResultSet rs;
	
	
	@Override
	public Integer guardarProducto(Connection conn, Producto producto) throws PersistenciaException {
		GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_PROD);
		genExec.setParam(producto.getTipoProd().getIdTipoProd());
		genExec.setParam(producto.getCodigo());
		genExec.setParam(producto.getNombre());
		genExec.setParam(producto.getDescripcion());
		genExec.setParam(producto.getStockMin());
		genExec.setParam(producto.getAplIva().getAsChar());
		genExec.setParam(producto.getUnidad().getIdUnidad());
		genExec.setParam(producto.getCantUnidad());
		genExec.setParam(producto.getPrecio());
		genExec.setParam(producto.getSinc().getAsChar());
		genExec.setParam(producto.getUltAct());
		genExec.setParam(producto.getEstadoProd().getAsInt());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al guardarProducto: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer modificarProducto(Connection conn, Producto producto) throws PersistenciaException {
		GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_PROD);
		genExec.setParam(producto.getTipoProd().getIdTipoProd());
		genExec.setParam(producto.getCodigo());
		genExec.setParam(producto.getNombre());
		genExec.setParam(producto.getDescripcion());
		genExec.setParam(producto.getStockMin());
		genExec.setParam(producto.getAplIva().getAsChar());
		genExec.setParam(producto.getUnidad().getIdUnidad());
		genExec.setParam(producto.getCantUnidad());
		genExec.setParam(producto.getPrecio());
		genExec.setParam(producto.getSinc().getAsChar());
		genExec.setParam(producto.getUltAct());
		genExec.setParam(producto.getIdProducto());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al modificarProducto: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer desactivarProducto(Connection conn, Producto producto) throws PersistenciaException {
		GenSqlExecType genExec = new GenSqlExecType(QRY_DESACT_PROD);
		genExec.setParam(producto.getEstadoProd().getAsInt());
		genExec.setParam(producto.getIdProducto());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al desactivarProducto: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Boolean checkExistProducto(Connection conn, Integer idProducto) throws PersistenciaException {
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_CHECK_EXIST_PROD);
			genType.setParam(idProducto);
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
