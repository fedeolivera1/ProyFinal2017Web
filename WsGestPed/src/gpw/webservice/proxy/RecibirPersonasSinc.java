
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirPersonasSinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirPersonasSinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paramRecPersonasSinc" type="{http://localhost:8080}ParamRecPersonasSinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirPersonasSinc", propOrder = {
    "paramRecPersonasSinc"
})
public class RecibirPersonasSinc {

    protected ParamRecPersonasSinc paramRecPersonasSinc;

    /**
     * Obtiene el valor de la propiedad paramRecPersonasSinc.
     * 
     * @return
     *     possible object is
     *     {@link ParamRecPersonasSinc }
     *     
     */
    public ParamRecPersonasSinc getParamRecPersonasSinc() {
        return paramRecPersonasSinc;
    }

    /**
     * Define el valor de la propiedad paramRecPersonasSinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamRecPersonasSinc }
     *     
     */
    public void setParamRecPersonasSinc(ParamRecPersonasSinc value) {
        this.paramRecPersonasSinc = value;
    }

}
