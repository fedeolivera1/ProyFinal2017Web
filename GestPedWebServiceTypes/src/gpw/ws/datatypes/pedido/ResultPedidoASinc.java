package gpw.ws.datatypes.pedido;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import gpw.ws.datatypes.errors.ErrorServicio;

@XmlType(name = "ResultPedidoASinc")
public class ResultPedidoASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPersona;
	private XMLGregorianCalendar fechaHora;
	private ErrorServicio errorServ;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	@XmlSchemaType(name = "dateTime")
	public XMLGregorianCalendar getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(XMLGregorianCalendar fechaHora) {
		this.fechaHora = fechaHora;
	}
	public ErrorServicio getErrorServ() {
		return errorServ;
	}
	public void setErrorServ(ErrorServicio errorServ) {
		this.errorServ = errorServ;
	}
	
}
