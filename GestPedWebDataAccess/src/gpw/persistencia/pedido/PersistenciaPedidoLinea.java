package gpw.persistencia.pedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryPedidoLinea;
import gpw.db.generic.GenSqlExecType;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.pedido.IPersPedidoLinea;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.producto.PersistenciaProducto;

public class PersistenciaPedidoLinea extends Conector implements IPersPedidoLinea, CnstQryPedidoLinea {

	private static final Logger logger = Logger.getLogger(PersistenciaPedidoLinea.class);
	private ResultSet rs;
	
	
	@Override
	public List<PedidoLinea> obtenerListaPedidoLinea(Connection conn, Pedido pedido) throws PersistenciaException {
		List<PedidoLinea> listaPedidoLinea = new ArrayList<>();	
		PersistenciaProducto pp = new PersistenciaProducto();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_PL);
			genType.setParam(pedido.getPersona().getIdPersona());
			genType.setParam(pedido.getFechaHora());
			rs = (ResultSet) runGeneric(conn, genType);
			while(rs.next()) {
				PedidoLinea pedidoLinea = new PedidoLinea();
				pedidoLinea.setProducto(pp.obtenerProductoPorId(conn, rs.getInt("id_producto")));
				pedidoLinea.setCantidad(rs.getInt("cantidad"));
				pedidoLinea.setPrecioUnit(rs.getDouble("precio_unit"));
				listaPedidoLinea.add(pedidoLinea);
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaPedidoLinea: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaPedidoLinea;
	}


	@Override
	public Integer guardarListaPedidoLinea(Connection conn, List<PedidoLinea> listaPedidoLinea) throws PersistenciaException {
		Integer resultado = null;
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_PL);
			ArrayList<Object> paramList = null;
			for(PedidoLinea pl : listaPedidoLinea) {
				paramList = new ArrayList<>();
				paramList.add(pl.getPedido().getPersona().getIdPersona());
				paramList.add(pl.getPedido().getFechaHora());
				paramList.add(pl.getProducto().getIdProducto());
				paramList.add(pl.getCantidad());
				paramList.add(pl.getPrecioUnit());
				genExec.setParamList(paramList);
			}
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.error("Excepcion al guardarListaPedidoLinea: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
	@Override
	public Integer eliminarListaPedidoLinea(Connection conn, Pedido pedido) throws PersistenciaException {
		Integer resultado = null;
		try {
			GenSqlExecType genExec = new GenSqlExecType(QRY_DELETE_PL);
			genExec.setParam(pedido.getPersona().getIdPersona());
			genExec.setParam(pedido.getFechaHora());
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.error("Excepcion al eliminarListaPedidoLinea: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

}
