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
	//pf
	public PersonaJuridica obtenerPersJuridicaPorId(Connection conn, Long id) throws PersistenciaException;
	public List<PersonaJuridica> obtPersonaJuridicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	//gen
	public Integer actualizarPersonaSinc(Connection conn, Long idPersona) throws PersistenciaException;
	public Persona obtenerPersGenerico(Connection conn, Long idPersona) throws PersistenciaException;
	
}
