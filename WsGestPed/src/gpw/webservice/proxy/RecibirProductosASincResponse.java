
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirProductosASincResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirProductosASincResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultRecProductosASinc" type="{http://192.168.1.2:8080}ResultRecProductosASinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirProductosASincResponse", propOrder = {
    "resultRecProductosASinc"
})
public class RecibirProductosASincResponse {

    protected ResultRecProductosASinc resultRecProductosASinc;

    /**
     * Obtiene el valor de la propiedad resultRecProductosASinc.
     * 
     * @return
     *     possible object is
     *     {@link ResultRecProductosASinc }
     *     
     */
    public ResultRecProductosASinc getResultRecProductosASinc() {
        return resultRecProductosASinc;
    }

    /**
     * Define el valor de la propiedad resultRecProductosASinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultRecProductosASinc }
     *     
     */
    public void setResultRecProductosASinc(ResultRecProductosASinc value) {
        this.resultRecProductosASinc = value;
    }

}
