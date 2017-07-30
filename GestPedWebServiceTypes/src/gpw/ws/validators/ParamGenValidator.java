package gpw.ws.validators;

import java.util.ArrayList;
import java.util.List;

import gpw.ws.datatypes.errors.ErrorServicio;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasASinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasASinc;
import gpw.ws.datatypes.producto.ParamProductoASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ParamTipoProdASinc;
import gpw.ws.datatypes.producto.ParamUnidadASinc;
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
	
	public static Boolean validarParam(ParamRecPersonasASinc param, ResultRecPersonasASinc result) {
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
			//control tipo prod
			if(!param.getListaTipoProd().isEmpty()) {
				for(ParamTipoProdASinc ptpas : param.getListaTipoProd()) {
					if(ptpas.getIdTipoProd() == null || ptpas.getDescripcion() == null ||
							ptpas.getSinc() == null || ptpas.getEstado() == null) {
						ErrorServicio error = new ErrorServicio(1, "Falta alguno/todos de los datos de TipoProd.");
						listaErrores.add(error);
					}
				}
			}
			//control unidad
			if(!param.getListaUnidad().isEmpty()) {
				for(ParamUnidadASinc puas : param.getListaUnidad()) {
					if(puas.getIdUnidad() == null || puas.getNombre() == null ||
							puas.getSinc() == null || puas.getEstado() == null) {
						ErrorServicio error = new ErrorServicio(1, "Falta alguno/todos de los datos de Unidad.");
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
