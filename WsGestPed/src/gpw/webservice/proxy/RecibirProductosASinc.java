
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirProductosASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirProductosASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paramRecProductosASinc" type="{http://192.168.1.2:8080}ParamRecProductosASinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirProductosASinc", propOrder = {
    "paramRecProductosASinc"
})
public class RecibirProductosASinc {

    protected ParamRecProductosASinc paramRecProductosASinc;

    /**
     * Obtiene el valor de la propiedad paramRecProductosASinc.
     * 
     * @return
     *     possible object is
     *     {@link ParamRecProductosASinc }
     *     
     */
    public ParamRecProductosASinc getParamRecProductosASinc() {
        return paramRecProductosASinc;
    }

    /**
     * Define el valor de la propiedad paramRecProductosASinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamRecProductosASinc }
     *     
     */
    public void setParamRecProductosASinc(ParamRecProductosASinc value) {
        this.paramRecProductosASinc = value;
    }

}
