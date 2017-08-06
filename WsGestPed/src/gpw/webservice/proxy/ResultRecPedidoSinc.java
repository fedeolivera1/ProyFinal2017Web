
package gpw.webservice.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResultRecPedidoSinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultRecPedidoSinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="erroresServ" type="{http://localhost:8080}ErrorServicio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaPedidoSinc" type="{http://localhost:8080}ResultPedidoSinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultRecPedidoSinc", propOrder = {
    "erroresServ",
    "listaPedidoSinc"
})
public class ResultRecPedidoSinc {

    @XmlElement(nillable = true)
    protected List<ErrorServicio> erroresServ;
    @XmlElement(nillable = true)
    protected List<ResultPedidoSinc> listaPedidoSinc;

    /**
     * Gets the value of the erroresServ property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the erroresServ property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErroresServ().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorServicio }
     * 
     * 
     */
    public List<ErrorServicio> getErroresServ() {
        if (erroresServ == null) {
            erroresServ = new ArrayList<ErrorServicio>();
        }
        return this.erroresServ;
    }

    /**
     * Gets the value of the listaPedidoSinc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPedidoSinc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPedidoSinc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultPedidoSinc }
     * 
     * 
     */
    public List<ResultPedidoSinc> getListaPedidoSinc() {
        if (listaPedidoSinc == null) {
            listaPedidoSinc = new ArrayList<ResultPedidoSinc>();
        }
        return this.listaPedidoSinc;
    }

}
