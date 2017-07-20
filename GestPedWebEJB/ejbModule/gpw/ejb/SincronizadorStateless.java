package gpw.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import gpw.db.generic.GenSqlExecType;
import gpw.dominio.persona.Persona;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.exceptions.PersistenciaException;
import gpw.exceptions.EjbException;
import gpw.interfaces.persona.IPersPersona;
import gpw.persistencia.conector.Conector;
import gpw.persistencia.persona.PersistenciaPersona;
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
	
	private static IPersPersona getInterfacePersona() {
		if(interfacePersona == null) {
			interfacePersona = new PersistenciaPersona();
		}
		return interfacePersona;
	}
	
    /**
     * Default constructor. 
     */
    public SincronizadorStateless() {
    }

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void guardadoPrueba() throws Exception {
		conn = ds.getConnection();
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		String consulta = "insert into unidad (id_unidad, nombre) values (1, 'Gr')";
		GenSqlExecType genType = new GenSqlExecType(consulta);
		try {
//			sentencia = dataSource.getConnection().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			resultado = sentencia.executeQuery();
			Integer res = (Integer) Conector.runGeneric(conn, genType);
			System.out.println("La consulta inserto: " + res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conector.closeConn(ds, sentencia, rs);
		}
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
			Conector.rollbackConn();
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
			List<PersonaFisica> listaPf = getInterfacePersona().obtPersonaFisicaNoSinc(conn, null, null);
			listaPersona.addAll((List<Persona>) (List<? extends Persona>) listaPf);
			List<PersonaJuridica> listaPj = getInterfacePersona().obtPersonaJuridicaNoSinc(conn, null, null);
			listaPersona.addAll((List<Persona>) (List<? extends Persona>) listaPj);
			Conector.closeConn(ds, null, null);
		} catch (PersistenciaException | SQLException e) {
			Conector.rollbackConn();
			logger.fatal("Excepcion en EJB > obtPersonasNoSinc: " + e.getMessage(), e);
			throw new EjbException(e);
		}
		return listaPersona;
	}


	@Override
	public List<Long> recPersonasSinc(HashMap<Long, Boolean> mapResultados) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
