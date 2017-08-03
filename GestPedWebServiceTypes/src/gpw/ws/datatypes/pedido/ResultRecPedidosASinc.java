package gpw.ws.datatypes.pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import gpw.ws.datatypes.errors.ErrorServicio;

@XmlType(name = "ResultRecPedidoSinc")
public class ResultRecPedidosASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ResultPedidoSinc> listaPedidoSinc;
	private List<ErrorServicio> erroresServ;
	
	public List<ResultPedidoSinc> getListaPedidoSinc() {
		return listaPedidoSinc;
	}
	public void setListaPedidoSinc(List<ResultPedidoSinc> listaPedidoSinc) {
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
