package gpw.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.dominio.persona.Persona;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.dominio.producto.EstadoProd;
import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.Estado;
import gpw.dominio.util.EstadoSinc;
import gpw.dominio.util.Sinc;
import gpw.ejb.hlp.HlpSincProd;
import gpw.ejb.result.parsers.ParserResultSincPedido;
import gpw.ejb.result.parsers.ParserResultSincPers;
import gpw.ejb.result.parsers.ParserResultSincProd;
import gpw.exceptions.EjbException;
import gpw.exceptions.ParsersException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.pedido.IPersPedido;
import gpw.interfaces.pedido.IPersPedidoLinea;
import gpw.interfaces.persona.IPersPersona;
import gpw.interfaces.producto.IPersProducto;
import gpw.interfaces.producto.IPersTipoProd;
import gpw.interfaces.producto.IPersUnidad;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.pedido.PersistenciaPedido;
import gpw.persistencia.pedido.PersistenciaPedidoLinea;
import gpw.persistencia.persona.PersistenciaPersona;
import gpw.persistencia.producto.PersistenciaProducto;
import gpw.persistencia.producto.PersistenciaTipoProd;
import gpw.persistencia.producto.PersistenciaUnidad;
import gpw.types.Fecha;
import gpw.ws.datatypes.errors.ErrorServicio;
import gpw.ws.datatypes.errors.ErroresServicioCod;
import gpw.ws.datatypes.pedido.ParamObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ParamRecPedidosASinc;
import gpw.ws.datatypes.pedido.ResultObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ResultPedidoASinc;
import gpw.ws.datatypes.pedido.ResultRecPedidosASinc;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamPersonaSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasASinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultPersonaSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasASinc;
import gpw.ws.datatypes.producto.ParamProductoASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ParamTipoProdASinc;
import gpw.ws.datatypes.producto.ParamUnidadASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;
import gpw.ws.parsers.ParserPedido;
import gpw.ws.parsers.ParserProducto;
import gpw.ws.validators.ParamGenValidator;

