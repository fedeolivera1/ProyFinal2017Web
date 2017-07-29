package gpw.ws.datatypes.producto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParamUnidadASinc")
public class ParamUnidadASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idUnidad;
	private String nombre;
	private String sinc;
	private Integer estado;
	
	public Integer getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSinc() {
		return sinc;
	}
	public void setSinc(String sinc) {
		this.sinc = sinc;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
