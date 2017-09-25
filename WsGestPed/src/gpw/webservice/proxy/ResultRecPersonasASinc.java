
package gpw.webservice.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResultRecPersonasASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultRecPersonasASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="erroresServ" type="{http://localhost:8080}ErrorServicio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaPersonaSinc" type="{http://localhost:8080}ResultPersonaSinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultRecPersonasASinc", propOrder = {
    "erroresServ",
    "listaPersonaSinc"
})
public class ResultRecPersonasASinc {

    @XmlElement(nillable = true)
    protected List<ErrorServicio> erroresServ;
    @XmlElement(nillable = true)
    protected List<ResultPersonaSinc> listaPersonaSinc;

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
     * Gets the value of the listaPersonaSinc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPersonaSinc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPersonaSinc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultPersonaSinc }
     * 
     * 
     */
    public List<ResultPersonaSinc> getListaPersonaSinc() {
        if (listaPersonaSinc == null) {
            listaPersonaSinc = new ArrayList<ResultPersonaSinc>();
        }
        return this.listaPersonaSinc;
    }

}
