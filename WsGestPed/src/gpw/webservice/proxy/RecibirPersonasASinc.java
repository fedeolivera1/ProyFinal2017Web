
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirPersonasASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirPersonasASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paramRecPersonasASinc" type="{http://192.168.1.2:8080}ParamRecPersonasASinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirPersonasASinc", propOrder = {
    "paramRecPersonasASinc"
})
public class RecibirPersonasASinc {

    protected ParamRecPersonasASinc paramRecPersonasASinc;

    /**
     * Obtiene el valor de la propiedad paramRecPersonasASinc.
     * 
     * @return
     *     possible object is
     *     {@link ParamRecPersonasASinc }
     *     
     */
    public ParamRecPersonasASinc getParamRecPersonasASinc() {
        return paramRecPersonasASinc;
    }

    /**
     * Define el valor de la propiedad paramRecPersonasASinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamRecPersonasASinc }
     *     
     */
    public void setParamRecPersonasASinc(ParamRecPersonasASinc value) {
        this.paramRecPersonasASinc = value;
    }

}
