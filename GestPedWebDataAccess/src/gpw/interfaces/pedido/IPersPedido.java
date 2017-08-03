package gpw.interfaces.pedido;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.pedido.Pedido;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public interface IPersPedido {

	public List<Pedido> obtenerListaPedidoNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	
}
