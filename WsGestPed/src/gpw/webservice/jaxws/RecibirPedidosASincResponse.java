
package gpw.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.6
 * Fri Aug 04 22:35:57 UYT 2017
 * Generated source version: 3.1.6
 */

@XmlRootElement(name = "recibirPedidosASincResponse", namespace = "http://localhost:8080")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirPedidosASincResponse", namespace = "http://localhost:8080")

public class RecibirPedidosASincResponse {

    @XmlElement(name = "resultRecPedidosASinc")
    private gpw.ws.datatypes.pedido.ResultRecPedidosASinc resultRecPedidosASinc;

    public gpw.ws.datatypes.pedido.ResultRecPedidosASinc getResultRecPedidosASinc() {
        return this.resultRecPedidosASinc;
    }

    public void setResultRecPedidosASinc(gpw.ws.datatypes.pedido.ResultRecPedidosASinc newResultRecPedidosASinc)  {
        this.resultRecPedidosASinc = newResultRecPedidosASinc;
    }

}
