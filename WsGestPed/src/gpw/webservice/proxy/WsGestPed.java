package gpw.webservice.proxy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-07-23T18:10:32.874-03:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://localhost:8080", name = "WsGestPed")
@XmlSeeAlso({ObjectFactory.class})
public interface WsGestPed {

    @WebMethod(action = "obtenerPersonasNoSinc")
    @RequestWrapper(localName = "obtenerPersonasNoSinc", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.ObtenerPersonasNoSinc")
    @ResponseWrapper(localName = "obtenerPersonasNoSincResponse", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.ObtenerPersonasNoSincResponse")
    @WebResult(name = "resultObtPersonasNoSinc", targetNamespace = "")
    public gpw.webservice.proxy.ResultObtPersonasNoSinc obtenerPersonasNoSinc(
        @WebParam(name = "paramObtPersonasNoSinc", targetNamespace = "")
        gpw.webservice.proxy.ParamObtPersonasNoSinc paramObtPersonasNoSinc
    );

    @WebMethod(action = "recibirPersonasSinc")
    @RequestWrapper(localName = "recibirPersonasSinc", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.RecibirPersonasSinc")
    @ResponseWrapper(localName = "recibirPersonasSincResponse", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.RecibirPersonasSincResponse")
    @WebResult(name = "resultRecPersonasSinc", targetNamespace = "")
    public gpw.webservice.proxy.ResultRecPersonasSinc recibirPersonasSinc(
        @WebParam(name = "paramRecPersonasSinc", targetNamespace = "")
        gpw.webservice.proxy.ParamRecPersonasSinc paramRecPersonasSinc
    );

    @WebMethod(action = "recibirProductosASinc")
    @RequestWrapper(localName = "recibirProductosASinc", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.RecibirProductosASinc")
    @ResponseWrapper(localName = "recibirProductosASincResponse", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.RecibirProductosASincResponse")
    @WebResult(name = "resultRecProductosASinc", targetNamespace = "")
    public gpw.webservice.proxy.ResultRecProductosASinc recibirProductosASinc(
        @WebParam(name = "paramRecProductosASinc", targetNamespace = "")
        gpw.webservice.proxy.ParamRecProductosASinc paramRecProductosASinc
    );

    @WebMethod(action = "servicioFuncional")
    @RequestWrapper(localName = "servicioFuncional", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.ServicioFuncional")
    @ResponseWrapper(localName = "servicioFuncionalResponse", targetNamespace = "http://localhost:8080", className = "gpw.webservice.proxy.ServicioFuncionalResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String servicioFuncional();
}
