package gpw.webservice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.jboss.logging.Logger;

import gpw.ejb.SincronizadorStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.ws.datatypes.pedido.ParamObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ParamRecPedidosASinc;
import gpw.ws.datatypes.pedido.ResultObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ResultRecPedidosASinc;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasASinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;

@WebService(targetNamespace = "http://localhost:8080", portName = "WsGestPed", serviceName = "WsGestPed")
public class WsGestPed {

	private static Logger logger = Logger.getLogger(WsGestPed.class);
	@Resource
	WebServiceContext wsctx;
	
	@WebMethod(operationName = "servicioFuncional", action = "servicioFuncional", exclude = false)
	public String servicioFuncional() {
		MessageContext mctx = wsctx.getMessageContext();
		
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");
		
		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		String retorno = sincSl.servicioFuncional();
		return retorno;
	}
	
	@WebMethod(operationName = "obtenerPersonasNoSinc", action = "obtenerPersonasNoSinc", exclude = false)
	@WebResult(name = "resultObtPersonasNoSinc")
	public ResultObtPersonasNoSinc obtenerPersonasNoSinc(@WebParam(name = "paramObtPersonasNoSinc") ParamObtPersonasNoSinc paramObtPersonasNoSinc) {
		logger.info("<<< # WS # >>> Inicia operacion obtenerClientesNoSinc...");
		
		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		ResultObtPersonasNoSinc result = sincSl.obtPersonasNoSinc(paramObtPersonasNoSinc);
				
		logger.info("<<< # WS # >>> Finaliza operacion obtenerClientesNoSincr...");
		return result;
	}
	
	@WebMethod(operationName = "recibirPersonasASinc", action = "recibirPersonasASinc", exclude = false)
	@WebResult(name = "resultRecPersonasASinc")
	public ResultRecPersonasASinc recibirPersonasASinc(@WebParam(name = "paramRecPersonasASinc") ParamRecPersonasASinc paramRecPersonasASinc) {
		logger.info("<<< # WS # >>> Inicia operacion recibirPersonasASinc...");

		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		ResultRecPersonasASinc result = sincSl.recPersonasASinc(paramRecPersonasASinc);

		logger.info("<<< # WS # >>> Finaliza operacion recibirPersonasASinc...");
		return result;
	}
	
	@WebMethod(operationName = "recibirProductosASinc", action = "recibirProductosASinc", exclude = false)
	@WebResult(name = "resultRecProductosASinc")
	public ResultRecProductosASinc recibirProductosASinc(@WebParam(name = "paramRecProductosASinc") ParamRecProductosASinc paramRecProductosASinc) {
		logger.info("<<< # WS # >>> Inicia operacion recibirProductosASinc...");

		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		ResultRecProductosASinc result = sincSl.recProductosASinc(paramRecProductosASinc);

		logger.info("<<< # WS # >>> Finaliza operacion recibirProductosASinc...");
		return result;
	}
	
	@WebMethod(operationName = "obtenerPedidosNoSinc", action = "obtenerPedidosNoSinc", exclude = false)
	@WebResult(name = "resultObtPedidosNoSinc")
	public ResultObtPedidosNoSinc obtenerPedidosNoSinc(@WebParam(name = "paramObtPedidosNoSinc") ParamObtPedidosNoSinc paramObtPedidosNoSinc) {
		logger.info("<<< # WS # >>> Inicia operacion obtenerPedidosASinc...");

		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		ResultObtPedidosNoSinc result = sincSl.obtPedidosNoSinc(paramObtPedidosNoSinc);

		logger.info("<<< # WS # >>> Finaliza operacion obtenerPedidosASinc...");
		return result;
	}
	
	@WebMethod(operationName = "recibirPedidosASinc", action = "recibirPedidosASinc", exclude = false)
	@WebResult(name = "resultRecPedidosASinc")
	public ResultRecPedidosASinc recibirPedidosASinc(@WebParam(name = "paramRecPedidosASinc") ParamRecPedidosASinc paramRecPedidosASinc) {
		logger.info("<<< # WS # >>> Inicia operacion recibirPedidosASinc...");

		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		ResultRecPedidosASinc result = sincSl.recPedidosASinc(paramRecPedidosASinc);

		logger.info("<<< # WS # >>> Finaliza operacion recibirPedidosASinc...");
		return result;
	}
	
}
