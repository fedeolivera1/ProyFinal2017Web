package gpw.ws.datatypes.producto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(name = "ParamProducto")
public class ParamProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	//TipoProd
	private Integer tipoProd;
	private String tipoProdDesc;
	//
	private String codigo;
	private String nombre;
	private String descripcion;
	private Float stockMin;
	//Unidad
	private Integer unidad;
	private String unidadDesc;
	//
	private Integer cantUnidad;
	//AplicaIva
	private String aplIva;
	private String aplIvaDesc;
	//
	private Double precio;
	//sinc (char)
	private String sinc;
	private XMLGregorianCalendar ultAct;
	
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
	public String getTipoProdDesc() {
		return tipoProdDesc;
	}
	public void setTipoProdDesc(String tipoProdDesc) {
		this.tipoProdDesc = tipoProdDesc;
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
	public Float getStockMin() {
		return stockMin;
	}
	public void setStockMin(Float stockMin) {
		this.stockMin = stockMin;
	}
	public Integer getUnidad() {
		return unidad;
	}
	public void setUnidad(Integer unidad) {
		this.unidad = unidad;
	}
	public String getUnidadDesc() {
		return unidadDesc;
	}
	public void setUnidadDesc(String unidadDesc) {
		this.unidadDesc = unidadDesc;
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
	public String getAplIvaDesc() {
		return aplIvaDesc;
	}
	public void setAplIvaDesc(String aplIvaDesc) {
		this.aplIvaDesc = aplIvaDesc;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getSinc() {
		return sinc;
	}
	public void setSinc(String sinc) {
		this.sinc = sinc;
	}
	public XMLGregorianCalendar getUltAct() {
		return ultAct;
	}
	public void setUltAct(XMLGregorianCalendar ultAct) {
		this.ultAct = ultAct;
	}
	
	
}
