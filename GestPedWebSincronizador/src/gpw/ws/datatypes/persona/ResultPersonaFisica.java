package gpw.ws.datatypes.persona;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

import gpw.dominio.persona.Localidad;
import gpw.types.Fecha;

public class ResultPersonaFisica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long documento;
	//TipoDoc
	private Integer tipoDoc;
	private String descTipoDoc;
	//
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private XMLGregorianCalendar fechaNac;
	private Character sexo;
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
	private Fecha fechaReg;
	//TipoPersona
	private Character tipoPers;
	private Localidad localidad;
	//Origen
	private Character origen;
	//Sinc
	private Character sinc;
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
	public String getDescTipoDoc() {
		return descTipoDoc;
	}
	public void setDescTipoDoc(String descTipoDoc) {
		this.descTipoDoc = descTipoDoc;
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
	public Character getSexo() {
		return sexo;
	}
	public void setSexo(Character sexo) {
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
	public Fecha getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(Fecha fechaReg) {
		this.fechaReg = fechaReg;
	}
	public Character getTipoPers() {
		return tipoPers;
	}
	public void setTipoPers(Character tipoPers) {
		this.tipoPers = tipoPers;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Character getOrigen() {
		return origen;
	}
	public void setOrigen(Character origen) {
		this.origen = origen;
	}
	public Character getSinc() {
		return sinc;
	}
	public void setSinc(Character sinc) {
		this.sinc = sinc;
	}
	public XMLGregorianCalendar getUltAct() {
		return ultAct;
	}
	public void setUltAct(XMLGregorianCalendar ultAct) {
		this.ultAct = ultAct;
	}
	
	

}
