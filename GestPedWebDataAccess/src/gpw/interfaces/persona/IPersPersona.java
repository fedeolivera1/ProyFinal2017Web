package gpw.interfaces.persona;

import java.sql.Connection;
import java.util.List;

import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public interface IPersPersona {

	public List<PersonaFisica> obtPersonaFisicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	public List<PersonaJuridica> obtPersonaJuridicaNoSinc(Connection conn, Fecha fechaDesde, Fecha fechaHasta) throws PersistenciaException;
	public Integer actualizarPersonaSinc(Connection conn, Long idPersona) throws PersistenciaException;
	
}
