package gpw.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import gpw.db.generic.GenSqlExecType;
import gpw.persistencia.conector.Conector;

/**
 * Session Bean implementation class SincronizadorStateless
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SincronizadorStateless implements SincronizadorStatelessRemote, SincronizadorStatelessLocal {

	@Resource(mappedName="java:jboss/datasources/dsGestPedWeb")
	private DataSource ds;
	private Connection conn;
	
	
	
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
			e.printStackTrace();
		} finally {
			Conector.closeConn(ds, sentencia, resultado);
		}
		return false;
	}
	
}
