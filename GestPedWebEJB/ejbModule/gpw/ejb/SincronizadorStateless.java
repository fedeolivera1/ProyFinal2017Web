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
import gpw.dominio.util.EstadoSinc;
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
	public Map<Integer, EstadoSinc> recProductosSinc(List<Producto> listaProductosASinc) throws EjbException {
		Map<Integer, EstadoSinc> mapResultados = null;
		try {
			conn = ds.getConnection();
			if(listaProductosASinc != null && !listaProductosASinc.isEmpty()) {
				Integer resultado = 0;
				mapResultados = new HashMap<>();
				for(Producto prod : listaProductosASinc) {
					if(EstadoProd.A.equals(prod.getEstadoProd())) {
						controlarDatosProducto(prod);
					}
					if(getInterfaceProducto().checkExistProducto(conn, prod.getIdProducto())) {
						if(EstadoProd.A.equals(prod.getEstadoProd())) {
							resultado = getInterfaceProducto().modificarProducto(conn, prod);
						} else if(EstadoProd.E.equals(prod.getEstadoProd())) {
							resultado = getInterfaceProducto().desactivarProducto(conn, prod);
						}
					} else {
						resultado = getInterfaceProducto().guardarProducto(conn, prod);
					}
					mapResultados.put(prod.getIdProducto(), resultado > 0 ? EstadoSinc.O : EstadoSinc.E);
				}
			}
		} catch (PersistenciaException | SQLException e) {
//			//Conector.rollbackConn(conn);
			logger.fatal("Excepcion en EJB > recProductosSinc: " + e.getMessage(), e);
			throw new EjbException(e);
		}
		return mapResultados;
	}
	
	private void controlarDatosProducto(Producto prod) throws EjbException {
		try {
			if(getInterfaceTipoProd().checkExistTipoProd(conn, prod.getTipoProd().getIdTipoProd())) {
				getInterfaceTipoProd().guardarTipoProd(conn, prod.getTipoProd());
			}
			if(getInterfaceUnidad().checkExistUnidad(conn, prod.getUnidad().getIdUnidad())) {
				getInterfaceUnidad().guardarUnidad(conn, prod.getUnidad());
			} else {
				
			}
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en EJB > controlarDatosProducto: " + e.getMessage(), e);
			throw new EjbException(e);
		}
		
	}
	
}
