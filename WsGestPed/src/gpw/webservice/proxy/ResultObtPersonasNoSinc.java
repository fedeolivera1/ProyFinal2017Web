
package gpw.webservice.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResultObtPersonasNoSinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultObtPersonasNoSinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="erroresServ" type="{http://localhost:8080}ErrorServicio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaPersFisica" type="{http://localhost:8080}ResultPersonaFisica" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaPersJuridica" type="{http://localhost:8080}ResultPersonaJuridica" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultObtPersonasNoSinc", propOrder = {
    "erroresServ",
    "listaPersFisica",
    "listaPersJuridica"
})
public class ResultObtPersonasNoSinc {

    @XmlElement(nillable = true)
    protected List<ErrorServicio> erroresServ;
    @XmlElement(nillable = true)
    protected List<ResultPersonaFisica> listaPersFisica;
    @XmlElement(nillable = true)
    protected List<ResultPersonaJuridica> listaPersJuridica;

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
     * Gets the value of the listaPersFisica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPersFisica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPersFisica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultPersonaFisica }
     * 
     * 
     */
    public List<ResultPersonaFisica> getListaPersFisica() {
        if (listaPersFisica == null) {
            listaPersFisica = new ArrayList<ResultPersonaFisica>();
        }
        return this.listaPersFisica;
    }

    /**
     * Gets the value of the listaPersJuridica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPersJuridica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPersJuridica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultPersonaJuridica }
     * 
     * 
     */
    public List<ResultPersonaJuridica> getListaPersJuridica() {
        if (listaPersJuridica == null) {
            listaPersJuridica = new ArrayList<ResultPersonaJuridica>();
        }
        return this.listaPersJuridica;
    }

}
