
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para ParamProducto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ParamProducto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aplIva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="aplIvaDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cantUnidad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idProducto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="sinc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stockMin" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="tipoProd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="tipoProdDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ultAct" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="unidadDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParamProducto", propOrder = {
    "aplIva",
    "aplIvaDesc",
    "cantUnidad",
    "codigo",
    "descripcion",
    "idProducto",
    "nombre",
    "precio",
    "sinc",
    "stockMin",
    "tipoProd",
    "tipoProdDesc",
    "ultAct",
    "unidad",
    "unidadDesc"
})
public class ParamProducto {

    protected String aplIva;
    protected String aplIvaDesc;
    protected Integer cantUnidad;
    protected String codigo;
    protected String descripcion;
    protected Integer idProducto;
    protected String nombre;
    protected Double precio;
    protected String sinc;
    protected Float stockMin;
    protected Integer tipoProd;
    protected String tipoProdDesc;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar ultAct;
    protected Integer unidad;
    protected String unidadDesc;

    /**
     * Obtiene el valor de la propiedad aplIva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplIva() {
        return aplIva;
    }

    /**
     * Define el valor de la propiedad aplIva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplIva(String value) {
        this.aplIva = value;
    }

    /**
     * Obtiene el valor de la propiedad aplIvaDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplIvaDesc() {
        return aplIvaDesc;
    }

    /**
     * Define el valor de la propiedad aplIvaDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplIvaDesc(String value) {
        this.aplIvaDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad cantUnidad.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantUnidad() {
        return cantUnidad;
    }

    /**
     * Define el valor de la propiedad cantUnidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantUnidad(Integer value) {
        this.cantUnidad = value;
    }

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad idProducto.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * Define el valor de la propiedad idProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdProducto(Integer value) {
        this.idProducto = value;
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
     * Obtiene el valor de la propiedad precio.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Define el valor de la propiedad precio.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrecio(Double value) {
        this.precio = value;
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
     * Obtiene el valor de la propiedad stockMin.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getStockMin() {
        return stockMin;
    }

    /**
     * Define el valor de la propiedad stockMin.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setStockMin(Float value) {
        this.stockMin = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoProd.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoProd() {
        return tipoProd;
    }

    /**
     * Define el valor de la propiedad tipoProd.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoProd(Integer value) {
        this.tipoProd = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoProdDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoProdDesc() {
        return tipoProdDesc;
    }

    /**
     * Define el valor de la propiedad tipoProdDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoProdDesc(String value) {
        this.tipoProdDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad ultAct.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUltAct() {
        return ultAct;
    }

    /**
     * Define el valor de la propiedad ultAct.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUltAct(XMLGregorianCalendar value) {
        this.ultAct = value;
    }

    /**
     * Obtiene el valor de la propiedad unidad.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnidad() {
        return unidad;
    }

    /**
     * Define el valor de la propiedad unidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnidad(Integer value) {
        this.unidad = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadDesc() {
        return unidadDesc;
    }

    /**
     * Define el valor de la propiedad unidadDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadDesc(String value) {
        this.unidadDesc = value;
    }

}
