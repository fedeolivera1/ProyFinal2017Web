package gpw.ejb;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import gpw.dominio.usuario.UsuarioWeb;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.usuario.IPersUsuario;
import gpw.persistencia.usuario.PersistenciaUsuario;

/**
 * Session Bean implementation class GpWebStateless
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GpWebStateless implements GpWebStatelessRemote, GpWebStatelessLocal {

	private static Logger logger = Logger.getLogger(GpWebStateless.class);
	
	@Resource(mappedName="java:jboss/datasources/dsGestPedWeb")
	private DataSource ds;
	private Connection conn;
	@Resource 
	private EJBContext context;
	
	private static IPersUsuario interfaceUsuario;
	
	private static IPersUsuario getInterfaceUsuario() {
		if(interfaceUsuario == null) {
			interfaceUsuario = new PersistenciaUsuario();
		}
		return interfaceUsuario;
	}
	
    /**
     * Default constructor. 
     */
    public GpWebStateless() {
    }

	@Override
	public UsuarioWeb obtenerUsuario(String nombreUsuario, String password) throws PersistenciaException {
		logger.info("Se ingresa a obtenerUsuario para " + nombreUsuario);
		UsuarioWeb usuario = null;
		try {
			conn = ds.getConnection();
			usuario = getInterfaceUsuario().obtenerUsuario(conn, nombreUsuario, password);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en ManagerUsuario > obtenerUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return usuario;
	}
    
    

}
