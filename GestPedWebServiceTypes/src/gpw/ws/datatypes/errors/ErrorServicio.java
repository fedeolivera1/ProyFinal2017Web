package gpw.ws.datatypes.errors;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ErrorServicio")
public class ErrorServicio {

	private Integer codigo;
	private String descripcion;
	
	public ErrorServicio() {
		super();
	}
	public ErrorServicio(Integer codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
