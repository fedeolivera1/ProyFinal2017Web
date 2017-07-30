package gpw.ws.datatypes.producto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParamRecProductosASinc")
public class ParamRecProductosASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ParamTipoProdASinc> listaTipoProd;
	private List<ParamUnidadASinc> listaUnidad;
	private List<ParamProductoASinc> listaProducto;
	
	public List<ParamTipoProdASinc> getListaTipoProd() {
		return listaTipoProd;
	}
	public void setListaTipoProd(List<ParamTipoProdASinc> listaTipoProd) {
		this.listaTipoProd = listaTipoProd;
	}
	public List<ParamUnidadASinc> getListaUnidad() {
		return listaUnidad;
	}
	public void setListaUnidad(List<ParamUnidadASinc> listaUnidad) {
		this.listaUnidad = listaUnidad;
	}
	public List<ParamProductoASinc> getListaProducto() {
		return listaProducto;
	}
	public void setListaProducto(List<ParamProductoASinc> listaProducto) {
		this.listaProducto = listaProducto;
	}
	
	
}
