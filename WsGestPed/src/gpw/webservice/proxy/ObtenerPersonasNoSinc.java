
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerPersonasNoSinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerPersonasNoSinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paramObtPersonasNoSinc" type="{http://localhost:8080}ParamObtPersonasNoSinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerPersonasNoSinc", propOrder = {
    "paramObtPersonasNoSinc"
})
public class ObtenerPersonasNoSinc {

    protected ParamObtPersonasNoSinc paramObtPersonasNoSinc;

    /**
     * Obtiene el valor de la propiedad paramObtPersonasNoSinc.
     * 
     * @return
     *     possible object is
     *     {@link ParamObtPersonasNoSinc }
     *     
     */
    public ParamObtPersonasNoSinc getParamObtPersonasNoSinc() {
        return paramObtPersonasNoSinc;
    }

    /**
     * Define el valor de la propiedad paramObtPersonasNoSinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamObtPersonasNoSinc }
     *     
     */
    public void setParamObtPersonasNoSinc(ParamObtPersonasNoSinc value) {
        this.paramObtPersonasNoSinc = value;
    }

}
