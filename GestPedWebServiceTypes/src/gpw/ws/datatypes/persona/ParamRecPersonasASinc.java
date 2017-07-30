package gpw.ws.datatypes.persona;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParamRecPersonasASinc")
public class ParamRecPersonasASinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ParamPersonaSinc> listaPersSinc;
	
	public List<ParamPersonaSinc> getListaPersSinc() {
		return listaPersSinc;
	}
	public void setListaPersSinc(List<ParamPersonaSinc> listaPersSinc) {
		this.listaPersSinc = listaPersSinc;
	}
	
}
