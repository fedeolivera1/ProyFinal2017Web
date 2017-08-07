
package gpw.webservice.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recibirPedidosASincResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recibirPedidosASincResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultRecPedidosASinc" type="{http://localhost:8080}ResultRecPedidosASinc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirPedidosASincResponse", propOrder = {
    "resultRecPedidosASinc"
})
public class RecibirPedidosASincResponse {

    protected ResultRecPedidosASinc resultRecPedidosASinc;

    /**
     * Obtiene el valor de la propiedad resultRecPedidosASinc.
     * 
     * @return
     *     possible object is
     *     {@link ResultRecPedidosASinc }
     *     
     */
    public ResultRecPedidosASinc getResultRecPedidosASinc() {
        return resultRecPedidosASinc;
    }

    /**
     * Define el valor de la propiedad resultRecPedidosASinc.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultRecPedidosASinc }
     *     
     */
    public void setResultRecPedidosASinc(ResultRecPedidosASinc value) {
        this.resultRecPedidosASinc = value;
    }

}
