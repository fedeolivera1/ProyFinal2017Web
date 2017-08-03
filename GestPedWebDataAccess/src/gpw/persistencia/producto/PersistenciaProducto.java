package gpw.persistencia.producto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryProducto;
import gpw.db.generic.GenSqlExecType;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.producto.AplicaIva;
import gpw.dominio.producto.EstadoProd;
import gpw.dominio.producto.Producto;
import gpw.dominio.util.Sinc;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.producto.IPersProducto;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.persona.PersistenciaPersona;
import gpw.types.Fecha;

public class PersistenciaProducto extends Conector implements IPersProducto, CnstQryProducto {

	private static final Logger logger = Logger.getLogger(PersistenciaPersona.class);
	private Integer resultado;
	private ResultSet rs;
	
	@Override
	public Producto obtenerProductoPorId(Connection conn, Integer id) throws PersistenciaException {
		Producto producto = null;
		PersistenciaTipoProd ptp = new PersistenciaTipoProd();
		PersistenciaUnidad pu = new PersistenciaUnidad();
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_PROD_XID);
			genSel.setParam(id);
			rs = (ResultSet) runGeneric(conn, genSel);
			if(rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getInt("id_producto"));
				producto.setTipoProd(ptp.obtenerTipoProdPorId(conn, rs.getInt("id_tipo_prod")));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setStockMin(rs.getFloat("stock_min"));
				char[] aplIvaChar = new char[1];
				rs.getCharacterStream("apl_iva").read(aplIvaChar);
				AplicaIva aplIva = AplicaIva.getAplicaIvaPorChar(aplIvaChar[0]);
				producto.setAplIva(aplIva);
				producto.setUnidad(pu.obtenerUnidadPorId(conn, rs.getInt("id_unidad")));
				producto.setCantUnidad(rs.getInt("cant_unidad"));
				producto.setPrecio(rs.getDouble("precio"));
				char[] sincChar = new char[1];
				rs.getCharacterStream("sinc").read(sincChar);
				Sinc sinc = Sinc.getSincPorChar(sincChar[0]);
				producto.setSinc(sinc);
				producto.setUltAct(new Fecha(rs.getTimestamp("ult_act")));
				producto.setEstadoProd(EstadoProd.getEstadoProdPorInt(rs.getInt("activo")));
			}
		} catch (ConectorException | SQLException | IOException e) {
			logger.fatal("Excepcion al obtenerProductoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return producto;
	}
	
	@Override
	public Integer guardarProducto(Connection conn, Producto producto) throws PersistenciaException {
		GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_PROD);
		genExec.setParam(producto.getIdProducto());
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
