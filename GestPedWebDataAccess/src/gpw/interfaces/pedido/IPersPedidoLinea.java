package gpw.interfaces.pedido;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.exceptions.PersistenciaException;

public interface IPersPedidoLinea {

	public List<PedidoLinea> obtenerListaPedidoLinea(Connection conn, Pedido pedido) throws PersistenciaException;
	public Integer guardarListaPedidoLinea(Connection conn, List<PedidoLinea> listaPedidoLinea) throws PersistenciaException;
	public Integer eliminarListaPedidoLinea(Connection conn, Pedido pedido) throws PersistenciaException;
	
}
