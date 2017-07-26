package gpw.ws.validators;

import java.util.ArrayList;
import java.util.List;

import gpw.ws.datatypes.errors.ErrorServicio;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasSinc;
import gpw.ws.datatypes.producto.ParamProductoASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;

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
	
	public static Boolean validarParam(ParamRecProductosASinc param, ResultRecProductosASinc result) {
		List<ErrorServicio> listaErrores = new ArrayList<>();
		if(param == null) {
			ErrorServicio error = new ErrorServicio(1, "El parametro no puede ser nulo.");
			listaErrores.add(error);
		} else {
			if(param.getListaProducto().isEmpty()) {
				ErrorServicio error = new ErrorServicio(1, "La lista de productos no puede ser vacia.");
				listaErrores.add(error);
			} else {
				for(ParamProductoASinc paramProd : param.getListaProducto()) {
					if(paramProd.getIdProducto() == null) {
						ErrorServicio error = new ErrorServicio(1, "El Id producto no puede ser nulo.");
						listaErrores.add(error);
					}
				}
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
