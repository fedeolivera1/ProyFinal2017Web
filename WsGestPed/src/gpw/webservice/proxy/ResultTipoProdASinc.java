
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResultTipoProdASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultTipoProdASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="estadoSinc" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="idTipoProd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultTipoProdASinc", propOrder = {
    "estadoSinc",
    "idTipoProd"
})
public class ResultTipoProdASinc {

    protected Integer estadoSinc;
    protected Integer idTipoProd;

    /**
     * Obtiene el valor de la propiedad estadoSinc.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstadoSinc() {
        return estadoSinc;
    }

    /**
     * Define el valor de la propiedad estadoSinc.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstadoSinc(Integer value) {
        this.estadoSinc = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoProd.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdTipoProd() {
        return idTipoProd;
    }

    /**
     * Define el valor de la propiedad idTipoProd.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdTipoProd(Integer value) {
        this.idTipoProd = value;
    }

}
