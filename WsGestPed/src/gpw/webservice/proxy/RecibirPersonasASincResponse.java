
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirPersonasASincResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirPersonasASincResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultRecPersonasASinc" type="{http://192.168.1.2:8080}ResultRecPersonasASinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirPersonasASincResponse", propOrder = {
    "resultRecPersonasASinc"
})
public class RecibirPersonasASincResponse {

    protected ResultRecPersonasASinc resultRecPersonasASinc;

    /**
     * Obtiene el valor de la propiedad resultRecPersonasASinc.
     * 
     * @return
     *     possible object is
     *     {@link ResultRecPersonasASinc }
     *     
     */
    public ResultRecPersonasASinc getResultRecPersonasASinc() {
        return resultRecPersonasASinc;
    }

    /**
     * Define el valor de la propiedad resultRecPersonasASinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultRecPersonasASinc }
     *     
     */
    public void setResultRecPersonasASinc(ResultRecPersonasASinc value) {
        this.resultRecPersonasASinc = value;
    }

}
