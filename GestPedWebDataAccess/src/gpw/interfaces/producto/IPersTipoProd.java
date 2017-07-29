package gpw.interfaces.producto;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.producto.TipoProd;
import gpw.exceptions.PersistenciaException;

public interface IPersTipoProd {

	public TipoProd obtenerTipoProdPorId(Connection conn, Integer id) throws PersistenciaException;
	public List<TipoProd> obtenerListaTipoProd(Connection conn) throws PersistenciaException;
	public Integer guardarTipoProd(Connection conn, TipoProd tipoProd) throws PersistenciaException;
	public Integer modificarTipoProd(Connection conn, TipoProd tipoProd) throws PersistenciaException;
//	public Integer modificarSincTipoProd(Connection conn, TipoProd tipoProd) throws PersistenciaException;
	public Integer eliminarTipoProd(Connection conn, TipoProd tipoProd) throws PersistenciaException;
	public Boolean checkExistTipoProd(Connection conn, Integer id) throws PersistenciaException;
	
}
