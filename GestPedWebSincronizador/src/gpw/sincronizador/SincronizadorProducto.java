package gpw.sincronizador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.EstadoSinc;
import gpw.ejb.SincronizadorStatelessLocal;
import gpw.ejb.hlp.HlpSincProd;
import gpw.exceptions.EjbException;
import gpw.lookup.LookUps;
import gpw.ws.datatypes.errors.ErrorServicio;
import gpw.ws.datatypes.producto.ParamProductoASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ParamTipoProdASinc;
import gpw.ws.datatypes.producto.ParamUnidadASinc;
import gpw.ws.datatypes.producto.ResultProductoASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;
import gpw.ws.datatypes.producto.ResultTipoProdASinc;
import gpw.ws.datatypes.producto.ResultUnidadASinc;
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
				List<TipoProd> listaTipoProd = new ArrayList<>();
				List<Unidad> listaUnidad = new ArrayList<>();
				List<Producto> listaProducto = new ArrayList<>();
				if(param.getListaTipoProd() != null && !param.getListaTipoProd().isEmpty()) {
					for(ParamTipoProdASinc paramTpas : param.getListaTipoProd()) {
						listaTipoProd.add(ParseProducto.parseParamTipoProd(paramTpas));
					}
				}
				if(param.getListaUnidad() != null && !param.getListaUnidad().isEmpty()) {
					for(ParamUnidadASinc paramUas : param.getListaUnidad()) {
						listaUnidad.add(ParseProducto.parseParamUnidad(paramUas));
					}
				}
				for(ParamProductoASinc paramProd : param.getListaProducto()) {
					listaProducto.add(ParseProducto.parseParamProducto(paramProd));
				}
				//envio productos a sincronizar y recibo el tipo Help para obtener las listas sincronizadas
				HlpSincProd hsp = sincSl.recProductosSinc(listaTipoProd, listaUnidad, listaProducto);
				//
				//ver de meter todos estos datos al ejb
				if(hsp != null) {
					if(!hsp.getMapTipoProd().isEmpty()) {
						for(Map.Entry<EstadoSinc, List<TipoProd>> entry : hsp.getMapTipoProd().entrySet()) {
							EstadoSinc estSinc = entry.getKey();
							List<TipoProd> listaTp = entry.getValue();
							for(TipoProd tp : listaTp) {
								ResultTipoProdASinc resultTp = new ResultTipoProdASinc();
								resultTp.setEstadoSinc(estSinc.getAsInt());
								resultTp.setIdTipoProd(tp.getIdTipoProd());
								result.getListaTpSinc().add(resultTp);
							}
						}
					}
					if(!hsp.getMapUnidad().isEmpty()) {
						for(Map.Entry<EstadoSinc, List<Unidad>> entry : hsp.getMapUnidad().entrySet()) {
							EstadoSinc estSinc = entry.getKey();
							List<Unidad> listaUni = entry.getValue();
							for(Unidad uni : listaUni) {
								ResultUnidadASinc resultUni = new ResultUnidadASinc();
								resultUni.setEstadoSinc(estSinc.getAsInt());
								resultUni.setIdUnidad(uni.getIdUnidad());
								result.getListaUnidadSinc().add(resultUni);
							}
						}
					}
					if(!hsp.getMapProd().isEmpty()) {
						for(Map.Entry<EstadoSinc, List<Producto>> entry : hsp.getMapProd().entrySet()) {
							EstadoSinc estSinc = entry.getKey();
							List<Producto> listaProd = entry.getValue();
							for(Producto prod : listaProd) {
								ResultProductoASinc resultProd = new ResultProductoASinc();
								resultProd.setEstadoSinc(estSinc.getAsInt());
								resultProd.setIdProducto(prod.getIdProducto());
								result.getListaProdSinc().add(resultProd);
							}
						}
					}
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
