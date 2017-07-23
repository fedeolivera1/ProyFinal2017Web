
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirPersonasSincResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirPersonasSincResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultRecPersonasSinc" type="{http://localhost:8080}ResultRecPersonasSinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirPersonasSincResponse", propOrder = {
    "resultRecPersonasSinc"
})
public class RecibirPersonasSincResponse {

    protected ResultRecPersonasSinc resultRecPersonasSinc;

    /**
     * Obtiene el valor de la propiedad resultRecPersonasSinc.
     * 
     * @return
     *     possible object is
     *     {@link ResultRecPersonasSinc }
     *     
     */
    public ResultRecPersonasSinc getResultRecPersonasSinc() {
        return resultRecPersonasSinc;
    }

    /**
     * Define el valor de la propiedad resultRecPersonasSinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultRecPersonasSinc }
     *     
     */
    public void setResultRecPersonasSinc(ResultRecPersonasSinc value) {
        this.resultRecPersonasSinc = value;
    }

}
