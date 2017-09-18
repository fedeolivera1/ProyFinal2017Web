
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirPedidosASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirPedidosASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paramRecPedidosASinc" type="{http://192.168.1.2:8080}ParamRecPedidosASinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirPedidosASinc", propOrder = {
    "paramRecPedidosASinc"
})
public class RecibirPedidosASinc {

    protected ParamRecPedidosASinc paramRecPedidosASinc;

    /**
     * Obtiene el valor de la propiedad paramRecPedidosASinc.
     * 
     * @return
     *     possible object is
     *     {@link ParamRecPedidosASinc }
     *     
     */
    public ParamRecPedidosASinc getParamRecPedidosASinc() {
        return paramRecPedidosASinc;
    }

    /**
     * Define el valor de la propiedad paramRecPedidosASinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamRecPedidosASinc }
     *     
     */
    public void setParamRecPedidosASinc(ParamRecPedidosASinc value) {
        this.paramRecPedidosASinc = value;
    }

}
