package gpw.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import gpw.dominio.persona.Persona;
import gpw.dominio.producto.Producto;
import gpw.dominio.producto.TipoProd;
import gpw.dominio.producto.Unidad;
import gpw.dominio.util.EstadoSinc;
import gpw.ejb.hlp.HlpSincProd;
import gpw.exceptions.EjbException;
import gpw.types.Fecha;

@Local
public interface SincronizadorStatelessLocal {
	
	public Boolean servicioFuncional() throws Exception;

	public List<Persona> obtPersonasNoSinc(Fecha fechaDesde, Fecha fechaHasta) throws Exception;
	public Map<Long, EstadoSinc> recPersonasSinc(List<Long> listaPersAConfSinc) throws Exception;
	
	public HlpSincProd recProductosSinc(List<TipoProd> listaTipoProd, List<Unidad> listaUnidad, List<Producto> listaProductosASinc) throws EjbException;
}
