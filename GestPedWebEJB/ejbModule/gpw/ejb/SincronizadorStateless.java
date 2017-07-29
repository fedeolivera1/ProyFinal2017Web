package gpw.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

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
import gpw.exceptions.EjbException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.persona.IPersPersona;
import gpw.interfaces.producto.IPersProducto;
import gpw.interfaces.producto.IPersTipoProd;
import gpw.interfaces.producto.IPersUnidad;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.persona.PersistenciaPersona;
import gpw.persistencia.producto.PersistenciaProducto;
import gpw.persistencia.producto.PersistenciaTipoProd;
import gpw.persistencia.producto.PersistenciaUnidad;
import gpw.types.Fecha;

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
	
	private static IPersPersona interfacePersona;
	private static IPersProducto interfaceProducto;
	private static IPersTipoProd interfaceTipoProd;
	private static IPersUnidad interfaceUnidad;
	
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
	
    /**
     * Default constructor. 
     */
    public SincronizadorStateless() {
    }

	
	public Boolean servicioFuncional() throws Exception {
		ResultSet resultado = null;
		PreparedStatement sentencia = null;
		String consulta = "select count(1) from unidad";
		try {
			sentencia = ds.getConnection().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultado = sentencia.executeQuery();
			if(resultado.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
//			Conector.rollbackConn(conn);
			logger.fatal("Excepcion en EJB > obtPersonasNoSinc: " + e.getMessage(), e);
		} finally {
			Conector.closeConn(ds, sentencia, resultado);
		}
		return false;
	}

	/*********************************************************************************************************************************************************************/
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Persona> obtPersonasNoSinc(Fecha fechaDesde, Fecha fechaHasta) throws EjbException {
		List<Persona> listaPersona = new ArrayList<>();
		try {
			conn = ds.getConnection();
			List<PersonaFisica> listaPf = getInterfacePersona().obtPersonaFisicaNoSinc(conn, fechaDesde, fechaHasta);
			listaPersona.addAll((List<Persona>) (List<? extends Persona>) listaPf);
			List<PersonaJuridica> listaPj = getInterfacePersona().obtPersonaJuridicaNoSinc(conn, fechaDesde, fechaHasta);
			listaPersona.addAll((List<Persona>) (List<? extends Persona>) listaPj);
			Conector.closeConn(ds, null, null);
		} catch (PersistenciaException | SQLException e) {
//			Conector.rollbackConn(conn);
			logger.fatal("Excepcion en EJB > obtPersonasNoSinc: " + e.getMessage(), e);
			throw new EjbException(e);
		}
		return listaPersona;
	}


	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Transactional(rollbackOn={Exception.class}, dontRollbackOn={SQLWarning.class})
	public Map<Long, EstadoSinc> recPersonasSinc(List<Long> listaPersAConfSinc) throws EjbException {
		Map<Long, EstadoSinc> mapResultados = null;
		try {
			conn = ds.getConnection();
			if(listaPersAConfSinc != null && !listaPersAConfSinc.isEmpty()) {
				mapResultados = new HashMap<>();
				for(Long idPers : listaPersAConfSinc) {
					Integer resultado = getInterfacePersona().actualizarPersonaSinc(conn, idPers);
					mapResultados.put(idPers, resultado > 0 ? EstadoSinc.O : EstadoSinc.E);
				}
			}
		} catch (PersistenciaException | SQLException e) {
//			Conector.rollbackConn(conn);
			logger.fatal("Excepcion en EJB > obtPersonasNoSinc: " + e.getMessage(), e);
			throw new EjbException(e);
		}
		return mapResultados;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Transactional(rollbackOn={Exception.class}, dontRollbackOn={SQLWarning.class})
	public HlpSincProd recProductosSinc(List<TipoProd> listaTipoProd, List<Unidad> listaUnidad, List<Producto> listaProductosASinc) throws EjbException {
		HlpSincProd hsp = new HlpSincProd();
		try {
			conn = ds.getConnection();
			if(listaProductosASinc != null && !listaProductosASinc.isEmpty()) {
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
				for(Producto prod : listaProductosASinc) {
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
		} catch (PersistenciaException | SQLException e) {
//			//Conector.rollbackConn(conn);
			logger.fatal("Excepcion en EJB > recProductosSinc: " + e.getMessage(), e);
			throw new EjbException(e);
		}
		return hsp;
	}
	
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
