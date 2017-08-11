package gpw.ejb;

import javax.ejb.Local;

import gpw.dominio.usuario.UsuarioWeb;
import gpw.exceptions.PersistenciaException;

@Local
public interface GpWebStatelessLocal {
	
	public UsuarioWeb obtenerUsuario(String nombreUsuario, String password) throws PersistenciaException;

}
