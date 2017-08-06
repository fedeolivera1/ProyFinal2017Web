package gpw.interfaces.pedido;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.pedido.Pedido;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public interface IPersPedido {

	public List<Pedido> obtenerListaPedidoNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	public Integer guardarPedido(Connection conn, Pedido pedido) throws PersistenciaException;
	public Integer modificarPedido(Connection conn, Pedido pedido) throws PersistenciaException;
	public Integer eliminarPedido(Connection conn, Pedido pedido) throws PersistenciaException;
	public Boolean checkExistPedido(Connection conn, Pedido pedido) throws PersistenciaException;
	
}
