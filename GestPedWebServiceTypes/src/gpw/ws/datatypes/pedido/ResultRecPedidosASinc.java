package gpw.ws.datatypes.pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import gpw.ws.datatypes.errors.ErrorServicio;

@XmlType(name = "ResultRecPedidosASinc")
public class ResultRecPedidosASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ResultPedidoConfirmado> listaPedidoConfirmado;
	private List<ResultPedidoASinc> listaPedidoSinc;
	private List<ErrorServicio> erroresServ;
	
	public List<ResultPedidoConfirmado> getListaPedidoConfirmado() {
		if(listaPedidoConfirmado == null) {
			listaPedidoConfirmado = new ArrayList<>();
		}
		return listaPedidoConfirmado;
	}
	public void setListaPedidoConfirmado(List<ResultPedidoConfirmado> listaPedidoConfirmado) {
		this.listaPedidoConfirmado = listaPedidoConfirmado;
	}
	public List<ResultPedidoASinc> getListaPedidoSinc() {
		if(listaPedidoSinc == null) {
			listaPedidoSinc = new ArrayList<>();
		}
		return listaPedidoSinc;
	}
	public void setListaPedidoSinc(List<ResultPedidoASinc> listaPedidoSinc) {
		this.listaPedidoSinc = listaPedidoSinc;
	}
	public List<ErrorServicio> getErroresServ() {
		if(erroresServ == null) {
			erroresServ = new ArrayList<>();
		}
		return erroresServ;
	}
	public void setErroresServ(List<ErrorServicio> erroresServ) {
		this.erroresServ = erroresServ;
	}
}
