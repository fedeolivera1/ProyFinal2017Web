
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerPedidosNoSincResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerPedidosNoSincResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultObtPedidosNoSinc" type="{http://localhost:8080}ResultObtPedidosNoSinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerPedidosNoSincResponse", propOrder = {
    "resultObtPedidosNoSinc"
})
public class ObtenerPedidosNoSincResponse {

    protected ResultObtPedidosNoSinc resultObtPedidosNoSinc;

    /**
     * Obtiene el valor de la propiedad resultObtPedidosNoSinc.
     * 
     * @return
     *     possible object is
     *     {@link ResultObtPedidosNoSinc }
     *     
     */
    public ResultObtPedidosNoSinc getResultObtPedidosNoSinc() {
        return resultObtPedidosNoSinc;
    }

    /**
     * Define el valor de la propiedad resultObtPedidosNoSinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultObtPedidosNoSinc }
     *     
     */
    public void setResultObtPedidosNoSinc(ResultObtPedidosNoSinc value) {
        this.resultObtPedidosNoSinc = value;
    }

}
