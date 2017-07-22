package gpw.ws.datatypes.persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import gpw.types.ErrorServicio;

@XmlType(name = "ResultObtPersonasNoSinc")
public class ResultObtPersonasNoSinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ResultPersonaFisica> listaPersFisica;
	private List<ResultPersonaJuridica> listaPersJuridica;
	private List<ErrorServicio> erroresServ;
	
	
	public List<ResultPersonaFisica> getListaPersFisica() {
		return listaPersFisica;
	}
	public void setListaPersFisica(List<ResultPersonaFisica> listaPersFisica) {
		this.listaPersFisica = listaPersFisica;
	}
	public List<ResultPersonaJuridica> getListaPersJuridica() {
		return listaPersJuridica;
	}
	public void setListaPersJuridica(List<ResultPersonaJuridica> listaPersJuridica) {
		this.listaPersJuridica = listaPersJuridica;
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
