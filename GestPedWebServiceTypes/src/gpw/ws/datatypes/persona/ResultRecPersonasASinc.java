package gpw.ws.datatypes.persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import gpw.ws.datatypes.errors.ErrorServicio;

@XmlType(name = "ResultRecPersonasASinc")
public class ResultRecPersonasASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ResultPersonaSinc> listaPersonaSinc;
	private List<ErrorServicio> erroresServ;
	
	public List<ResultPersonaSinc> getListaPersonaSinc() {
		if(listaPersonaSinc == null) {
			listaPersonaSinc = new ArrayList<>();
		}
		return listaPersonaSinc;
	}
	public void setListaPersonaSinc(List<ResultPersonaSinc> listaPersonaSinc) {
		this.listaPersonaSinc = listaPersonaSinc;
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
