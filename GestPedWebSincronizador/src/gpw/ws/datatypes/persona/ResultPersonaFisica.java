package gpw.ws.datatypes.persona;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlType(name = "ResultPersonaFisica", propOrder = {
	    "documento", "tipoDoc", "tipoDocDesc", "apellido1", "apellido2",
	    "nombre1", "nombre2", "fechaNac", "sexo", "documentoAnt",
	    "direccion", "puerta", "solar", "manzana", "km",
	    "complemento", "telefono", "celular", "email", "fechaReg",
	    "tipoPers", "localidad", "origen", "sinc", "ultAct"})
public class ResultPersonaFisica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long documento;
	//TipoDoc
	private Integer tipoDoc;
	@XmlElement(name = "tipoDocDesc")
	private String tipoDocDesc;
	//
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private XMLGregorianCalendar fechaNac;
	//Sexo (char)
	private String sexo;
	private Long documentoAnt;
	//Persona
	private String direccion;
	private String puerta;
	private String solar;
	private String manzana;
	private Float km;
	private String complemento;
	private String telefono;
	private String celular;
	private String email;
	private XMLGregorianCalendar fechaReg;
	//TipoPersona (char)
	private String tipoPers;
	private Integer localidad;
	//Origen (char)
	private String origen;
	//Sinc (char)
	private String sinc;
	private XMLGregorianCalendar ultAct;
	
	
	public Long getDocumento() {
		return documento;
	}
	public void setDocumento(Long documento) {
		this.documento = documento;
	}
	public Integer getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(Integer tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String geTipoDocDesc() {
		return tipoDocDesc;
	}
	public void setTipoDocDesc(String tipoDocDesc) {
		this.tipoDocDesc = tipoDocDesc;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public XMLGregorianCalendar getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(XMLGregorianCalendar fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Long getDocumentoAnt() {
		return documentoAnt;
	}
	public void setDocumentoAnt(Long documentoAnt) {
		this.documentoAnt = documentoAnt;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPuerta() {
		return puerta;
	}
	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}
	public String getSolar() {
		return solar;
	}
	public void setSolar(String solar) {
		this.solar = solar;
	}
	public String getManzana() {
		return manzana;
	}
	public void setManzana(String manzana) {
		this.manzana = manzana;
	}
	public Float getKm() {
		return km;
	}
	public void setKm(Float km) {
		this.km = km;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public XMLGregorianCalendar getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(XMLGregorianCalendar fechaReg) {
		this.fechaReg = fechaReg;
	}
	public String getTipoPers() {
		return tipoPers;
	}
	public void setTipoPers(String tipoPers) {
		this.tipoPers = tipoPers;
	}
	public Integer getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Integer localidad) {
		this.localidad = localidad;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getSinc() {
		return sinc;
	}
	public void setSinc(String sinc) {
		this.sinc = sinc;
	}
	public XMLGregorianCalendar getUltAct() {
		return ultAct;
	}
	public void setUltAct(XMLGregorianCalendar ultAct) {
		this.ultAct = ultAct;
	}
	
	

}
