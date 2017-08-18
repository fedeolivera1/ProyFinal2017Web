package gpw.ws.parsers;

import org.apache.log4j.Logger;

import gpw.dominio.producto.AplicaIva;
import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.Estado;
import gpw.dominio.util.Sinc;
import gpw.exceptions.ParsersException;
import gpw.types.Fecha;
import gpw.ws.datatypes.producto.ParamProductoASinc;
import gpw.ws.datatypes.producto.ParamTipoProdASinc;
import gpw.ws.datatypes.producto.ParamUnidadASinc;

public class ParserProducto {

	private static Logger logger = Logger.getLogger(ParserProducto.class);
	
	public static TipoProd parseParamTipoProd(ParamTipoProdASinc param) throws ParsersException {
		TipoProd tp = null;
		try {
			if(param != null) {
				tp = new TipoProd();
				tp.setIdTipoProd(param.getIdTipoProd());
				tp.setDescripcion(param.getDescripcion());
				tp.setSinc(Sinc.getSincPorChar(param.getSinc().charAt(0)));
				tp.setEstado(Estado.getEstadoPorInt(param.getEstado()));
			}
		} catch (Exception e) {
			logger.error("Excepcion al parsear en ParserProducto: ", e);
			throw new ParsersException(e);
		}
		return tp;
	}
	
	public static Unidad parseParamUnidad(ParamUnidadASinc param) throws ParsersException {
		Unidad uni = null;
		try {
			if(param != null) {
				uni = new Unidad();
				uni.setIdUnidad(param.getIdUnidad());
				uni.setNombre(param.getNombre());
				uni.setSinc(Sinc.getSincPorChar(param.getSinc().charAt(0)));
				uni.setEstado(Estado.getEstadoPorInt(param.getEstado()));
			}
		} catch (Exception e) {
			logger.error("Excepcion al parsear en ParserProducto: ", e);
			throw new ParsersException(e);
		}
		return uni;
	}
	
	public static Producto parseParamProducto(ParamProductoASinc param) throws ParsersException {
		Producto prod = null;
		try {
			if(param != null) {
				prod = new Producto();
				prod.setIdProducto(param.getIdProducto());
				TipoProd tp = new TipoProd();
				//tipo prod
				tp.setIdTipoProd(param.getTipoProd());
				prod.setTipoProd(tp);
				//
				prod.setCodigo(param.getCodigo());
				prod.setNombre(param.getNombre());
				prod.setDescripcion(param.getDescripcion());
				prod.setStockMin(param.getStockMin());
				//unidad
				Unidad unidad = new Unidad();
				unidad.setIdUnidad(param.getUnidad());
				prod.setUnidad(unidad);
				//
				prod.setCantUnidad(param.getCantUnidad());
				prod.setAplIva(AplicaIva.getAplicaIvaPorChar(param.getAplIva().charAt(0)));
				prod.setPrecio(param.getPrecio());
				prod.setSinc(Sinc.getSincPorChar(param.getSinc().charAt(0)));
				prod.setUltAct(new Fecha(param.getUltAct(), Fecha.AMDHMS));
				prod.setEstadoProd(Estado.getEstadoPorInt(param.getEstadoProd()));
			}
		} catch (Exception e) {
			logger.error("Excepcion al parsear en ParserProducto: ", e);
			throw new ParsersException(e);
		}
		return prod;
	}
}
