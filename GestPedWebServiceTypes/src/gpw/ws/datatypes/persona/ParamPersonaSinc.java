package gpw.ws.datatypes.persona;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParamPersonaSinc")
public class ParamPersonaSinc implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPersona;
//	private Integer estadoSinc;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
//	public Integer getEstadoSinc() {
//		return estadoSinc;
//	}
//	public void setEstadoSinc(Integer estadoSinc) {
//		this.estadoSinc = estadoSinc;
//	}
}
