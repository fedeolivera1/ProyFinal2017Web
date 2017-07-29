package gpw.ws.datatypes.producto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParamTipoProdASinc")
public class ParamTipoProdASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTipoProd;
	private String descripcion;
	private String sinc;
	private Integer estado;
	
	public Integer getIdTipoProd() {
		return idTipoProd;
	}
	public void setIdTipoProd(Integer idTipoProd) {
		this.idTipoProd = idTipoProd;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