/**
 * Session Bean implementation class SincronizadorStateless
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SincronizadorStateless implements SincronizadorStatelessRemote, SincronizadorStatelessLocal {

	private static Logger logger = Logger.getLogger(SincronizadorStateless.class);
	
	@Resource(mappedName="java:jboss/datasources/dsGestPedWeb")
	private DataSource ds;
	private Connection conn;
	@Resource 
    private EJBContext context;
	
	private static IPersPersona interfacePersona;
	private static IPersProducto interfaceProducto;
	private static IPersTipoProd interfaceTipoProd;
	private static IPersUnidad interfaceUnidad;
	private static IPersPedido interfacePedido;
	private static IPersPedidoLinea interfacePedidoLinea;
	
	private static IPersPersona getInterfacePersona() {
		if(interfacePersona == null) {
			interfacePersona = new PersistenciaPersona();
		}
		return interfacePersona;
	}
	private static IPersProducto getInterfaceProducto() {
		if(interfaceProducto == null) {
			interfaceProducto = new PersistenciaProducto();
		}
		return interfaceProducto;
	}
	private static IPersTipoProd getInterfaceTipoProd() {
		if(interfaceTipoProd == null) {
			interfaceTipoProd = new PersistenciaTipoProd();
		}
		return interfaceTipoProd;
	}
	private static IPersUnidad getInterfaceUnidad() {
		if(interfaceUnidad == null) {
			interfaceUnidad = new PersistenciaUnidad();
		}
		return interfaceUnidad;
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
    public SincronizadorStateless() {
    }

	
	public String servicioFuncional() {
		String mensaje = null;
		ResultSet resultado = null;
		PreparedStatement sentencia = null;
		String consulta = "SELECT (1) FROM unidad";
		try {
			sentencia = ds.getConnection().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultado = sentencia.executeQuery();
			if(resultado.next()) {
				mensaje = "Servicio funcional";
			} else {
				mensaje = "Servicio NO DISPONIBLE";
				
			}
		} catch (SQLException e) {
			logger.fatal("Excepcion en EJB > obtPersonasNoSinc: " + e.getMessage(), e);
			mensaje = e.getMessage();
		} finally {
			Conector.closeConn(ds, sentencia, resultado);
		}
		return mensaje;
	}

	/***************************************************************************************************************************/
	/** SINC PERSONA **/
	/***************************************************************************************************************************/
	
	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ResultObtPersonasNoSinc obtPersonasNoSinc(ParamObtPersonasNoSinc param) {
		ResultObtPersonasNoSinc result = new ResultObtPersonasNoSinc();
		List<Persona> listaPersona = new ArrayList<>();
		try {
			
			if(ParamGenValidator.validarParam(param, result)) {
				Fecha fechaDesde = new Fecha(param.getFechaDesde(), Fecha.AMD);
				Fecha fechaHasta = new Fecha(param.getFechaHasta(), Fecha.AMD);
				//se obtiene conexion y se van a buscar listas de personas
				conn = ds.getConnection();
				List<PersonaFisica> listaPf = getInterfacePersona().obtPersonaFisicaNoSinc(conn, fechaDesde, fechaHasta);
				listaPersona.addAll((List<Persona>) (List<? extends Persona>) listaPf);
				List<PersonaJuridica> listaPj = getInterfacePersona().obtPersonaJuridicaNoSinc(conn, fechaDesde, fechaHasta);
				listaPersona.addAll((List<Persona>) (List<? extends Persona>) listaPj);
				result = ParserResultSincPers.parseResultObtPersonasNoSinc(listaPersona);
			}
			Conector.closeConn(ds, null, null);
		} catch (PersistenciaException | SQLException | EjbException e) {
			logger.fatal("Excepcion en EJB > obtPersonasNoSinc: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ErroresServicioCod.CODERR_EXCEP, e.getMessage()));
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ResultRecPersonasASinc recPersonasASinc(ParamRecPersonasASinc param) {
		ResultRecPersonasASinc result = new ResultRecPersonasASinc();
		Map<Long, EstadoSinc> mapResultados = null;
		try {
			
			if(ParamGenValidator.validarParam(param, result)) {
				List<Long> listaPersSincOk = null;
//				SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
				listaPersSincOk = new ArrayList<>();
				for(ParamPersonaSinc paramPers : param.getListaPersSinc()) {
					logger.info("Se agrega nueva persona al sincronizador - idPersona: " + paramPers.getIdPersona());
//						if(paramPers.getEstadoSinc().equals(EstadoSinc.O)) {
						listaPersSincOk.add(paramPers.getIdPersona());
//						}
				}
				conn = ds.getConnection();
				if(listaPersSincOk != null && !listaPersSincOk.isEmpty()) {
					mapResultados = new HashMap<>();
					for(Long idPers : listaPersSincOk) {
						Integer resultado = getInterfacePersona().actualizarPersonaSinc(conn, idPers);
						mapResultados.put(idPers, resultado > 0 ? EstadoSinc.O : EstadoSinc.E);
					}
				}
				for(Map.Entry<Long, EstadoSinc> entry : mapResultados.entrySet()) {
					Long idPersona = entry.getKey();
					EstadoSinc estSinc = entry.getValue();
					logger.info("Se recibe del sincronizador la persona - idPersona: " + idPersona + " - con estado: " + estSinc.getSinc());
					ResultPersonaSinc resultPs = new ResultPersonaSinc();
					resultPs.setIdPersona(idPersona);
					resultPs.setEstadoSinc(estSinc.getAsInt());
					result.getListaPersonaSinc().add(resultPs);
				}
			}
		} catch (PersistenciaException | SQLException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en EJB > obtPersonasNoSinc: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ErroresServicioCod.CODERR_EXCEP, e.getMessage()));
		}
		return result;
	}
	
	/***************************************************************************************************************************/
	/** SINC PRODUCTO **/
	/***************************************************************************************************************************/
	
	/**
	 * 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ResultRecProductosASinc recProductosASinc(ParamRecProductosASinc param) {
		ResultRecProductosASinc result = new ResultRecProductosASinc();
		try {
			if(ParamGenValidator.validarParam(param, result)) {
				List<TipoProd> listaTipoProd = new ArrayList<>();
				List<Unidad> listaUnidad = new ArrayList<>();
				List<Producto> listaProducto = new ArrayList<>();
				if(param.getListaTipoProd() != null && !param.getListaTipoProd().isEmpty()) {
					for(ParamTipoProdASinc paramTpas : param.getListaTipoProd()) {
						listaTipoProd.add(ParserProducto.parseParamTipoProd(paramTpas));
					}
				}
				if(param.getListaUnidad() != null && !param.getListaUnidad().isEmpty()) {
					for(ParamUnidadASinc paramUas : param.getListaUnidad()) {
						listaUnidad.add(ParserProducto.parseParamUnidad(paramUas));
					}
				}
				for(ParamProductoASinc paramProd : param.getListaProducto()) {
					listaProducto.add(ParserProducto.parseParamProducto(paramProd));
				}
				//
				HlpSincProd hsp = new HlpSincProd();
				conn = ds.getConnection();
				if(listaProducto != null && !listaProducto.isEmpty()) {
					if(listaTipoProd != null && !listaTipoProd.isEmpty()) {
						for(TipoProd tp : listaTipoProd) {
							Integer resQry = 0;
							tp.setSinc(Sinc.S);
							if(Estado.A.equals(tp.getEstado())) {
								if(getInterfaceTipoProd().checkExistTipoProd(conn, tp.getIdTipoProd())) {
									resQry = getInterfaceTipoProd().modificarTipoProd(conn, tp);
								} else {
									resQry = getInterfaceTipoProd().guardarTipoProd(conn, tp);
								}
							} else {
								resQry = getInterfaceTipoProd().eliminarTipoProd(conn, tp);
							}
							//chequeo si genero un movimiento en la base
							EstadoSinc estSinc = resQry > 0 ? EstadoSinc.O : EstadoSinc.E;
							//guardo en el mapa del helper dependiendo del estado
							if(hsp.getMapTipoProd().containsKey(estSinc)) {
								hsp.getMapTipoProd().get(estSinc).add(tp);
							} else {
								ArrayList<TipoProd> listaTpSinc = new ArrayList<>();
								listaTpSinc.add(tp);
								hsp.getMapTipoProd().put(estSinc, listaTpSinc);
							}
						}
					}
					if(listaUnidad != null && !listaUnidad.isEmpty()) {
						for(Unidad unidad : listaUnidad) {
							Integer resQry = 0;
							unidad.setSinc(Sinc.S);
							if(Estado.A.equals(unidad.getEstado())) {
								if(getInterfaceUnidad().checkExistUnidad(conn, unidad.getIdUnidad())) {
									resQry = getInterfaceUnidad().modificarUnidad(conn, unidad);
								} else {
									resQry = getInterfaceUnidad().guardarUnidad(conn, unidad);
								}
							} else {
								resQry = getInterfaceUnidad().eliminarUnidad(conn, unidad);
							}
							//chequeo si genero un movimiento en la base
							EstadoSinc estSinc = resQry > 0 ? EstadoSinc.O : EstadoSinc.E;
							//guardo en el mapa del helper dependiendo del estado
							if(hsp.getMapUnidad().containsKey(estSinc)) {
								hsp.getMapUnidad().get(estSinc).add(unidad);
							} else {
								ArrayList<Unidad> listaUniSinc = new ArrayList<>();
								listaUniSinc.add(unidad);
								hsp.getMapUnidad().put(estSinc, listaUniSinc);
							}
						}
					}
					for(Producto prod : listaProducto) {
						Integer resQry = 0;
						if(EstadoProd.A.equals(prod.getEstadoProd())) { // << chequeo si el producto tiene estado 'activo' (existente)
							if(controlarDatosProducto(prod)) {
								logger.debug("El control de los productos funciona ok, se procede con la sincronizacion web...");
								if(getInterfaceProducto().checkExistProducto(conn, prod.getIdProducto())) {
									logger.debug("El producto ya existe en base web, se actualiza...");
									prod.setSinc(Sinc.S);
									resQry = getInterfaceProducto().modificarProducto(conn, prod);
								} else {
									logger.debug("El producto no existe en base web, se agrega...");
									prod.setSinc(Sinc.S);
									resQry = getInterfaceProducto().guardarProducto(conn, prod);
								}
							} else {
								logger.warn("El producto queda como no sincronizado porque el metodo controlarDatosProducto falla.");
							}
						} else { // << caso producto con estado 'Eliminado', fue dado de baja
							prod.setSinc(Sinc.S);
							resQry = getInterfaceProducto().desactivarProducto(conn, prod);
						}
						//chequeo si genero un movimiento en la base
						EstadoSinc estSinc = resQry > 0 ? EstadoSinc.O : EstadoSinc.E;
						logger.info("Estado sincronizacion para el producto [" + prod.getCodigo() + "] es " + estSinc.getSinc());
						//guardo en el mapa del helper dependiendo del estado
						if(hsp.getMapProd().containsKey(estSinc)) {
							hsp.getMapProd().get(estSinc).add(prod);
						} else {
							ArrayList<Producto> listaProdSinc = new ArrayList<>();
							listaProdSinc.add(prod);
							hsp.getMapProd().put(estSinc, listaProdSinc);
						}
					}
				}
				result =  ParserResultSincProd.parseResultRecProductosASinc(hsp);
			}
		} catch (PersistenciaException | SQLException | EjbException | ParsersException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en EJB > recProductosSinc: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ErroresServicioCod.CODERR_EXCEP, e.getMessage()));
		}
		return result;
	}
	
	/***************************************************************************************************************************/
	/** SINC PEDIDO **/
	/***************************************************************************************************************************/
	
	/**
	 * 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ResultObtPedidosNoSinc obtPedidosNoSinc(ParamObtPedidosNoSinc param) {
		ResultObtPedidosNoSinc result = new ResultObtPedidosNoSinc();
		List<Pedido> listaPedido = new ArrayList<>();
		try {
			if(ParamGenValidator.validarParam(param, result)) {
				Fecha fechaDesde = new Fecha(param.getFechaDesde(), Fecha.AMD);
				Fecha fechaHasta = new Fecha(param.getFechaHasta(), Fecha.AMD);
				conn = ds.getConnection();
				listaPedido = getInterfacePedido().obtenerListaPedidoNoSinc(conn, fechaDesde, fechaHasta);
				if(listaPedido != null && !listaPedido.isEmpty()) {
					for(Pedido pedido : listaPedido) {
						List<PedidoLinea> listaPedidoLin = getInterfacePedidoLinea().obtenerListaPedidoLinea(conn, pedido);
						pedido.setListaPedidoLinea(listaPedidoLin);
					}
					result = ParserResultSincPedido.parseResultObtPedidosNoSinc(listaPedido);
				}
			}
		} catch (PersistenciaException | SQLException | EjbException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en EJB > obtPedidosNoSinc: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ErroresServicioCod.CODERR_EXCEP, e.getMessage()));
		}
		return result;
	}
	
	/**
	 * 
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ResultRecPedidosASinc recPedidosASinc(ParamRecPedidosASinc param) {
		ResultRecPedidosASinc result = new ResultRecPedidosASinc();
		try {
			if(ParamGenValidator.validarParam(param, result)) {
				conn = ds.getConnection();
				List<Pedido> listaPedidoASinc = ParserPedido.parsePedido(param);
				for(Pedido pedido : listaPedidoASinc) {
					ResultPedidoASinc resultPas = new ResultPedidoASinc();
					//obtengo persona generica ya que desde el servicio solamente contamos con el id de la misma
					Persona pers = getInterfacePersona().obtenerPersGenerico(conn, pedido.getIdPersTemp());
					pedido.setPersona(pers);
					if(Sinc.N.equals(pedido.getSinc()) && getInterfacePedido().checkExistPedido(conn, pedido)) {
						//bajo y subo la lista de lineas ya que debería haber cambios en ellas...
						getInterfacePedidoLinea().eliminarListaPedidoLinea(conn, pedido);
						getInterfacePedidoLinea().guardarListaPedidoLinea(conn, pedido.getListaPedidoLinea());
						//modifico datos del pedido
						pedido.setSinc(Sinc.S);
						Integer resQry = getInterfacePedido().modificarPedido(conn, pedido);
						if(resQry > 0) {
							resultPas.setIdPersona(pedido.getPersona().getIdPersona());
							resultPas.setFechaHora(pedido.getFechaHora().getAsXMLGregorianCalendar(Fecha.AMDHMS));
						} else {
							logger.error("ERROR: Se agrega error servicio por no registrarse actualizacion en un pedido NO SINC...");
							ErrorServicio error = new ErrorServicio(ErroresServicioCod.CODERR_SINC_UPDATE, "ERROR al sincronizar pedido: pers [" + pedido.getIdPersTemp() 
							+ "] - fecha-hora [" + pedido.getFechaHora().toString(Fecha.DMAHMS) + "]");
							resultPas.setErrorServ(error);
						}
					} else {
						ErrorServicio error = new ErrorServicio(0, "El pedido para la persona: " + pedido.getPersona().getIdPersona() + 
								" y la fecha-hora: " + pedido.getFechaHora().toString(Fecha.DMAHMS) + " no existe o ya ha sido sincronizado.");
						resultPas.setErrorServ(error);
					}
					result.getListaPedidoSinc().add(resultPas);
				}
			}
		} catch (PersistenciaException | SQLException | ParsersException e) {
			context.setRollbackOnly();
			logger.fatal("Excepcion en EJB > recPedidosASinc: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ErroresServicioCod.CODERR_EXCEP, e.getMessage()));
		}
		
		return result;
	}
	
	/***************************************************************************************************************************/
	/** UTILES **/
	
	/**
	 * metodo para chequear que antes de sincronizaar el producto, los datos de los cuales depende
	 * existan y estén correctos en base
	 * @param prod
	 * @return
	 * @throws EjbException
	 */
	private Boolean controlarDatosProducto(Producto prod) throws EjbException {
		Boolean resultado = true;
		try {
			resultado = getInterfaceTipoProd().checkExistTipoProd(conn, prod.getTipoProd().getIdTipoProd());
			resultado = getInterfaceUnidad().checkExistUnidad(conn, prod.getUnidad().getIdUnidad());
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en EJB > controlarDatosProducto: " + e.getMessage(), e);
			throw new EjbException(e);
		}
		return resultado;
	}
	
}
