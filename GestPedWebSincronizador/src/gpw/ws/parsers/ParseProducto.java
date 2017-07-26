package gpw.ws.parsers;

import gpw.dominio.producto.AplicaIva;
import gpw.dominio.producto.EstadoProd;
import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.Sinc;
import gpw.types.Fecha;
import gpw.ws.datatypes.producto.ParamProductoASinc;

public class ParseProducto {

	public static Producto parseParamProducto(ParamProductoASinc param) {
		Producto prod = null;
		if(param != null) {
			prod = new Producto();
			prod.setIdProducto(param.getIdProducto());
			TipoProd tp = new TipoProd();
			//tipo prod
			tp.setIdTipoProd(param.getTipoProd());
			tp.setDescripcion(param.getTipoProdDesc());
			prod.setTipoProd(tp);
			//
			prod.setCodigo(param.getCodigo());
			prod.setNombre(param.getNombre());
			prod.setDescripcion(param.getDescripcion());
			prod.setStockMin(param.getStockMin());
			//unidad
			Unidad unidad = new Unidad();
			unidad.setIdUnidad(param.getUnidad());
			unidad.setNombre(param.getUnidadDesc());
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
