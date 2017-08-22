package gpw.ws.datatypes.pedido;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ResultPedidoLinea")
public class ResultPedidoLinea implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private Integer cantidad;
	private Double precioUnit;
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecioUnit() {
		return precioUnit;
	}
	public void setPrecioUnit(Double precioUnit) {
		this.precioUnit = precioUnit;
	}
}
