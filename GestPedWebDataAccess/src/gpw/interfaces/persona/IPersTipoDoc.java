package gpw.interfaces.persona;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.persona.TipoDoc;
import gpw.exceptions.PersistenciaException;

public interface IPersTipoDoc {

	public TipoDoc obtenerTipoDocPorId(Connection conn, Integer id) throws PersistenciaException;
	public List<TipoDoc> obtenerListaTipoDoc(Connection conn) throws PersistenciaException;
	
}
