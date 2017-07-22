package gpw.ws.validators;

import java.util.ArrayList;
import java.util.List;

import gpw.types.ErrorServicio;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasSinc;

public class ParamGenValidator {

	public static Boolean validarParam(ParamObtPersonasNoSinc param, ResultObtPersonasNoSinc result) {
		List<ErrorServicio> listaErrores = new ArrayList<>();
		if(param == null) {
			ErrorServicio error = new ErrorServicio(1, "El parametro no puede ser nulo.");
			listaErrores.add(error);
		} else {
			if(param.getFechaDesde() == null) {
				ErrorServicio error = new ErrorServicio(1, "La fecha desde no puede ser nula.");
				listaErrores.add(error);
			}
			if(param.getFechaHasta() == null) {
				ErrorServicio error = new ErrorServicio(1, "La fecha hasta no puede ser nula.");
				listaErrores.add(error);
			}
		}
		if(listaErrores.isEmpty()) {
			return true;
		} else {
			result.setErroresServ(listaErrores);
			return false;
		}
	}
	
	public static Boolean validarParam(ParamRecPersonasSinc param, ResultRecPersonasSinc result) {
		List<ErrorServicio> listaErrores = new ArrayList<>();
		if(param == null) {
			ErrorServicio error = new ErrorServicio(1, "El parametro no puede ser nulo.");
			listaErrores.add(error);
		} else {
			if(param.getListaPersSinc() == null || param.getListaPersSinc().isEmpty()) {
				ErrorServicio error = new ErrorServicio(1, "La lista de personas no puede ser vacia.");
				listaErrores.add(error);
			}
		}
		if(listaErrores.isEmpty()) {
			return true;
		} else {
			result.setErroresServ(listaErrores);
			return false;
		}
	}
}
