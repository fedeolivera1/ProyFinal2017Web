package gpw.interfaces.persona;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.persona.Departamento;
import gpw.dominio.persona.Localidad;
import gpw.exceptions.PersistenciaException;

public interface IPersDepLoc {

	public List<Departamento> obtenerListaDepartamentos(Connection conn) throws PersistenciaException;
	public Departamento obtenerDepartamentoPorId(Connection conn, Integer id) throws PersistenciaException;
	public List<Localidad> obtenerListaLocPorDep(Connection conn, Integer idDep) throws PersistenciaException;
	public Localidad obtenerLocalidadPorId(Connection conn, Integer idLoc) throws PersistenciaException;
	
}
