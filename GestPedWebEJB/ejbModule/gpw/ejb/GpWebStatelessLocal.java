package gpw.ejb;

import java.util.List;

import javax.ejb.Local;

import gpw.dominio.persona.Departamento;
import gpw.dominio.persona.Localidad;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.exceptions.PersistenciaException;

@Local
public interface GpWebStatelessLocal {
	
	//usuario
	public UsuarioWeb obtenerUsuario(String nombreUsuario, String password) throws PersistenciaException;
//	public UsuarioWeb obtenerUsuarioPorId(String nombreUsuario, String password) throws PersistenciaException;
	public Integer guardarUsuario(UsuarioWeb usr) throws PersistenciaException;
	public Integer modificarUsuario(UsuarioWeb usr) throws PersistenciaException;
	public Integer eliminarUsuario(UsuarioWeb usr) throws PersistenciaException;
	
	//persona
	public List<Departamento> obtenerListaDepartamentos() throws PersistenciaException;
	public Departamento obtenerDepartamentoPorId(Integer id) throws PersistenciaException;
	public List<Localidad> obtenerListaLocPorDep(Integer idDep) throws PersistenciaException;
	public Localidad obtenerLocalidadPorId(Integer idLoc) throws PersistenciaException;
	
	
}
