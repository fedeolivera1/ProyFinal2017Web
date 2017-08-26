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
import gpw.persistencia.persona.PersistenciaPersona;

/**
 * COMENTARIO GENERAL PARA LA CLASE PersistenciaUsuario:
 * no se utilizaran los metodos genericos de persistencia, ya que se requiere NO
 * loguear los datos de passwords de los usuarios.  
 */
public class PersistenciaUsuario extends Conector implements IPersUsuario, CnstQryUsuario {

	private static final Logger logger = Logger.getLogger(PersistenciaUsuario.class);
	
	
	@Override
	public String loginUsuario(Connection conn, String nombreUsuario, String passwd) throws PersistenciaException {
		logger.info("Ejecucion de loginUsuario para: " + nombreUsuario);
		String usuario = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_LOGIN);
			ps.setString(1, nombreUsuario);
			ps.setString(2, passwd);
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					usuario = rs.getString("nom_usu");
				}
			}
		} catch (SQLException e) {
			logger.fatal("Excepcion al loginUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al loginUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return usuario;
	}
	
	@Override
	public UsuarioWeb obtenerUsuario(Connection conn, String nombreUsuario) throws PersistenciaException {
		logger.info("Ejecucion de obtenerUsuario para: " + nombreUsuario);
		UsuarioWeb usuario = null;
		PersistenciaPersona pp = new PersistenciaPersona();
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_OBT_USR);
			ps.setString(1, nombreUsuario);
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					usuario = new UsuarioWeb();
					usuario.setNomUsu(rs.getString("nom_usu"));
					//usuario.setPass(passwd);//no obtengo la password
					usuario.setPersona(pp.obtenerPersGenerico(conn, rs.getLong("id_persona")));
				}
			}
		} catch (SQLException e) {
			logger.fatal("Excepcion al obtenerUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtenerUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return usuario;
	}
	
	@Override
	public Long obtenerUsuarioPersActual(Connection conn, String nombreUsuario) throws PersistenciaException {
		logger.info("Ejecucion de obtenerUsuario para: " + nombreUsuario);
		Long idPers = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_OBT_USR_PERSACT);
			ps.setString(1, nombreUsuario);
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					idPers = rs.getLong("id_persona");
				}
			}
		} catch (SQLException e) {
			logger.fatal("Excepcion al obtenerUsuarioPersActual: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al obtenerUsuarioPersActual: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return idPers;
	}
	
	@Override
	public Integer guardarUsuario(Connection conn, UsuarioWeb usuario) throws PersistenciaException {
		logger.info("Ejecucion de guardarUsuario para: " + usuario.getNomUsu());
		Integer resultado = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_INSERT_USR);
			ps.setString(1, usuario.getNomUsu());
			ps.setString(2, usuario.getPass());
			ps.setLong(3, usuario.getPersona().getIdPersona());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			logger.fatal("Excepcion al guardarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al guardarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer modificarUsuarioSinPasswd(Connection conn, UsuarioWeb usuario) throws PersistenciaException {
		logger.info("Ejecucion de modificarUsuario para: " + usuario.getNomUsu());
		Integer resultado = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_UPDATE_USR_SP);
			ps.setLong(1, usuario.getPersona().getIdPersona());
			ps.setString(2, usuario.getNomUsu());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			logger.fatal("Excepcion al modificarUsuarioSinPasswd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al modificarUsuarioSinPasswd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	@Override
	public Integer modificarUsuarioConPasswd(Connection conn, UsuarioWeb usuario) throws PersistenciaException {
		logger.info("Ejecucion de modificarUsuario para: " + usuario.getNomUsu());
		Integer resultado = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QRY_UPDATE_USR_CP);
			ps.setString(1, usuario.getPass());
			ps.setLong(2, usuario.getPersona().getIdPersona());
			ps.setString(3, usuario.getNomUsu());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			logger.fatal("Excepcion al modificarUsuarioConPasswd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al modificarUsuarioConPasswd: " + e.getMessage(), e);
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
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA al eliminarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

}
