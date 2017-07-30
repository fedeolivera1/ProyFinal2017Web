package gpw.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.jboss.logging.Logger;

import gpw.ejb.SincronizadorStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasASinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;

@WebService(targetNamespace = "http://localhost:8080", portName = "WsGestPed", serviceName = "WsGestPed")
public class WsGestPed {

	private static Logger logger = Logger.getLogger(WsGestPed.class);
	
	@WebMethod(operationName = "servicioFuncional", action = "servicioFuncional", exclude = false)
	public String servicioFuncional() {
		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		String retorno = sincSl.servicioFuncional();
		return retorno;
	}
	
	@WebMethod(operationName = "obtenerPersonasNoSinc", action = "obtenerPersonasNoSinc", exclude = false)
	@WebResult(name = "resultObtPersonasNoSinc")
	public ResultObtPersonasNoSinc obtenerPersonasNoSinc(@WebParam(name = "paramObtPersonasNoSinc") ParamObtPersonasNoSinc paramObtPersonasNoSinc) {
		logger.info(">>> # WS # >>> Inicia operacion obtenerClientesNoSinc...");
		ResultObtPersonasNoSinc result = new ResultObtPersonasNoSinc();
		
		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		result = sincSl.obtPersonasNoSinc(paramObtPersonasNoSinc);
				
		logger.info(">>> # WS # >>> Finaliza operacion obtenerClientesNoSincr...");
		return result;
	}
	
	@WebMethod(operationName = "recibirPersonasSinc", action = "recibirPersonasSinc", exclude = false)
	@WebResult(name = "resultRecPersonasASinc")
	public ResultRecPersonasASinc recibirPersonasSinc(@WebParam(name = "paramRecPersonasASinc") ParamRecPersonasASinc paramRecPersonasASinc) {
		logger.info(">>> # WS # >>> Inicia operacion recibirPersonasSinc...");
		ResultRecPersonasASinc result = new ResultRecPersonasASinc();

		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		result = sincSl.recPersonasASinc(paramRecPersonasASinc);

		logger.info(">>> # WS # >>> Finaliza operacion recibirPersonasSinc...");
		return result;
	}
	
	@WebMethod(operationName = "recibirProductosASinc", action = "recibirProductosASinc", exclude = false)
	@WebResult(name = "resultRecProductosASinc")
	public ResultRecProductosASinc recibirProductosASinc(@WebParam(name = "paramRecProductosASinc") ParamRecProductosASinc paramRecProductosASinc) {
		logger.info(">>> # WS # >>> Inicia operacion recibirProductosASinc...");
		ResultRecProductosASinc result = new ResultRecProductosASinc();

		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		result = sincSl.recProductosASinc(paramRecProductosASinc);

		logger.info(">>> # WS # >>> Finaliza operacion recibirProductosASinc...");
		return result;
	}
	
}
