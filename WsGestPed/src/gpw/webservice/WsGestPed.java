package gpw.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.jboss.logging.Logger;

import gpw.sincronizador.SincronizadorPersona;
import gpw.sincronizador.SincronizadorProducto;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasSinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;

@WebService(targetNamespace = "http://localhost:8080", portName = "WsGestPed", serviceName = "WsGestPed")
public class WsGestPed {

	private static Logger logger = Logger.getLogger(WsGestPed.class);
	
	@WebMethod(operationName = "servicioFuncional", action = "servicioFuncional", exclude = false)
	public String servicioFuncional() {
		SincronizadorPersona sp = new SincronizadorPersona();
		String retorno = sp.controlFuncionalidad();
		return retorno;
	}
	
	@WebMethod(operationName = "obtenerPersonasNoSinc", action = "obtenerPersonasNoSinc", exclude = false)
	@WebResult(name = "resultObtPersonasNoSinc")
	public ResultObtPersonasNoSinc obtenerPersonasNoSinc(@WebParam(name = "paramObtPersonasNoSinc") ParamObtPersonasNoSinc paramObtPersonasNoSinc) {
		logger.info(">>> # WS # >>> Inicia operacion obtenerClientesNoSinc...");
		ResultObtPersonasNoSinc result = new ResultObtPersonasNoSinc();
		
		SincronizadorPersona sinc = new SincronizadorPersona();
		result = sinc.obtPersonasNoSinc(paramObtPersonasNoSinc);
				
		logger.info(">>> # WS # >>> Finaliza operacion obtenerClientesNoSincr...");
		return result;
	}
	
	@WebMethod(operationName = "recibirPersonasSinc", action = "recibirPersonasSinc", exclude = false)
	@WebResult(name = "resultRecPersonasSinc")
	public ResultRecPersonasSinc recibirPersonasSinc(@WebParam(name = "paramRecPersonasSinc") ParamRecPersonasSinc paramRecPersonasSinc) {
		logger.info(">>> # WS # >>> Inicia operacion recibirPersonasSinc...");
		ResultRecPersonasSinc result = new ResultRecPersonasSinc();

		SincronizadorPersona sinc = new SincronizadorPersona();
		result = sinc.recPersonasSinc(paramRecPersonasSinc);

		logger.info(">>> # WS # >>> Finaliza operacion recibirPersonasSinc...");
		return result;
	}
	
	@WebMethod(operationName = "recibirProductosASinc", action = "recibirProductosASinc", exclude = false)
	@WebResult(name = "resultRecProductosASinc")
	public ResultRecProductosASinc recibirProductosASinc(@WebParam(name = "paramRecProductosASinc") ParamRecProductosASinc paramRecProductosASinc) {
		logger.info(">>> # WS # >>> Inicia operacion recibirProductosASinc...");
		ResultRecProductosASinc result = new ResultRecProductosASinc();

		SincronizadorProducto sinc = new SincronizadorProducto();
		result = sinc.recProductosASinc(paramRecProductosASinc);

		logger.info(">>> # WS # >>> Finaliza operacion recibirProductosASinc...");
		return result;
	}
	
}
