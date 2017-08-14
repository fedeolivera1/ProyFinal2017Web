package gpw.persistencia.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryUsuario;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.usuario.IPersUsuario;
import gpw.persistencia.conector.Conector;

/**
 * COMENTARIO GENERAL PARA LA CLASE PersistenciaUsuario:
 * no se utilizaran los metodos genericos de persistencia, ya que se requiere NO
 * loguear los datos de passwords de los usuarios.  
 */
public class PersistenciaUsuario extends Conector implements IPersUsuario, CnstQryUsuario {

	private static final Logger logger = Logger.getLogger(PersistenciaUsuario.class);
	private ResultSet rs;
	
	
	@Override
	public UsuarioWeb obtenerUsuario(Connection conn, String nombreUsuario, String passwd) throws PersistenciaException {
		logger.info("Ejecucion de obtenerUsuario para: " + nombreUsuario);
		UsuarioWeb usuario = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_LOGIN);
			ps.setString(1, nombreUsuario);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			if(rs.next()) {
				usuario = new UsuarioWeb();
				usuario.setNomUsu(nombreUsuario);
				usuario.setPass(passwd);
			}
		} catch (SQLException e) {
			logger.fatal("Excepcion al obtenerUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return usuario;
	}
	
	@Override
	public Integer guardarUsuario(Connection conn, UsuarioWeb usuario) throws PersistenciaException {
		logger.info("Ejecucion de guardarUsuario para: " + usuario.getNomUsu());
		Integer resultado = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_INSERT_USR);
			ps.setString(1, usuario.getNomUsu());
			ps.setString(2, usuario.getPass());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			logger.fatal("Excepcion al guardarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer modificarUsuario(Connection conn, UsuarioWeb usuario) throws PersistenciaException {
		logger.info("Ejecucion de modificarUsuario para: " + usuario.getNomUsu());
		Integer resultado = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_UPDATE_USR);
			ps.setString(1, usuario.getPass());
			ps.setString(2, usuario.getNomUsu());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			logger.fatal("Excepcion al modificarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer eliminarUsuario(Connection conn, UsuarioWeb usuario) throws PersistenciaException {
		logger.info("Ejecucion de eliminarUsuario para: " + usuario.getNomUsu());
		Integer resultado = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_DELETE_USR);
			ps.setString(1, usuario.getNomUsu());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			logger.fatal("Excepcion al eliminarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

}
