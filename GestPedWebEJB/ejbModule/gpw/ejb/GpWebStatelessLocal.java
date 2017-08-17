package gpw.ejb;

import java.util.List;

import javax.ejb.Local;

import gpw.dominio.persona.Departamento;
import gpw.dominio.persona.Localidad;
import gpw.dominio.persona.TipoDoc;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.exceptions.PersistenciaException;

@Local
public interface GpWebStatelessLocal {
	
	/*
	 * usuario
	 */
	public String loginUsuario(String nombreUsuario, String password) throws PersistenciaException;
	public UsuarioWeb obtenerUsuario(String nombreUsuario) throws PersistenciaException;
//	public UsuarioWeb obtenerUsuarioPorId(String nombreUsuario, String password) throws PersistenciaException;
	public Integer guardarUsuario(UsuarioWeb usr) throws PersistenciaException;
	public Integer modificarUsuario(UsuarioWeb usr, Boolean modificaPasswd) throws PersistenciaException;
	public Integer eliminarUsuario(UsuarioWeb usr) throws PersistenciaException;
	
	/*
	 * persona
	 */
	//tipo doc
	public TipoDoc obtenerTipoDocPorId(Integer id) throws PersistenciaException;
	public List<TipoDoc> obtenerListaTipoDoc() throws PersistenciaException;
	//dep y loc
	public List<Departamento> obtenerListaDepartamentos() throws PersistenciaException;
	public Departamento obtenerDepartamentoPorId(Integer id) throws PersistenciaException;
	public List<Localidad> obtenerListaLocPorDep(Integer idDep) throws PersistenciaException;
	public Localidad obtenerLocalidadPorId(Integer idLoc) throws PersistenciaException;
	
	
}
