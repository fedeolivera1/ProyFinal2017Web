package gpw.interfaces.producto;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.producto.Unidad;
import gpw.exceptions.PersistenciaException;

public interface IPersUnidad {

	public Unidad obtenerUnidadPorId(Connection conn, Integer id) throws PersistenciaException;
	public List<Unidad> obtenerListaUnidad(Connection conn) throws PersistenciaException;
	public Integer guardarUnidad(Connection conn, Unidad unidad) throws PersistenciaException;
	public Integer modificarUnidad(Connection conn, Unidad unidad) throws PersistenciaException;
//	public Integer modificarSincUnidad(Connection conn, Unidad unidad) throws PersistenciaException;
	public Integer eliminarUnidad(Connection conn, Unidad unidad) throws PersistenciaException;
	public Boolean checkExistUnidad(Connection conn, Integer id) throws PersistenciaException;
	
}
