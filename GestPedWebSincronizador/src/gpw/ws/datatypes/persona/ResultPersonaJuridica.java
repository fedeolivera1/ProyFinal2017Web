package gpw.ws.datatypes.persona;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

import gpw.types.Fecha;

public class ResultPersonaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long rut;
	private String nombre;
	private String razonSocial;
	private String bps;
	private String bse;
	private Boolean esProv;
	private Long rutAnt;
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
	//Localidad
	private Integer localidad;
	//Origen
	private Character origen;
	//Sinc
	private Character sinc;
	private XMLGregorianCalendar ultAct;
	
	
	public Long getRut() {
		return rut;
	}
	public void setRut(Long rut) {
		this.rut = rut;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getBps() {
		return bps;
	}
	public void setBps(String bps) {
		this.bps = bps;
	}
	public String getBse() {
		return bse;
	}
	public void setBse(String bse) {
		this.bse = bse;
	}
	public Boolean getEsProv() {
		return esProv;
	}
	public void setEsProv(Boolean esProv) {
		this.esProv = esProv;
	}
	public Long getRutAnt() {
		return rutAnt;
	}
	public void setRutAnt(Long rutAnt) {
		this.rutAnt = rutAnt;
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
	public Integer getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Integer localidad) {
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
