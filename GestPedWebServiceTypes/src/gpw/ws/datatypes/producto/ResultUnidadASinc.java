package gpw.ws.datatypes.producto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ResultUnidadASinc")
public class ResultUnidadASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idUnidad;
	private Integer estadoSinc;
	
	public Integer getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}
	public Integer getEstadoSinc() {
		return estadoSinc;
	}
	public void setEstadoSinc(Integer estadoSinc) {
		this.estadoSinc = estadoSinc;
	}
}
