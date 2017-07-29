package gpw.ws.parsers;

import gpw.dominio.producto.AplicaIva;
import gpw.dominio.producto.EstadoProd;
import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.Estado;
import gpw.dominio.util.Sinc;
import gpw.types.Fecha;
import gpw.ws.datatypes.producto.ParamProductoASinc;
import gpw.ws.datatypes.producto.ParamTipoProdASinc;
import gpw.ws.datatypes.producto.ParamUnidadASinc;

public class ParseProducto {

	public static TipoProd parseParamTipoProd(ParamTipoProdASinc param) {
		TipoProd tp = null;
		if(param != null) {
			tp = new TipoProd();
			tp.setIdTipoProd(param.getIdTipoProd());
			tp.setDescripcion(param.getDescripcion());
			tp.setSinc(Sinc.getSincPorChar(param.getSinc().charAt(0)));
			tp.setEstado(Estado.getEstadoProdPorInt(param.getEstado()));
		}
		return tp;
	}
	
	public static Unidad parseParamUnidad(ParamUnidadASinc param) {
		Unidad uni = null;
		if(param != null) {
			uni = new Unidad();
			uni.setIdUnidad(param.getIdUnidad());
			uni.setNombre(param.getNombre());
			uni.setSinc(Sinc.getSincPorChar(param.getSinc().charAt(0)));
			uni.setEstado(Estado.getEstadoProdPorInt(param.getEstado()));
		}
		return uni;
	}
	
	public static Producto parseParamProducto(ParamProductoASinc param) {
		Producto prod = null;
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
			prod.setEstadoProd(EstadoProd.getEstadoProdPorInt(param.getEstadoProd()));
		}
		return prod;
	}
}
