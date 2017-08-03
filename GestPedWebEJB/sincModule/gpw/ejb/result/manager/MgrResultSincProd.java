package gpw.ejb.result.manager;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.EstadoSinc;
import gpw.ejb.hlp.HlpSincProd;
import gpw.exceptions.EjbException;
import gpw.ws.datatypes.producto.ResultProductoASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;
import gpw.ws.datatypes.producto.ResultTipoProdASinc;
import gpw.ws.datatypes.producto.ResultUnidadASinc;


public class MgrResultSincProd {

	private static Logger logger = Logger.getLogger(MgrResultSincProd.class);
	
	public static ResultRecProductosASinc manageResult(HlpSincProd hsp) throws EjbException {
		logger.debug("Se ingresa a manejar el resultado para ResultRecProductosASinc...");
		ResultRecProductosASinc result = new ResultRecProductosASinc();
		try {
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
		} catch (Exception e) {
			logger.error("Excepcion generica al parsear datos en 'MgrResultSincProd' > manageResult: " + e.getMessage());
			throw new EjbException(e);
		}
		return result;
	}

}
