package gpw.interfaces.producto;

import java.sql.Connection;

import gpw.dominio.producto.Producto;
import gpw.exceptions.PersistenciaException;

public interface IPersProducto {

	public Integer guardarProducto(Connection conn, Producto producto) throws PersistenciaException;
	public Integer modificarProducto(Connection conn, Producto producto) throws PersistenciaException;
	public Integer desactivarProducto(Connection conn, Producto producto) throws PersistenciaException;
	public Boolean checkExistProducto(Connection conn, Integer idProducto) throws PersistenciaException;
}
