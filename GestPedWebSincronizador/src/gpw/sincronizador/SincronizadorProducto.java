package gpw.sincronizador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import gpw.dominio.producto.Producto;
import gpw.dominio.util.EstadoSinc;
import gpw.ejb.SincronizadorStatelessLocal;
import gpw.exceptions.EjbException;
import gpw.lookup.LookUps;
import gpw.ws.datatypes.errors.ErrorServicio;
import gpw.ws.datatypes.producto.ParamProductoASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ResultProductoASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;
import gpw.ws.parsers.ParseProducto;
import gpw.ws.validators.ParamGenValidator;

public class SincronizadorProducto {

	Logger logger = Logger.getLogger(SincronizadorProducto.class);
	private static final int ERROR_SRV_GENERICO = -99;
	
	public ResultRecProductosASinc recProductosASinc(ParamRecProductosASinc param) {
		ResultRecProductosASinc result = new ResultRecProductosASinc();
		try {
			if(ParamGenValidator.validarParam(param, result)) {
				SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
				List<Producto> listaProducto = new ArrayList<>();
				for(ParamProductoASinc paramProd : param.getListaProducto()) {
					listaProducto.add(ParseProducto.parseParamProducto(paramProd));
				}
//				Map<Integer, EstadoSinc> hmResultProd = sincSl.recProductosSinc(listaProducto);
//				for(Map.Entry<Long, Boolean> entry : mapResultados.entrySet()) {
//					ResultProductoASinc resultPas = new ResultProductoASinc();
//					hmResultProd.put(key, value)
//				}
			}
//		} catch (EjbException e) {
//			logger.fatal("Excepcion de EJB al obtPersonasNoSinc [SincronizadorPersona]: " + e.getMessage(), e);
//			result.getErroresServ().add(new ErrorServicio(ERROR_SRV_GENERICO, e.getMessage()));
		} catch (Exception e) {
			logger.fatal("Excepcion al recPersonasSinc [SincronizadorPersona]: " + e.getMessage(), e);
			result.getErroresServ().add(new ErrorServicio(ERROR_SRV_GENERICO, e.getMessage()));
		}
		return null;
	}
}
