
package gpw.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.6
 * Sat Sep 09 22:42:42 UYT 2017
 * Generated source version: 3.1.6
 */

@XmlRootElement(name = "obtenerPersonasNoSincResponse", namespace = "http://192.168.1.2:8080")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerPersonasNoSincResponse", namespace = "http://192.168.1.2:8080")

public class ObtenerPersonasNoSincResponse {

    @XmlElement(name = "resultObtPersonasNoSinc")
    private gpw.ws.datatypes.persona.ResultObtPersonasNoSinc resultObtPersonasNoSinc;

    public gpw.ws.datatypes.persona.ResultObtPersonasNoSinc getResultObtPersonasNoSinc() {
        return this.resultObtPersonasNoSinc;
    }

    public void setResultObtPersonasNoSinc(gpw.ws.datatypes.persona.ResultObtPersonasNoSinc newResultObtPersonasNoSinc)  {
        this.resultObtPersonasNoSinc = newResultObtPersonasNoSinc;
    }

}

