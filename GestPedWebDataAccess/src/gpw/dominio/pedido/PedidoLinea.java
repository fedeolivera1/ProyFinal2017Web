package gpw.dominio.pedido;

import java.io.Serializable;

import gpw.dominio.producto.Producto;

public class PedidoLinea implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private Producto producto;
	private Integer cantidad;
	private Double precioUnit;
	
	
	public PedidoLinea() {
		super();
	}
	
	public PedidoLinea(Pedido pedido) {
		super();
		this.pedido = pedido;
	}
	
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
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
