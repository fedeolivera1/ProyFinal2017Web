package gpw.ejblookup;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import gpw.ejb.GpWebStatelessLocal;

public abstract class LookUps {
	
	private static Logger logger = Logger.getLogger(LookUps.class);
	
	public static GpWebStatelessLocal lookUpGpWebStateless() {
		
		try {
			GpWebStatelessLocal gpwStLoc = InitialContext.doLookup("java:app/GestPedWebEJB/GpWebStateless!gpw.ejb.GpWebStatelessLocal");
			return gpwStLoc;
		} catch (NamingException e) {
			logger.error("Excepcion NamingException al hacer lookup... LookUps > lookUpEjb: " + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Excepcion no controlada al hacer lookup... LookUps > lookUpEjb: " + e.getMessage(), e);
		}
		return null;
	}
	
	
}
