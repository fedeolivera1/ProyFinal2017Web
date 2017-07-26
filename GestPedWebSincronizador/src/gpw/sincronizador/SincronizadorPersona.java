package gpw.sincronizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import gpw.dominio.persona.Persona;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.dominio.util.EstadoSinc;
import gpw.ejb.SincronizadorStatelessLocal;
import gpw.exceptions.EjbException;
import gpw.lookup.LookUps;
import gpw.types.Fecha;
import gpw.ws.datatypes.errors.ErrorServicio;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamPersonaSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultPersonaFisica;
import gpw.ws.datatypes.persona.ResultPersonaJuridica;
import gpw.ws.datatypes.persona.ResultPersonaSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasSinc;
import gpw.ws.parsers.ParsePersona;
import gpw.ws.validators.ParamGenValidator;

public class SincronizadorPersona {

	Logger logger = Logger.getLogger(SincronizadorPersona.class);
	private static final int ERROR_SRV_GENERICO = -99;
	
	public String controlFuncionalidad() {
		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		String mensaje = null;
		try {
			Boolean res = sincSl.servicioFuncional();
			mensaje = res ? "Servicio funcional" : "Servicio NO DISPONIBLE";
//			sincSl.guardadoPrueba();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
	//WS personas 1
	public ResultObtPersonasNoSinc obtPersonasNoSinc(ParamObtPersonasNoSinc param) {
		ResultObtPersonasNoSinc result = new ResultObtPersonasNoSinc();
		try {
			if(ParamGenValidator.validarParam(param, result)) {
				SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
				Fecha fechaDesde = new Fecha(param.getFechaDesde(), Fecha.AMD);
				Fecha fechaHasta = new Fecha(param.getFechaHasta(), Fecha.AMD);
				List<Persona> listaPersonasNoSinc = sincSl.obtPersonasNoSinc(fechaDesde, fechaHasta);
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
			}
		} catch (EjbException e) {
			logger.fatal("Excepcion de EJB al obtPersonasNoSinc [SincronizadorPersona]: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ERROR_SRV_GENERICO, e.getMessage()));
		} catch (Exception e) {
			logger.fatal("Excepcion al obtPersonasNoSinc [SincronizadorPersona]: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ERROR_SRV_GENERICO, e.getMessage()));
		}
		return result;
	}
	
	//WS personas 2
	public ResultRecPersonasSinc recPersonasSinc(ParamRecPersonasSinc param) {
		ResultRecPersonasSinc result = new ResultRecPersonasSinc();
		try {
			if(ParamGenValidator.validarParam(param, result)) {
				List<Long> listaPersSincOk = null;
				SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
				listaPersSincOk = new ArrayList<>();
				for(ParamPersonaSinc paramPers : param.getListaPersSinc()) {
					logger.info("Se agrega nueva persona al sincronizador - idPersona: " + paramPers.getIdPersona());
//						if(paramPers.getEstadoSinc().equals(EstadoSinc.O)) {
						listaPersSincOk.add(paramPers.getIdPersona());
//						}
				}
				HashMap<Long, EstadoSinc> mapSinc = (HashMap<Long, EstadoSinc>) sincSl.recPersonasSinc(listaPersSincOk);
				for(Map.Entry<Long, EstadoSinc> entry : mapSinc.entrySet()) {
					Long idPersona = entry.getKey();
					EstadoSinc estSinc = entry.getValue();
					logger.info("Se recibe del sincronizador la persona - idPersona: " + idPersona + " - con estado: " + estSinc.getSinc());
					ResultPersonaSinc resultPs = new ResultPersonaSinc();
					resultPs.setIdPersona(idPersona);
					resultPs.setEstadoSinc(estSinc.getAsInt());
					result.getListaPersonaSinc().add(resultPs);
				}
			}
		} catch (EjbException e) {
			logger.fatal("Excepcion de EJB al obtPersonasNoSinc [SincronizadorPersona]: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ERROR_SRV_GENERICO, e.getMessage()));
		} catch (Exception e) {
			logger.fatal("Excepcion al recPersonasSinc [SincronizadorPersona]: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ERROR_SRV_GENERICO, e.getMessage()));
		}
		return result;
	}
	
}
