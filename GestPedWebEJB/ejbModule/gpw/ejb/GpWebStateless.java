package gpw.ejb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import gpw.dominio.persona.Departamento;
import gpw.dominio.persona.Localidad;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.dominio.persona.TipoDoc;
import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.dominio.util.Origen;
import gpw.dominio.util.Sinc;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.persona.IPersDepLoc;
import gpw.interfaces.persona.IPersPersona;
import gpw.interfaces.persona.IPersTipoDoc;
import gpw.interfaces.producto.IPersProducto;
import gpw.interfaces.producto.IPersTipoProd;
import gpw.interfaces.usuario.IPersUsuario;
import gpw.persistencia.persona.PersistenciaDepLoc;
import gpw.persistencia.persona.PersistenciaPersona;
import gpw.persistencia.persona.PersistenciaTipoDoc;
import gpw.persistencia.producto.PersistenciaProducto;
import gpw.persistencia.producto.PersistenciaTipoProd;
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
	private static IPersPersona interfacePersona;
	private static IPersTipoDoc interfaceTipoDoc;
	private static IPersDepLoc interfaceDepLoc;
	private static IPersTipoProd interfaceTipoProd;
	private static IPersProducto interfaceProducto;
	
	private static IPersUsuario getInterfaceUsuario() {
		if(interfaceUsuario == null) {
			interfaceUsuario = new PersistenciaUsuario();
		}
		return interfaceUsuario;
	}
	private static IPersPersona getInterfacePersona() {
		if(interfacePersona == null) {
			interfacePersona = new PersistenciaPersona();
		}
		return interfacePersona;
	}
	private static IPersTipoDoc getInterfaceTipoDoc() {
		if(interfaceTipoDoc == null) {
			interfaceTipoDoc = new PersistenciaTipoDoc();
		}
		return interfaceTipoDoc;
	}
	private static IPersDepLoc getInterfaceDepLoc() {
		if(interfaceDepLoc == null) {
			interfaceDepLoc = new PersistenciaDepLoc();
		}
		return interfaceDepLoc;
	}
	private static IPersTipoProd getInterfaceTipoProd() {
		if(interfaceTipoProd == null) {
			interfaceTipoProd = new PersistenciaTipoProd();
		}
		return interfaceTipoProd;
	}
	private static IPersProducto getInterfaceProducto() {
		if(interfaceProducto == null) {
			interfaceProducto = new PersistenciaProducto();
		}
		return interfaceProducto;
	}
	
    /**
     * Default constructor. 
     */
    public GpWebStateless() {
    }

    
    /*****************************************************************************************************************************************************/
	/* USUARIO */
	/*****************************************************************************************************************************************************/
    
    @Override
    public String loginUsuario(String nombreUsuario, String password) throws PersistenciaException {
    	logger.info("Se ingresa a loginUsuario para " + nombreUsuario);
    	String usuario = null;
    	try {
    		conn = ds.getConnection();
    		usuario = getInterfaceUsuario().loginUsuario(conn, nombreUsuario, password);
    	} catch (PersistenciaException | SQLException e) {
    		logger.fatal("Excepcion en GpWebStateless > obtenerUsuario: " + e.getMessage(), e);
    		throw new PersistenciaException(e);
    	}
    	return usuario;
    }
    
	@Override
	public UsuarioWeb obtenerUsuario(String nombreUsuario) throws PersistenciaException {
		logger.info("Se ingresa a obtenerUsuario para " + nombreUsuario);
		UsuarioWeb usuario = null;
		try {
			conn = ds.getConnection();
			usuario = getInterfaceUsuario().obtenerUsuario(conn, nombreUsuario);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return usuario;
	}

	@Override
	public Integer guardarUsuario(UsuarioWeb usr) throws PersistenciaException {
		logger.info("Se ingresa a guardarUsuario para " + usr.getNomUsu());
		Integer resultado = null;
		try {
			conn = ds.getConnection();
			usr.getPersona().setOrigen(Origen.W);
			usr.getPersona().setSinc(Sinc.N);
			if(usr.getPersona() instanceof PersonaFisica) {
				PersonaFisica pf = (PersonaFisica) usr.getPersona();
				getInterfacePersona().guardarPersFisica(conn, pf);
			} else if(usr.getPersona() instanceof PersonaJuridica) {
				PersonaJuridica pj = (PersonaJuridica) usr.getPersona();
				getInterfacePersona().guardarPersJuridica(conn, pj);
			}
			resultado = getInterfaceUsuario().guardarUsuario(conn, usr);
		} catch (PersistenciaException | SQLException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en GpWebStateless > guardarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer modificarUsuario(UsuarioWeb usr, Boolean modificaPasswd) throws PersistenciaException {
		logger.info("Se ingresa a guardarUsuario para " + usr.getNomUsu());
		Integer resultado = null;
		try {
			conn = ds.getConnection();
			usr.getPersona().setOrigen(Origen.W);
			usr.getPersona().setSinc(Sinc.N);
			Long idPersonaActual = getInterfaceUsuario().obtenerUsuarioPersActual(conn, usr.getNomUsu());
			//control eventual cambio de documento desde presentacion
			
			if(usr.getPersona() instanceof PersonaFisica) {
				PersonaFisica pf = (PersonaFisica) usr.getPersona();
				if(idPersonaActual != null && idPersonaActual.longValue() != pf.getIdPersona()) {
					pf.setDocumentoAnt(idPersonaActual);
				}
				resultado = getInterfacePersona().modificarPersFisica(conn, pf);
				usr.setPersona(pf);
			} else if(usr.getPersona() instanceof PersonaJuridica) {
				PersonaJuridica pj = (PersonaJuridica) usr.getPersona();
				if(idPersonaActual != null && idPersonaActual.longValue() != pj.getIdPersona()) {
					pj.setRutAnt(idPersonaActual);
				}
				resultado = getInterfacePersona().modificarPersJuridica(conn, pj);
				usr.setPersona(pj);
			}
			if(resultado > 0) {
				if(modificaPasswd) {
					resultado = getInterfaceUsuario().modificarUsuarioConPasswd(conn, usr);
				} else {
					resultado = getInterfaceUsuario().modificarUsuarioSinPasswd(conn, usr);
				}
			}
		} catch (PersistenciaException | SQLException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en GpWebStateless > modificarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer eliminarUsuario(UsuarioWeb usr) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	/*****************************************************************************************************************************************************/
	/* PERSONA */
	/*****************************************************************************************************************************************************/
	
	//tipo doc
	@Override
	public TipoDoc obtenerTipoDocPorId(Integer id) throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaDepartamentos...");
		TipoDoc tipoDoc = null;
		try {
			conn = ds.getConnection();
			tipoDoc = getInterfaceTipoDoc().obtenerTipoDocPorId(conn, id);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerTipoDocPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return tipoDoc;
	}
	@Override
	public List<TipoDoc> obtenerListaTipoDoc() throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaDepartamentos...");
		List<TipoDoc> listaTd = null;
		try {
			conn = ds.getConnection();
			listaTd = getInterfaceTipoDoc().obtenerListaTipoDoc(conn);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaTipoDoc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaTd;
	}
	
	//dep y loc
	@Override
	public List<Departamento> obtenerListaDepartamentos() throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaDepartamentos...");
		List<Departamento> listaDep = null;
		try {
			conn = ds.getConnection();
			listaDep = getInterfaceDepLoc().obtenerListaDepartamentos(conn);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaDepartamentos: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaDep;
	}

	@Override
	public Departamento obtenerDepartamentoPorId(Integer id) throws PersistenciaException {
		logger.info("Se ingresa a obtenerDepartamentoPorId...");
		Departamento dep = null;
		try {
			conn = ds.getConnection();
			dep = getInterfaceDepLoc().obtenerDepartamentoPorId(conn, id);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerDepartamentoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return dep;
	}

	@Override
	public List<Localidad> obtenerListaLocPorDep(Integer idDep) throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaLocPorDep...");
		List<Localidad> listaLoc = null;
		try {
			conn = ds.getConnection();
			listaLoc = getInterfaceDepLoc().obtenerListaLocPorDep(conn, idDep);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaLocPorDep: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaLoc;
	}

	@Override
	public Localidad obtenerLocalidadPorId(Integer idLoc) throws PersistenciaException {
		logger.info("Se ingresa a obtenerLocalidadPorId...");
		Localidad loc = null;
		try {
			conn = ds.getConnection();
			loc = getInterfaceDepLoc().obtenerLocalidadPorId(conn, idLoc);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerLocalidadPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return loc;
	}
	
	/*****************************************************************************************************************************************************/
	/* PRODUCTO */
	/*****************************************************************************************************************************************************/
	
	//tipo prod
	@Override
	public List<TipoProd> obtenerListaTipoProd() throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaTipoProd...");
		List<TipoProd> listaTp = null;
		try {
			conn = ds.getConnection();
			listaTp = getInterfaceTipoProd().obtenerListaTipoProd(conn);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaTp;
	}
	
	//producto
	@Override
	public List<Producto> obtenerListaProductoPorTipo(Integer tipoProd) throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaProductoPorTipo...");
		List<Producto> listaProd = null;
		try {
			conn = ds.getConnection();
			listaProd = getInterfaceProducto().obtenerListaProductoPorTipo(conn, tipoProd);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaProductoPorTipo: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaProd;
	}
	
}
