
package gpw.webservice.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ParamRecProductosASinc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ParamRecProductosASinc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaProducto" type="{http://localhost:8080}ParamProductoASinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaTipoProd" type="{http://localhost:8080}ParamTipoProdASinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaUnidad" type="{http://localhost:8080}ParamUnidadASinc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParamRecProductosASinc", propOrder = {
    "listaProducto",
    "listaTipoProd",
    "listaUnidad"
})
public class ParamRecProductosASinc {

    @XmlElement(nillable = true)
    protected List<ParamProductoASinc> listaProducto;
    @XmlElement(nillable = true)
    protected List<ParamTipoProdASinc> listaTipoProd;
    @XmlElement(nillable = true)
    protected List<ParamUnidadASinc> listaUnidad;

    /**
     * Gets the value of the listaProducto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaProducto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaProducto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParamProductoASinc }
     * 
     * 
     */
    public List<ParamProductoASinc> getListaProducto() {
        if (listaProducto == null) {
            listaProducto = new ArrayList<ParamProductoASinc>();
        }
        return this.listaProducto;
    }

    /**
     * Gets the value of the listaTipoProd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaTipoProd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaTipoProd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParamTipoProdASinc }
     * 
     * 
     */
    public List<ParamTipoProdASinc> getListaTipoProd() {
        if (listaTipoProd == null) {
            listaTipoProd = new ArrayList<ParamTipoProdASinc>();
        }
        return this.listaTipoProd;
    }

    /**
     * Gets the value of the listaUnidad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaUnidad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaUnidad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParamUnidadASinc }
     * 
     * 
     */
    public List<ParamUnidadASinc> getListaUnidad() {
        if (listaUnidad == null) {
            listaUnidad = new ArrayList<ParamUnidadASinc>();
        }
        return this.listaUnidad;
    }

}
