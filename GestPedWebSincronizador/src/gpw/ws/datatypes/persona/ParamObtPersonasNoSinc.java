package gpw.ws.datatypes.persona;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(name = "ParamObtPersonasNoSinc")
public class ParamObtPersonasNoSinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private XMLGregorianCalendar fechaDesde;
	private XMLGregorianCalendar fechaHasta;
	
	
	public XMLGregorianCalendar getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(XMLGregorianCalendar fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public XMLGregorianCalendar getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(XMLGregorianCalendar fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	
}