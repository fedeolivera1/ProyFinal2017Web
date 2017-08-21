package gpw.ws.datatypes.producto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(name = "ParamProductoASinc")
public class ParamProductoASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	//TipoProd
	private Integer tipoProd;
	//
	private String codigo;
	private String nombre;
	private String descripcion;
	//Unidad
	private Integer unidad;
	//
	private Integer cantUnidad;
	//AplicaIva
	private String aplIva;
	//
	private Double precioVta;
	//sinc (char)
	private String sinc;
	private XMLGregorianCalendar ultAct;
	private Integer estadoProd;
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getTipoProd() {
		return tipoProd;
	}
	public void setTipoProd(Integer tipoProd) {
		this.tipoProd = tipoProd;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getUnidad() {
		return unidad;
	}
	public void setUnidad(Integer unidad) {
		this.unidad = unidad;
	}
	public Integer getCantUnidad() {
		return cantUnidad;
	}
	public void setCantUnidad(Integer cantUnidad) {
		this.cantUnidad = cantUnidad;
	}
	public String getAplIva() {
		return aplIva;
	}
	public void setAplIva(String aplIva) {
		this.aplIva = aplIva;
	}
	public Double getPrecioVta() {
		return precioVta;
	}
	public void setPrecioVta(Double precioVta) {
		this.precioVta = precioVta;
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
	public Integer getEstadoProd() {
		return estadoProd;
	}
	public void setEstadoProd(Integer estadoProd) {
		this.estadoProd = estadoProd;
	}
	
	
}
