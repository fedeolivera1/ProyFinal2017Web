package gpw.interfaces.usuario;

import java.sql.Connection;

import gpw.dominio.usuario.UsuarioWeb;
import gpw.exceptions.PersistenciaException;

public interface IPersUsuario {

	public String loginUsuario(Connection conn, String nombreUsuario, String password) throws PersistenciaException;
	public UsuarioWeb obtenerUsuario(Connection conn, String nombreUsuario) throws PersistenciaException;
	public Long obtenerUsuarioPersActual(Connection conn, String nombreUsuario) throws PersistenciaException;
//	public UsuarioWeb obtenerUsuarioPorId(Connector conn, String nombreUsuario) throws PersistenciaException;
	public Integer guardarUsuario(Connection conn, UsuarioWeb usuario) throws PersistenciaException;
	public Integer modificarUsuarioSinPasswd(Connection conn, UsuarioWeb usuario) throws PersistenciaException;
	public Integer modificarUsuarioConPasswd(Connection conn, UsuarioWeb usuario) throws PersistenciaException;
	public Integer eliminarUsuario(Connection conn, UsuarioWeb usuario) throws PersistenciaException;
	
}
