package gpw.persistencia.producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryTipoProd;
import gpw.db.generic.GenSqlExecType;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.producto.TipoProd;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.producto.IPersTipoProd;
import gpw.persistencia.conector.Conector;

public class PersistenciaTipoProd extends Conector implements IPersTipoProd, CnstQryTipoProd {

	private static final Logger logger = Logger.getLogger(PersistenciaTipoProd.class);
	private ResultSet rs;
	
	
	@Override
	public TipoProd obtenerTipoProdPorId(Connection conn, Integer id) throws PersistenciaException {
		TipoProd tipoProd = null;
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_TIPOPROD_X_ID);
			genType.setParam(id);
			rs = (ResultSet) runGeneric(conn, genType);
			if(rs.next()) {
				tipoProd = new TipoProd();
				tipoProd.setIdTipoProd(rs.getInt("id_tipo_prod"));
				tipoProd.setDescripcion(rs.getString("descripcion"));
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerTipoProdPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return tipoProd;
	}
	
	@Override
	public List<TipoProd> obtenerListaTipoProd(Connection conn) throws PersistenciaException {
		List<TipoProd> listaTipoProd = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_TIPOPROD);
			rs = (ResultSet) runGeneric(conn, genType);
			while(rs.next()) {
				TipoProd tipoProd = new TipoProd();
				tipoProd.setIdTipoProd(rs.getInt("id_tipo_prod"));
				tipoProd.setDescripcion(rs.getString("descripcion"));
				listaTipoProd.add(tipoProd);
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaTipoProd;
	}

	@Override
	public Integer guardarTipoProd(Connection conn, TipoProd tipoProd) throws PersistenciaException {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_TIPOPROD);
		genExec.setParam(tipoProd.getDescripcion());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al guardarTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer eliminarTipoProd(Connection conn, TipoProd tipoProd) throws PersistenciaException {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_DELETE_TIPOPROD);
		genExec.setParam(tipoProd.getIdTipoProd());
		try {
			resultado = (Integer) runGeneric(conn, genExec);
		} catch (ConectorException e) {
			logger.fatal("Excepcion al eliminarTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Boolean checkExistTipoProd(Connection conn, Integer id) throws PersistenciaException {
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_CHECK_EXIST_TIPOPROD);
			genType.setParam(id);
			rs = (ResultSet) runGeneric(conn, genType);
			if(rs.next()) {
				return true;
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaEmpresasPorTipo: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return false;
	}

}
