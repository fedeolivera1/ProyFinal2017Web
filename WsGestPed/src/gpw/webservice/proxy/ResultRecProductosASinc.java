
package gpw.webservice.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResultRecProductosASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultRecProductosASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="erroresServ" type="{http://192.168.1.2:8080}ErrorServicio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaProdSinc" type="{http://192.168.1.2:8080}ResultProductoASinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaTpSinc" type="{http://192.168.1.2:8080}ResultTipoProdASinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaUnidadSinc" type="{http://192.168.1.2:8080}ResultUnidadASinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultRecProductosASinc", propOrder = {
    "erroresServ",
    "listaProdSinc",
    "listaTpSinc",
    "listaUnidadSinc"
})
public class ResultRecProductosASinc {

    @XmlElement(nillable = true)
    protected List<ErrorServicio> erroresServ;
    @XmlElement(nillable = true)
    protected List<ResultProductoASinc> listaProdSinc;
    @XmlElement(nillable = true)
    protected List<ResultTipoProdASinc> listaTpSinc;
    @XmlElement(nillable = true)
    protected List<ResultUnidadASinc> listaUnidadSinc;

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
     * Gets the value of the listaProdSinc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaProdSinc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaProdSinc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultProductoASinc }
     * 
     * 
     */
    public List<ResultProductoASinc> getListaProdSinc() {
        if (listaProdSinc == null) {
            listaProdSinc = new ArrayList<ResultProductoASinc>();
        }
        return this.listaProdSinc;
    }

    /**
     * Gets the value of the listaTpSinc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaTpSinc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaTpSinc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultTipoProdASinc }
     * 
     * 
     */
    public List<ResultTipoProdASinc> getListaTpSinc() {
        if (listaTpSinc == null) {
            listaTpSinc = new ArrayList<ResultTipoProdASinc>();
        }
        return this.listaTpSinc;
    }

    /**
     * Gets the value of the listaUnidadSinc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaUnidadSinc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaUnidadSinc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultUnidadASinc }
     * 
     * 
     */
    public List<ResultUnidadASinc> getListaUnidadSinc() {
        if (listaUnidadSinc == null) {
            listaUnidadSinc = new ArrayList<ResultUnidadASinc>();
        }
        return this.listaUnidadSinc;
    }

}
