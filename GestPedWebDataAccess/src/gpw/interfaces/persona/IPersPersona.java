package gpw.interfaces.persona;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.persona.Persona;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public interface IPersPersona {

	//pf
	public PersonaFisica obtenerPersFisicaPorId(Connection conn, Long id) throws PersistenciaException;
	public List<PersonaFisica> obtPersonaFisicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	public Integer guardarPersFisica(Connection conn, PersonaFisica pf) throws PersistenciaException;
	public Integer modificarPersFisica(Connection conn, PersonaFisica pf) throws PersistenciaException;
	//pj
	public PersonaJuridica obtenerPersJuridicaPorId(Connection conn, Long id) throws PersistenciaException;
	public List<PersonaJuridica> obtPersonaJuridicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	public Integer guardarPersJuridica(Connection conn, PersonaJuridica pj) throws PersistenciaException;
	public Integer modificarPersJuridica(Connection conn, PersonaJuridica pj) throws PersistenciaException;
	//gen
	public Integer actualizarPersonaSinc(Connection conn, Long idPersona) throws PersistenciaException;
	public Persona obtenerPersGenerico(Connection conn, Long idPersona) throws PersistenciaException;
	
}
