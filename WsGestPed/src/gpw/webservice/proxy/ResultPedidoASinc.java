
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para ResultPedidoASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultPedidoASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errorServ" type="{http://localhost:8080}ErrorServicio" minOccurs="0"/&gt;
 *         &lt;element name="fechaHora" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="idPersona" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultPedidoASinc", propOrder = {
    "errorServ",
    "fechaHora",
    "idPersona"
})
public class ResultPedidoASinc {

    protected ErrorServicio errorServ;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHora;
    protected Long idPersona;

    /**
     * Obtiene el valor de la propiedad errorServ.
     * 
     * @return
     *     possible object is
     *     {@link ErrorServicio }
     *     
     */
    public ErrorServicio getErrorServ() {
        return errorServ;
    }

    /**
     * Define el valor de la propiedad errorServ.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorServicio }
     *     
     */
    public void setErrorServ(ErrorServicio value) {
        this.errorServ = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHora.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHora() {
        return fechaHora;
    }

    /**
     * Define el valor de la propiedad fechaHora.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHora(XMLGregorianCalendar value) {
        this.fechaHora = value;
    }

    /**
     * Obtiene el valor de la propiedad idPersona.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdPersona() {
        return idPersona;
    }

    /**
     * Define el valor de la propiedad idPersona.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdPersona(Long value) {
        this.idPersona = value;
    }

}
