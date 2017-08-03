package gpw.ws.datatypes.pedido;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(name = "ParamObtPedidoSinc")
public class ParamObtPedidosNoSinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private XMLGregorianCalendar fechaDesde;
	private XMLGregorianCalendar fechaHasta;
	
	@XmlSchemaType(name = "date")
	public XMLGregorianCalendar getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(XMLGregorianCalendar fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	@XmlSchemaType(name = "date")
	public XMLGregorianCalendar getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(XMLGregorianCalendar fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
}
