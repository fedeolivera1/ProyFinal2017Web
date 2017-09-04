package gpw.ws.datatypes.wsfunc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import gpw.ws.datatypes.errors.ErrorServicio;

@XmlType(name = "ResultServFuncional")
public class ResultServFuncional implements Serializable {

	private static final long serialVersionUID = 1L;
	private String resultado;
	private ErrorServicio error;
	
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public ErrorServicio getError() {
		return error;
	}
	public void setError(ErrorServicio error) {
		this.error = error;
	}

}
