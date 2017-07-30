package gpw.ws.datatypes.producto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ResultProductoASinc")
public class ResultProductoASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private Integer estadoSinc;
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getEstadoSinc() {
		return estadoSinc;
	}
	public void setEstadoSinc(Integer estadoSinc) {
		this.estadoSinc = estadoSinc;
	}
	
	

	
}
