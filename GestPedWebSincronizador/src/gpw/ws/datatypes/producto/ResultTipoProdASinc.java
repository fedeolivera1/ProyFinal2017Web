package gpw.ws.datatypes.producto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ResultTipoProdASinc")
public class ResultTipoProdASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTipoProd;
	private Integer estadoSinc;
	
	public Integer getIdTipoProd() {
		return idTipoProd;
	}
	public void setIdTipoProd(Integer idTipoProd) {
		this.idTipoProd = idTipoProd;
	}
	public Integer getEstadoSinc() {
		return estadoSinc;
	}
	public void setEstadoSinc(Integer estadoSinc) {
		this.estadoSinc = estadoSinc;
	}

}
