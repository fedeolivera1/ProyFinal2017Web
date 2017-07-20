package gpw.sincronizador;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.dominio.persona.Persona;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.ejb.SincronizadorStatelessLocal;
import gpw.lookup.LookUps;
import gpw.types.ErrorServicio;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultPersonaFisica;
import gpw.ws.datatypes.persona.ResultPersonaJuridica;
import gpw.ws.parsers.ParsePersona;

public class SincronizadorPersona {

	Logger logger = Logger.getLogger(SincronizadorPersona.class);
	
	
	public String controlFuncionalidad() {
		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		String mensaje = null;
		try {
			Boolean res = sincSl.servicioFuncional();
			mensaje = res ? "Servicio funcional" : "Servicio NO DISPONIBLE";
			sincSl.guardadoPrueba();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
	public ResultObtPersonasNoSinc obtPersonasNoSinc(ParamObtPersonasNoSinc param) {
		ResultObtPersonasNoSinc result = new ResultObtPersonasNoSinc();
		try {
			if(param != null) {
				SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
				List<Persona> listaPersonasNoSinc = sincSl.obtPersonasNoSinc(null, null);
				if(listaPersonasNoSinc != null && !listaPersonasNoSinc.isEmpty()) {
					List<ResultPersonaFisica> listaResultPf = new ArrayList<>();
					List<ResultPersonaJuridica> listaResultPj = new ArrayList<>();
					for(Persona pers : listaPersonasNoSinc) {
						if(pers instanceof PersonaFisica) {
							PersonaFisica pf = (PersonaFisica) pers;
							listaResultPf.add(ParsePersona.parsePersonaFisica(pf));
						} else if(pers instanceof PersonaJuridica) {
							PersonaJuridica pj = (PersonaJuridica) pers;
							listaResultPj.add(ParsePersona.parsePersonaJuridica(pj));
						}
					}
					result.setListaPersFisica(listaResultPf);
					result.setListaPersJuridica(listaResultPj);
				}
			} else {
				ErrorServicio error = new ErrorServicio();
				//FIXME ver codigos de error y mensajes para validaciones
				result.getErroresServ().add(error);
			}
		} catch (Exception e) {
			logger.fatal("Excepcion al obtPersonasNoSinc [SincronizadorPersona]: " + e.getMessage(), e);
		}
		return result;
	}
	
}
