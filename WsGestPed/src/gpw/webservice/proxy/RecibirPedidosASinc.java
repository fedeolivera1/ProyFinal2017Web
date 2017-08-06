
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
 *         &lt;element name="paramRecPedidosASinc" type="{http://localhost:8080}ParamRecPedidoSinc" minOccurs="0"/&gt;
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

    protected ParamRecPedidoSinc paramRecPedidosASinc;

    /**
     * Obtiene el valor de la propiedad paramRecPedidosASinc.
     * 
     * @return
     *     possible object is
     *     {@link ParamRecPedidoSinc }
     *     
     */
    public ParamRecPedidoSinc getParamRecPedidosASinc() {
        return paramRecPedidosASinc;
    }

    /**
     * Define el valor de la propiedad paramRecPedidosASinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamRecPedidoSinc }
     *     
     */
    public void setParamRecPedidosASinc(ParamRecPedidoSinc value) {
        this.paramRecPedidosASinc = value;
    }

}
