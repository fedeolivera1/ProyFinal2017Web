
package gpw.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.6
 * Sun Jul 30 01:35:40 UYT 2017
 * Generated source version: 3.1.6
 */

@XmlRootElement(name = "servicioFuncionalResponse", namespace = "http://localhost:8080")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servicioFuncionalResponse", namespace = "http://localhost:8080")

public class ServicioFuncionalResponse {

    @XmlElement(name = "return")
    private java.lang.String _return;

    public java.lang.String getReturn() {
        return this._return;
    }

    public void setReturn(java.lang.String new_return)  {
        this._return = new_return;
    }

}

