package gpw.ejb;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import gpw.dominio.persona.Persona;
import gpw.types.Fecha;

@Local
public interface SincronizadorStatelessLocal {
	
	public Boolean servicioFuncional() throws Exception;
	public void guardadoPrueba() throws Exception;

	public List<Persona> obtPersonasNoSinc(Fecha fechaDesde, Fecha fechaHasta) throws Exception;
	public List<Long> recPersonasSinc(HashMap<Long, Boolean> mapResultados) throws Exception;
}
