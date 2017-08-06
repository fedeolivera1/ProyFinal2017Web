
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerPedidosNoSinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerPedidosNoSinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paramObtPedidosNoSinc" type="{http://localhost:8080}ParamObtPedidoSinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerPedidosNoSinc", propOrder = {
    "paramObtPedidosNoSinc"
})
public class ObtenerPedidosNoSinc {

    protected ParamObtPedidoSinc paramObtPedidosNoSinc;

    /**
     * Obtiene el valor de la propiedad paramObtPedidosNoSinc.
     * 
     * @return
     *     possible object is
     *     {@link ParamObtPedidoSinc }
     *     
     */
    public ParamObtPedidoSinc getParamObtPedidosNoSinc() {
        return paramObtPedidosNoSinc;
    }

    /**
     * Define el valor de la propiedad paramObtPedidosNoSinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamObtPedidoSinc }
     *     
     */
    public void setParamObtPedidosNoSinc(ParamObtPedidoSinc value) {
        this.paramObtPedidosNoSinc = value;
    }

}
