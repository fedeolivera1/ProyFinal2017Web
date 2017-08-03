package gpw.ws.datatypes.pedido;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(name = "ResultPedidoSinc")
public class ResultPedidoSinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPersona;
	private XMLGregorianCalendar fechaHora;
	private Integer estadoSinc;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public XMLGregorianCalendar getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(XMLGregorianCalendar fechaHora) {
		this.fechaHora = fechaHora;
	}
	public Integer getEstadoSinc() {
		return estadoSinc;
	}
	public void setEstadoSinc(Integer estadoSinc) {
		this.estadoSinc = estadoSinc;
	}
}
