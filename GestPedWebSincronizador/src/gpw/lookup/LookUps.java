package gpw.lookup;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import gpw.ejb.SincronizadorStatelessLocal;


//import gpw.ejb.sincronizacion.SincronizadorStatelessRemote;

public abstract class LookUps {
	
	
	public static SincronizadorStatelessLocal lookUpEjb() {
		
		try {
			SincronizadorStatelessLocal sincStLoc = InitialContext.doLookup("java:app/GestPedWebEJB/SincronizadorStateless!gpw.ejb.SincronizadorStatelessLocal");
			return sincStLoc;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
