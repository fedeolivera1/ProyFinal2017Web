package gpw.ejb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
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
import gpw.interfaces.pedido.IPersPedido;
import gpw.interfaces.pedido.IPersPedidoLinea;
import gpw.interfaces.persona.IPersDepLoc;
import gpw.interfaces.persona.IPersPersona;
import gpw.interfaces.persona.IPersTipoDoc;
import gpw.interfaces.producto.IPersProducto;
import gpw.interfaces.producto.IPersTipoProd;
import gpw.interfaces.usuario.IPersUsuario;
import gpw.persistencia.pedido.PersistenciaPedido;
import gpw.persistencia.pedido.PersistenciaPedidoLinea;
import gpw.persistencia.persona.PersistenciaDepLoc;
import gpw.persistencia.persona.PersistenciaPersona;
import gpw.persistencia.persona.PersistenciaTipoDoc;
import gpw.persistencia.producto.PersistenciaProducto;
import gpw.persistencia.producto.PersistenciaTipoProd;
import gpw.persistencia.usuario.PersistenciaUsuario;
import gpw.types.Fecha;

/**
 * Session Bean implementation class GpWebStateless
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GpWebStateless implements GpWebStatelessRemote, GpWebStatelessLocal {

	private static Logger logger = Logger.getLogger(GpWebStateless.class);
	
	@Resource(lookup="java:jboss/datasources/dsGestPedWeb")
	private DataSource ds;
	@Resource 
	private EJBContext context;
	
	private static IPersUsuario interfaceUsuario;
	private static IPersPersona interfacePersona;
	private static IPersTipoDoc interfaceTipoDoc;
	private static IPersDepLoc interfaceDepLoc;
	private static IPersTipoProd interfaceTipoProd;
	private static IPersProducto interfaceProducto;
	private static IPersPedido interfacePedido;
	private static IPersPedidoLinea interfacePedidoLinea;
	
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
	private static IPersPedido getInterfacePedido() {
		if(interfacePedido == null) {
			interfacePedido = new PersistenciaPedido();
		}
		return interfacePedido;
	}
	private static IPersPedidoLinea getInterfacePedidoLinea() {
		if(interfacePedidoLinea == null) {
			interfacePedidoLinea = new PersistenciaPedidoLinea();
		}
		return interfacePedidoLinea;
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String loginUsuario(String nombreUsuario, String password) throws PersistenciaException {
    	logger.info("Se ingresa a loginUsuario para " + nombreUsuario);
    	String usuario = null;
    	try (Connection conn = ds.getConnection()) {
    		usuario = getInterfaceUsuario().loginUsuario(conn, nombreUsuario, password);
    	} catch (PersistenciaException | SQLException e) {
    		logger.fatal("Excepcion en GpWebStateless > loginUsuario: " + e.getMessage(), e);
    		throw new PersistenciaException(e);
    	} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > loginUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
    	return usuario;
    }
    
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioWeb obtenerUsuario(String nombreUsuario) throws PersistenciaException {
		logger.info("Se ingresa a obtenerUsuario para " + nombreUsuario);
		UsuarioWeb usuario = null;
		try (Connection conn = ds.getConnection()) {
			usuario = getInterfaceUsuario().obtenerUsuario(conn, nombreUsuario);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return usuario;
	}

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Integer guardarUsuario(UsuarioWeb usr) throws PersistenciaException {
		logger.info("Se ingresa a guardarUsuario para " + usr.getNomUsu());
		Integer resultado = null;
		try (Connection conn = ds.getConnection()) {
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
		} catch (Exception e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion GENERICA en GpWebStateless > guardarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Integer modificarUsuario(UsuarioWeb usr, Boolean modificaPasswd) throws PersistenciaException {
		logger.info("Se ingresa a modificarUsuario para " + usr.getNomUsu());
		Integer resultado = null;
		try (Connection conn = ds.getConnection()) {
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
		} catch (Exception e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion GENERICA en GpWebStateless > modificarUsuario: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
    
    

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Boolean checkExistPersona(Long idPersona) throws PersistenciaException {
    	logger.info("Se ingresa a checkExistePersona...");
    	Boolean checkExist = false;
    	try (Connection conn = ds.getConnection()) {
    		checkExist = getInterfacePersona().checkExistPersona(conn, idPersona);
    	} catch (PersistenciaException | SQLException e) {
    		logger.fatal("Excepcion en GpWebStateless > checkExistPersona: " + e.getMessage(), e);
    		throw new PersistenciaException(e);
    	} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > checkExistPersona: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
    	return checkExist;
	}

	/*****************************************************************************************************************************************************/
	/* PERSONA */
	/*****************************************************************************************************************************************************/
	
	//tipo doc
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public TipoDoc obtenerTipoDocPorId(Integer id) throws PersistenciaException {
		logger.info("Se ingresa a obtenerTipoDocPorId...");
		TipoDoc tipoDoc = null;
		try (Connection conn = ds.getConnection()) {
			tipoDoc = getInterfaceTipoDoc().obtenerTipoDocPorId(conn, id);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerTipoDocPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerTipoDocPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return tipoDoc;
	}
    
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<TipoDoc> obtenerListaTipoDoc() throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaTipoDoc...");
		List<TipoDoc> listaTd = null;
		try (Connection conn = ds.getConnection()) {
			listaTd = getInterfaceTipoDoc().obtenerListaTipoDoc(conn);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaTipoDoc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerListaTipoDoc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaTd;
	}
	
	//dep y loc
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Departamento> obtenerListaDepartamentos() throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaDepartamentos...");
		List<Departamento> listaDep = null;
		try (Connection conn = ds.getConnection()) {
			listaDep = getInterfaceDepLoc().obtenerListaDepartamentos(conn);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaDepartamentos: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerListaDepartamentos: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaDep;
	}

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Departamento obtenerDepartamentoPorId(Integer id) throws PersistenciaException {
		logger.info("Se ingresa a obtenerDepartamentoPorId...");
		Departamento dep = null;
		try (Connection conn = ds.getConnection()) {
			dep = getInterfaceDepLoc().obtenerDepartamentoPorId(conn, id);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerDepartamentoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerDepartamentoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return dep;
	}

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Localidad> obtenerListaLocPorDep(Integer idDep) throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaLocPorDep...");
		List<Localidad> listaLoc = null;
		try (Connection conn = ds.getConnection()) {
			listaLoc = getInterfaceDepLoc().obtenerListaLocPorDep(conn, idDep);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaLocPorDep: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerListaLocPorDep: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaLoc;
	}

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Localidad obtenerLocalidadPorId(Integer idLoc) throws PersistenciaException {
		logger.info("Se ingresa a obtenerLocalidadPorId...");
		Localidad loc = null;
		try (Connection conn = ds.getConnection()) {
			loc = getInterfaceDepLoc().obtenerLocalidadPorId(conn, idLoc);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerLocalidadPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerLocalidadPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return loc;
	}
	
	/*****************************************************************************************************************************************************/
	/* PRODUCTO */
	/*****************************************************************************************************************************************************/
	
	//tipo prod
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<TipoProd> obtenerListaTipoProd() throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaTipoProd...");
		List<TipoProd> listaTp = null;
		try (Connection conn = ds.getConnection()) {
			listaTp = getInterfaceTipoProd().obtenerListaTipoProd(conn);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerListaTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaTp;
	}
	
	//producto
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Producto obtenerProductoPorId(Integer id) throws PersistenciaException {
		logger.info("Se ingresa a obtenerProductoPorId...");
		Producto producto = null;
		try (Connection conn = ds.getConnection()) {
			producto = getInterfaceProducto().obtenerProductoPorId(conn, id);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerProductoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerProductoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return producto;
	}
    
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Producto> obtenerListaProductoPorTipo(Integer tipoProd) throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaProductoPorTipo...");
		List<Producto> listaProd = null;
		try (Connection conn = ds.getConnection()) {
			listaProd = getInterfaceProducto().obtenerListaProductoPorTipo(conn, tipoProd);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaProductoPorTipo: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerListaProductoPorTipo: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaProd;
	}
	
	
	/*****************************************************************************************************************************************************/
	/* PEDIDO */
	/*****************************************************************************************************************************************************/
	
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Pedido obtenerPedidoPorId(Long idPersona, Fecha fechaHora) throws PersistenciaException {
		logger.info("Se ingresa a obtenerPedidoPorId...");
		Pedido pedido = null;
		try (Connection conn = ds.getConnection()) {
			pedido = getInterfacePedido().obtenerPedidoPorId(conn, idPersona, fechaHora);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerPedidoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerPedidoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return pedido;
	}
	
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Pedido> obtenerListaPedido(EstadoPedido ep, Long idPersona, Fecha fechaDesde, Fecha fechaHasta)
			throws PersistenciaException {
		logger.info("Se ingresa a obtenerListaPedido...");
		List<Pedido> listaPedido = null;
		try (Connection conn = ds.getConnection()) {
			listaPedido = getInterfacePedido().obtenerListaPedido(conn, idPersona, ep, fechaDesde, fechaHasta);
		} catch (PersistenciaException | SQLException e) {
			logger.fatal("Excepcion en GpWebStateless > obtenerListaPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			logger.fatal("Excepcion GENERICA en GpWebStateless > obtenerListaPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaPedido;
	}
	
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Integer guardarPedido(Pedido pedido) throws PersistenciaException {
		logger.info("Se ingresa a guardarPedido");
		Integer resultado = null;
		try (Connection conn = ds.getConnection()) {
			if(pedido != null && pedido.getListaPedidoLinea() != null && 
					!pedido.getListaPedidoLinea().isEmpty()) {
				resultado = getInterfacePedido().guardarPedido(conn, pedido);
				getInterfacePedidoLinea().guardarListaPedidoLinea(conn, pedido.getListaPedidoLinea());
			}
		} catch (PersistenciaException | SQLException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en GpWebStateless > guardarPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion GENERICA en GpWebStateless > guardarPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Integer modificarPedido(Pedido pedido, List<PedidoLinea> lineaLineasNuevas) throws PersistenciaException {
		logger.info("Se ingresa a modificarPedido");
		Integer resultado = null;
		try (Connection conn = ds.getConnection()) {
			if(pedido != null && pedido.getListaPedidoLinea() != null && 
					!pedido.getListaPedidoLinea().isEmpty()) {
				resultado = getInterfacePedido().modificarPedido(conn, pedido);
				getInterfacePedidoLinea().eliminarListaPedidoLinea(conn, pedido);
				pedido.setListaPedidoLinea(lineaLineasNuevas);
				getInterfacePedidoLinea().guardarListaPedidoLinea(conn, pedido.getListaPedidoLinea());
			}
		} catch (PersistenciaException | SQLException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en GpWebStateless > modificarPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion GENERICA en GpWebStateless > modificarPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Integer modificarEstadoPedido(Pedido pedido) throws PersistenciaException {
		logger.info("Se ingresa a modificarEstadoPedido");
		Integer resultado = null;
		try (Connection conn = ds.getConnection()) {
			if(pedido != null) {
				resultado = getInterfacePedido().modificarEstadoPedido(conn, pedido);
			}
		} catch (PersistenciaException | SQLException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en GpWebStateless > modificarEstadoPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} catch (Exception e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion GENERICA en GpWebStateless > modificarEstadoPedido: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}
	
}
