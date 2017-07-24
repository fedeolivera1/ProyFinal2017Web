package gpw.webservice.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-07-23T18:10:32.911-03:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "WsGestPed", 
                  wsdlLocation = "file:/C:/Proyecto/ProyFinal2017Web/WsGestPed/wsdl/WsGestPed.wsdl",
                  targetNamespace = "http://localhost:8080") 
public class WsGestPed_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://localhost:8080", "WsGestPed");
    public final static QName WsGestPed = new QName("http://localhost:8080", "WsGestPed");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Proyecto/ProyFinal2017Web/WsGestPed/wsdl/WsGestPed.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WsGestPed_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Proyecto/ProyFinal2017Web/WsGestPed/wsdl/WsGestPed.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WsGestPed_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WsGestPed_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WsGestPed_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public WsGestPed_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public WsGestPed_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public WsGestPed_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns WsGestPed
     */
    @WebEndpoint(name = "WsGestPed")
    public WsGestPed getWsGestPed() {
        return super.getPort(WsGestPed, WsGestPed.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsGestPed
     */
    @WebEndpoint(name = "WsGestPed")
    public WsGestPed getWsGestPed(WebServiceFeature... features) {
        return super.getPort(WsGestPed, WsGestPed.class, features);
    }

}
