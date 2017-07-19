package gpw.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.jboss.logging.Logger;

import gpw.sincronizador.SincronizadorPersona;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;

@WebService(targetNamespace = "http://localhost:8080", portName = "WsGestPed", serviceName = "WsGestPed")
public class WsGestPed {

	private static Logger logger = Logger.getLogger(WsGestPed.class);
	
	@WebMethod(operationName = "servicioFuncional", action = "servicioFuncional", exclude = false)
	public String servicioFuncional() {
		SincronizadorPersona sp = new SincronizadorPersona();
		String retorno = sp.controlFuncionalidad();
		return retorno;
	}
	
	@WebMethod(operationName = "obtPersonasNoSinc", action = "obtPersonasNoSinc", exclude = false)
	@WebResult(name = "resultObtPersonasNoSinc")
	public ResultObtPersonasNoSinc obtenerClientesNoSincr(@WebParam(name = "paramObtPersonasNoSinc") ParamObtPersonasNoSinc paramObtPersonasNoSinc) {
		logger.info(">>> # WS # >>> Inicia operacion obtenerClientesNoSincr...");
		ResultObtPersonasNoSinc result = new ResultObtPersonasNoSinc();
		
//		SincronizadorPersona sinc = new SincronizadorPersona();
				
		logger.info(">>> # WS # >>> Finaliza operacion obtenerClientesNoSincr...");
		return result;
	}
	
}
