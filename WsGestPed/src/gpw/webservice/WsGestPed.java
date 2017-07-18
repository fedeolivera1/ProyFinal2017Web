package gpw.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import gpw.lookup.LookUps;

@WebService(targetNamespace = "http://localhost:8080", portName = "WsGestPed", serviceName = "WsGestPed")
public class WsGestPed {

	@WebMethod(operationName = "retornoPrueba", action = "retornoPrueba", exclude = false)
	public String retornoPrueba() {
		LookUps lkps = new LookUps();
		String retorno = lkps.lookupEjb(); 
		return retorno;
	}
	
}
