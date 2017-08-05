package gpw.ws.datatypes.pedido;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(name = "ParamPedidoASinc")
public class ParamPedidoASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPersona;
	private XMLGregorianCalendar fechaHora;
	private String estado;
	private XMLGregorianCalendar fechaProg;
	private XMLGregorianCalendar horaProg;
	private String origen;
	private Double subTotal;
	private Double iva;
	private Double total;
	private String sinc;
	private XMLGregorianCalendar ultAct;
	private List<ParamPedidoLinea> listaPedidoLinea;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	@XmlSchemaType(name = "date")
	public XMLGregorianCalendar getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(XMLGregorianCalendar fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@XmlSchemaType(name = "date")
	public XMLGregorianCalendar getFechaProg() {
		return fechaProg;
	}
	public void setFechaProg(XMLGregorianCalendar fechaProg) {
		this.fechaProg = fechaProg;
	}
	@XmlSchemaType(name = "time")
	public XMLGregorianCalendar getHoraProg() {
		return horaProg;
	}
	public void setHoraProg(XMLGregorianCalendar horaProg) {
		this.horaProg = horaProg;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getSinc() {
		return sinc;
	}
	public void setSinc(String sinc) {
		this.sinc = sinc;
	}
	@XmlSchemaType(name = "date")
	public XMLGregorianCalendar getUltAct() {
		return ultAct;
	}
	public void setUltAct(XMLGregorianCalendar ultAct) {
		this.ultAct = ultAct;
	}
	public List<ParamPedidoLinea> getListaPedidoLinea() {
		return listaPedidoLinea;
	}
	public void setListaPedidoLinea(List<ParamPedidoLinea> listaPedidoLinea) {
		this.listaPedidoLinea = listaPedidoLinea;
	}
	
}
