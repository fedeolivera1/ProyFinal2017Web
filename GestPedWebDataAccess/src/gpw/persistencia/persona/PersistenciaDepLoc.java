package gpw.persistencia.persona;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.db.constantes.CnstQryDepLoc;
import gpw.db.generic.GenSqlSelectType;
import gpw.dominio.persona.Departamento;
import gpw.dominio.persona.Localidad;
import gpw.exceptions.ConectorException;
import gpw.exceptions.PersistenciaException;
import gpw.interfaces.persona.IPersDepLoc;
import gpw.persistencia.conector.Conector;

public class PersistenciaDepLoc extends Conector implements IPersDepLoc, CnstQryDepLoc {

	private static final Logger logger = Logger.getLogger(PersistenciaDepLoc.class);
	private ResultSet rs;
	
	@Override
	public List<Departamento> obtenerListaDepartamentos(Connection conn) throws PersistenciaException {
		List<Departamento> listaDep = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_DEP);
			rs = (ResultSet) runGeneric(conn, genType);
			while(rs.next()) {
				Departamento departamento = new Departamento();
				departamento.setIdDepartamento(rs.getInt("id_dep"));
				departamento.setNombreDepartamento(rs.getString("nombre"));
				listaDep.add(departamento);
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaDepartamentos: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaDep;
	}
	
	@Override
	public Departamento obtenerDepartamentoPorId(Connection conn, Integer idDep) throws PersistenciaException {
		Departamento departamento = null;
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_DEP_XID);
			genType.setParam(idDep);
			ResultSet rs = (ResultSet) runGeneric(conn, genType);
			if(rs.next()) {
				departamento = new Departamento();
				departamento.setIdDepartamento(rs.getInt("id_dep"));
				departamento.setNombreDepartamento(rs.getString("nombre"));
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerDepartamentoPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return departamento;
	}

	@Override
	public List<Localidad> obtenerListaLocPorDep(Connection conn, Integer idDep) throws PersistenciaException {
		List<Localidad> listaLoc = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_LOC_XDEP);
			genType.setParam(idDep);
			ResultSet rs = (ResultSet) runGeneric(conn, genType);
			while(rs.next()) {
				Localidad localidad = new Localidad();
				localidad.setIdLocalidad(rs.getInt("id_loc"));
				localidad.setNombreLocalidad(rs.getString("nombre"));
				localidad.setDepartamento(obtenerDepartamentoPorId(conn, rs.getInt("id_dep")));
				listaLoc.add(localidad);
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerListaLocPorDep: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return listaLoc;
	}

	@Override
	public Localidad obtenerLocalidadPorId(Connection conn, Integer idLoc) throws PersistenciaException {
		Localidad localidad = null;
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_LOC_XID);
			genType.setParam(idLoc);
			ResultSet rs = (ResultSet) runGeneric(conn, genType);
			if(rs.next()) {
				localidad = new Localidad();
				localidad.setIdLocalidad(rs.getInt("id_loc"));
				localidad.setNombreLocalidad(rs.getString("nombre"));
				localidad.setDepartamento(obtenerDepartamentoPorId(conn, rs.getInt("id_dep")));
			}
		} catch (ConectorException | SQLException e) {
			logger.fatal("Excepcion al obtenerLocalidadPorId: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return localidad;
	}
	
}
