
package gpw.webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.6
 * Mon Sep 25 03:09:09 UYT 2017
 * Generated source version: 3.1.6
 */

@XmlRootElement(name = "obtenerPedidosNoSinc", namespace = "http://localhost:8080")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerPedidosNoSinc", namespace = "http://localhost:8080")

public class ObtenerPedidosNoSinc {

    @XmlElement(name = "paramObtPedidosNoSinc")
    private gpw.ws.datatypes.pedido.ParamObtPedidosNoSinc paramObtPedidosNoSinc;

    public gpw.ws.datatypes.pedido.ParamObtPedidosNoSinc getParamObtPedidosNoSinc() {
        return this.paramObtPedidosNoSinc;
    }

    public void setParamObtPedidosNoSinc(gpw.ws.datatypes.pedido.ParamObtPedidosNoSinc newParamObtPedidosNoSinc)  {
        this.paramObtPedidosNoSinc = newParamObtPedidosNoSinc;
    }

}

