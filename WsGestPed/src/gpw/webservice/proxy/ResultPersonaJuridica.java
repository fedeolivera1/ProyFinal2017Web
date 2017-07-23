
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResultPersonaJuridica complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultPersonaJuridica"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rut" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="bps" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="bse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="esProv" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="rutAnt" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="puerta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="solar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="manzana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="km" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="celular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaReg" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/&gt;
 *         &lt;element name="tipoPers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="localidad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sinc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ultAct" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultPersonaJuridica", propOrder = {
    "rut",
    "nombre",
    "razonSocial",
    "bps",
    "bse",
    "esProv",
    "rutAnt",
    "direccion",
    "puerta",
    "solar",
    "manzana",
    "km",
    "complemento",
    "telefono",
    "celular",
    "email",
    "fechaReg",
    "tipoPers",
    "localidad",
    "origen",
    "sinc",
    "ultAct"
})
public class ResultPersonaJuridica {

    protected Long rut;
    protected String nombre;
    protected String razonSocial;
    protected String bps;
    protected String bse;
    protected Boolean esProv;
    protected Long rutAnt;
    protected String direccion;
    protected String puerta;
    protected String solar;
    protected String manzana;
    protected Float km;
    protected String complemento;
    protected String telefono;
    protected String celular;
    protected String email;
    @XmlSchemaType(name = "anySimpleType")
    protected Object fechaReg;
    protected String tipoPers;
    protected Integer localidad;
    protected String origen;
    protected String sinc;
    @XmlSchemaType(name = "anySimpleType")
    protected Object ultAct;

    /**
     * Obtiene el valor de la propiedad rut.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRut() {
        return rut;
    }

    /**
     * Define el valor de la propiedad rut.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRut(Long value) {
        this.rut = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad bps.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBps() {
        return bps;
    }

    /**
     * Define el valor de la propiedad bps.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBps(String value) {
        this.bps = value;
    }

    /**
     * Obtiene el valor de la propiedad bse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBse() {
        return bse;
    }

    /**
     * Define el valor de la propiedad bse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBse(String value) {
        this.bse = value;
    }

    /**
     * Obtiene el valor de la propiedad esProv.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsProv() {
        return esProv;
    }

    /**
     * Define el valor de la propiedad esProv.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsProv(Boolean value) {
        this.esProv = value;
    }

    /**
     * Obtiene el valor de la propiedad rutAnt.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRutAnt() {
        return rutAnt;
    }

    /**
     * Define el valor de la propiedad rutAnt.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRutAnt(Long value) {
        this.rutAnt = value;
    }

    /**
     * Obtiene el valor de la propiedad direccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Define el valor de la propiedad direccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Obtiene el valor de la propiedad puerta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuerta() {
        return puerta;
    }

    /**
     * Define el valor de la propiedad puerta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuerta(String value) {
        this.puerta = value;
    }

    /**
     * Obtiene el valor de la propiedad solar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolar() {
        return solar;
    }

    /**
     * Define el valor de la propiedad solar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolar(String value) {
        this.solar = value;
    }

    /**
     * Obtiene el valor de la propiedad manzana.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManzana() {
        return manzana;
    }

    /**
     * Define el valor de la propiedad manzana.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManzana(String value) {
        this.manzana = value;
    }

    /**
     * Obtiene el valor de la propiedad km.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getKm() {
        return km;
    }

    /**
     * Define el valor de la propiedad km.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setKm(Float value) {
        this.km = value;
    }

    /**
     * Obtiene el valor de la propiedad complemento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Define el valor de la propiedad complemento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

    /**
     * Obtiene el valor de la propiedad telefono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Define el valor de la propiedad telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Obtiene el valor de la propiedad celular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCelular() {
        return celular;
    }

    /**
     * Define el valor de la propiedad celular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCelular(String value) {
        this.celular = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaReg.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFechaReg() {
        return fechaReg;
    }

    /**
     * Define el valor de la propiedad fechaReg.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFechaReg(Object value) {
        this.fechaReg = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoPers.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPers() {
        return tipoPers;
    }

    /**
     * Define el valor de la propiedad tipoPers.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPers(String value) {
        this.tipoPers = value;
    }

    /**
     * Obtiene el valor de la propiedad localidad.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLocalidad() {
        return localidad;
    }

    /**
     * Define el valor de la propiedad localidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLocalidad(Integer value) {
        this.localidad = value;
    }

    /**
     * Obtiene el valor de la propiedad origen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Define el valor de la propiedad origen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigen(String value) {
        this.origen = value;
    }

    /**
     * Obtiene el valor de la propiedad sinc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSinc() {
        return sinc;
    }

    /**
     * Define el valor de la propiedad sinc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSinc(String value) {
        this.sinc = value;
    }

    /**
     * Obtiene el valor de la propiedad ultAct.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getUltAct() {
        return ultAct;
    }

    /**
     * Define el valor de la propiedad ultAct.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setUltAct(Object value) {
        this.ultAct = value;
    }

}
