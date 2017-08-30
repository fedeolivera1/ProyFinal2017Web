package gpw.webservice.security;

import java.util.List;
import java.util.Map;

import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

public class Authenticator {
	
	private static Logger logger = Logger.getLogger(Authenticator.class);

	public static Boolean authenticateWsCAll(MessageContext msgCtx) {
		try {
			if(msgCtx != null) {
				Map<?,?> requestHeaders = (Map<?,?>) msgCtx.get(MessageContext.HTTP_REQUEST_HEADERS);
		        List<?> userList = (List<?>) requestHeaders.get("username");
		        List<?> passList = (List<?>) requestHeaders.get("password");
		        if( (userList != null && !userList.isEmpty()) && 
		        		(passList != null && !passList.isEmpty()) ) {
		        	String nomUsr = (String) userList.get(0);
		        	String passWd = (String) passList.get(0);
		        	if(nomUsr.equals("wsgestped") && passWd.equals("pf2017yametl")) {
		        		return true;
		        	}
		        }
			}
		} catch (Exception e) {
			logger.fatal("Excepcion en Authenticator > authenticateWsCAll al validar las credenciales: " + e.getMessage(), e);
			return false;
		}
		return false;
	}

}
