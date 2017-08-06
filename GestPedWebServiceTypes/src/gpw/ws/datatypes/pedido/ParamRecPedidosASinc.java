package gpw.ws.datatypes.pedido;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParamRecPedidoSinc")
public class ParamRecPedidosASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ParamPedidoASinc> listaPedidosASinc;
	
	public List<ParamPedidoASinc> getListaPedidosASinc() {
		return listaPedidosASinc;
	}
	public void setListaPedidosASinc(List<ParamPedidoASinc> listaPedidosASinc) {
		this.listaPedidosASinc = listaPedidosASinc;
	}
	
}
