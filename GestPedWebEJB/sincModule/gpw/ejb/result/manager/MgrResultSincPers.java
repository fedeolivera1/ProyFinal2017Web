package gpw.ejb.result.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import gpw.dominio.persona.Persona;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.dominio.util.EstadoSinc;
import gpw.exceptions.EjbException;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultPersonaFisica;
import gpw.ws.datatypes.persona.ResultPersonaJuridica;
import gpw.ws.datatypes.persona.ResultPersonaSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasASinc;
import gpw.ws.parsers.ParserPersona;

public class MgrResultSincPers {

	private static Logger logger = Logger.getLogger(MgrResultSincPers.class);
	
	public static ResultObtPersonasNoSinc manageResultObtPersonasNoSinc(List<Persona> listaPersonasNoSinc) throws EjbException {
		logger.debug("Se ingresa a manejar el resultado para ResultObtPersonasNoSinc...");
		ResultObtPersonasNoSinc result = null;
		try {
			if(listaPersonasNoSinc != null && !listaPersonasNoSinc.isEmpty()) {
				result = new ResultObtPersonasNoSinc();
				List<ResultPersonaFisica> listaResultPf = new ArrayList<>();
				List<ResultPersonaJuridica> listaResultPj = new ArrayList<>();
				for(Persona pers : listaPersonasNoSinc) {
					if(pers instanceof PersonaFisica) {
						logger.info("Se parsea a result la persona fisica - idPersona: " + pers.getIdPersona());
						PersonaFisica pf = (PersonaFisica) pers;
						listaResultPf.add(ParserPersona.parsePersonaFisica(pf));
					} else if(pers instanceof PersonaJuridica) {
						logger.info("Se parsea a result la persona juridica - idPersona: " + pers.getIdPersona());
						PersonaJuridica pj = (PersonaJuridica) pers;
						listaResultPj.add(ParserPersona.parsePersonaJuridica(pj));
					}
				}
				result.setListaPersFisica(listaResultPf);
				result.setListaPersJuridica(listaResultPj);
			}
		} catch (Exception e) {
			logger.error("Excepcion generica al parsear datos en 'MgrResultSincPers' > manageResultObtPersonasNoSinc: " + e.getMessage());
			throw new EjbException(e);
		}
		return result;
		
	}
	
	public static ResultRecPersonasASinc manageResultRecPersonasSinc(Map<Long, EstadoSinc> mapPersSinc) throws EjbException {
		logger.debug("Se ingresa a manejar el resultado para ResultRecPersonasASinc...");
		ResultRecPersonasASinc result = null;
		try {
			if(mapPersSinc != null && !mapPersSinc.isEmpty()) {
				result = new ResultRecPersonasASinc();
				for(Map.Entry<Long, EstadoSinc> entry : mapPersSinc.entrySet()) {
					Long idPersona = entry.getKey();
					EstadoSinc estSinc = entry.getValue();
					logger.info("Se recibe del sincronizador la persona - idPersona: " + idPersona + " - con estado: " + estSinc.getSinc());
					ResultPersonaSinc resultPs = new ResultPersonaSinc();
					resultPs.setIdPersona(idPersona);
					resultPs.setEstadoSinc(estSinc.getAsInt());
					result.getListaPersonaSinc().add(resultPs);
				}
			}
		} catch (Exception e) {
			logger.error("Excepcion generica al parsear datos en 'MgrResultSincPers' > manageResultRecPersonasSinc: " + e.getMessage());
			throw new EjbException(e);
		}
		return result;
	}
}
