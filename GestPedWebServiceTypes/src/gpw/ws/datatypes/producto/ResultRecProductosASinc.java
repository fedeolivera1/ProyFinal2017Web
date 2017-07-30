package gpw.ws.datatypes.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import gpw.ws.datatypes.errors.ErrorServicio;

@XmlType(name = "ResultRecProductosASinc")
public class ResultRecProductosASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ResultTipoProdASinc> listaTpSinc;
	private List<ResultUnidadASinc> listaUnidadSinc;
	private List<ResultProductoASinc> listaProdSinc;
	private List<ErrorServicio> erroresServ;
	
	public List<ResultTipoProdASinc> getListaTpSinc() {
		if(listaTpSinc == null) {
			listaTpSinc = new ArrayList<>();
		}
		return listaTpSinc;
	}
	public void setListaTpSinc(List<ResultTipoProdASinc> listaTpSinc) {
		this.listaTpSinc = listaTpSinc;
	}
	public List<ResultUnidadASinc> getListaUnidadSinc() {
		if(listaUnidadSinc == null) {
			listaUnidadSinc = new ArrayList<>();
		}
		return listaUnidadSinc;
	}
	public void setListaUnidadSinc(List<ResultUnidadASinc> listaUnidadSinc) {
		this.listaUnidadSinc = listaUnidadSinc;
	}
	public List<ResultProductoASinc> getListaProdSinc() {
		if(listaProdSinc == null) {
			listaProdSinc = new ArrayList<>();
		}
		return listaProdSinc;
	}
	public void setListaProdSinc(List<ResultProductoASinc> listaProdSinc) {
		this.listaProdSinc = listaProdSinc;
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
