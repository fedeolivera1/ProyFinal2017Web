package gpw.ws.datatypes.pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import gpw.ws.datatypes.errors.ErrorServicio;

@XmlType(name = "ResultObtPedidosNoSinc")
public class ResultObtPedidosNoSinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ResultPedidoNoSinc> listaPedidoNoSinc;
	private List<ErrorServicio> erroresServ;
	
	public List<ResultPedidoNoSinc> getListaPedidoNoSinc() {
		if(listaPedidoNoSinc == null) {
			listaPedidoNoSinc = new ArrayList<>();
		}
		return listaPedidoNoSinc;
	}
	public void setListaPedidoNoSinc(List<ResultPedidoNoSinc> listaPedidoNoSinc) {
		this.listaPedidoNoSinc = listaPedidoNoSinc;
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
