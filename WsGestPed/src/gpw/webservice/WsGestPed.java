package gpw.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.jboss.logging.Logger;

import gpw.sincronizador.SincronizadorPersona;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasSinc;

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
		
		SincronizadorPersona sinc = new SincronizadorPersona();
		result = sinc.obtPersonasNoSinc(paramObtPersonasNoSinc);
				
		logger.info(">>> # WS # >>> Finaliza operacion obtenerClientesNoSincr...");
		return result;
	}
	
	@WebMethod(operationName = "recPersonasSinc", action = "recPersonasSinc", exclude = false)
	@WebResult(name = "resultRecPersonasSinc")
	public ResultRecPersonasSinc recPersonasSinc(@WebParam(name = "paramRecPersonasSinc") ParamRecPersonasSinc paramRecPersonasSinc) {
		logger.info(">>> # WS # >>> Inicia operacion recPersonasSinc...");
		ResultRecPersonasSinc result = new ResultRecPersonasSinc();

		SincronizadorPersona sinc = new SincronizadorPersona();
		result = sinc.recPersonasSinc(paramRecPersonasSinc);

		logger.info(">>> # WS # >>> Finaliza operacion recPersonasSinc...");
		return result;
	}
	
}
