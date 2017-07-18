package gpw.lookup;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import gpw.ejb.SincronizadorStatelessLocal;


//import gpw.ejb.sincronizacion.SincronizadorStatelessRemote;

public class LookUps {
	
	public String lookupEjb() {
		
		try {
//			Context ctx = new InitialContext();
			SincronizadorStatelessLocal sh = InitialContext.doLookup("java:app/GestPedWebEJB/SincronizadorStateless!gpw.ejb.SincronizadorStatelessLocal");
			String mensaje = sh.devolverMensaje("no puedo creer que anda...");
			
			return mensaje;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
