
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para servFuncionalResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="servFuncionalResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultServFuncional" type="{http://192.168.1.2:8080}ResultServFuncional" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servFuncionalResponse", propOrder = {
    "resultServFuncional"
})
public class ServFuncionalResponse {

    protected ResultServFuncional resultServFuncional;

    /**
     * Obtiene el valor de la propiedad resultServFuncional.
     * 
     * @return
     *     possible object is
     *     {@link ResultServFuncional }
     *     
     */
    public ResultServFuncional getResultServFuncional() {
        return resultServFuncional;
    }

    /**
     * Define el valor de la propiedad resultServFuncional.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultServFuncional }
     *     
     */
    public void setResultServFuncional(ResultServFuncional value) {
        this.resultServFuncional = value;
    }

}
