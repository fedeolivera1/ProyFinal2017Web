package gpw.ejb.hlp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.EstadoSinc;

public class HlpSincProd {

	private Map<EstadoSinc, List<TipoProd>> mapTipoProd;
	private Map<EstadoSinc, List<Unidad>> mapUnidad;
	private Map<EstadoSinc, List<Producto>> mapProd;
	
	public Map<EstadoSinc, List<TipoProd>> getMapTipoProd() {
		if(mapTipoProd == null) {
			mapTipoProd = new HashMap<>();
		}
		return mapTipoProd;
	}
	public void setMapTipoProd(Map<EstadoSinc, List<TipoProd>> mapTipoProd) {
		this.mapTipoProd = mapTipoProd;
	}
	public Map<EstadoSinc, List<Unidad>> getMapUnidad() {
		if(mapUnidad == null) {
			mapUnidad = new HashMap<>();
		}
		return mapUnidad;
	}
	public void setMapUnidad(Map<EstadoSinc, List<Unidad>> mapUnidad) {
		this.mapUnidad = mapUnidad;
	}
	public Map<EstadoSinc, List<Producto>> getMapProd() {
		if(mapProd == null) {
			mapProd = new HashMap<>();
		}
		return mapProd;
	}
	public void setMapProd(Map<EstadoSinc, List<Producto>> mapProd) {
		this.mapProd = mapProd;
	}
	
}
