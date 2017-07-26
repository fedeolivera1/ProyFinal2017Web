package gpw.ws.datatypes.producto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParamRecProductosASinc")
public class ParamRecProductosASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ParamProductoASinc> listaProducto;
	
	public List<ParamProductoASinc> getListaProducto() {
		return listaProducto;
	}
	public void setListaProducto(List<ParamProductoASinc> listaProducto) {
		this.listaProducto = listaProducto;
	}
	
	
}
