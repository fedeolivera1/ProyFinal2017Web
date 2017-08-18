package gpw.interfaces.producto;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.producto.Producto;
import gpw.exceptions.PersistenciaException;

public interface IPersProducto {

	public Producto obtenerProductoPorId(Connection conn, Integer id) throws PersistenciaException;
	public List<Producto> obtenerListaProductoPorTipo(Connection conn, Integer tipoProd) throws PersistenciaException;
	public Integer guardarProducto(Connection conn, Producto producto) throws PersistenciaException;
	public Integer modificarProducto(Connection conn, Producto producto) throws PersistenciaException;
	public Integer desactivarProducto(Connection conn, Producto producto) throws PersistenciaException;
	public Boolean checkExistProducto(Connection conn, Integer idProducto) throws PersistenciaException;
}
