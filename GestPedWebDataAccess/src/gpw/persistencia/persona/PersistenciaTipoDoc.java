package gpw.persistencia.persona;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryTipoDoc;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.persona.TipoDoc;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.persona.IPersTipoDoc;
import gpw.persistencia.conector.Conector;

public class PersistenciaTipoDoc extends Conector implements IPersTipoDoc, CnstQryTipoDoc {

	private static final Logger logger = Logger.getLogger(PersistenciaTipoDoc.class);
	private ResultSet rs;
	
	@Override
	public TipoDoc obtenerTipoDocPorId(Connection conn, Integer id) throws PersistenciaException {
		TipoDoc tipoDoc = null;
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_TIPODOC_XID);
			genSel.setParam(id);
			rs = (ResultSet) runGeneric(conn, genSel);
			if(rs.next()) {
				tipoDoc = new TipoDoc();
				tipoDoc.setIdTipoDoc(rs.getInt("id_tipo_doc"));
				tipoDoc.setNombre(rs.getString("nombre"));
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaTipoDoc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return tipoDoc;
	}

	@Override
	public List<TipoDoc> obtenerListaTipoDoc(Connection conn) throws PersistenciaException {
		List<TipoDoc> listaTipoDoc = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_TIPODOC);
			rs = (ResultSet) runGeneric(conn, genType);
			while(rs.next()) {
				TipoDoc tipoDoc = new TipoDoc();
				tipoDoc.setIdTipoDoc(rs.getInt("id_tipo_doc"));
				tipoDoc.setNombre(rs.getString("nombre"));
				listaTipoDoc.add(tipoDoc);
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaTipoDoc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaTipoDoc;
	}
	

}
