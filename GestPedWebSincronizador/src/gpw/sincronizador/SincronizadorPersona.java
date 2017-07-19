package gpw.sincronizador;

import org.apache.log4j.Logger;

import gpw.ejb.SincronizadorStatelessLocal;
import gpw.lookup.LookUps;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;

public class SincronizadorPersona {

	Logger logger = Logger.getLogger(SincronizadorPersona.class);
	
	
	public String controlFuncionalidad() {
		SincronizadorStatelessLocal sincSl = LookUps.lookUpEjb();
		String mensaje = null;
		try {
			Boolean res = sincSl.servicioFuncional();
			mensaje = res ? "Servicio funcional" : "Servicio NO DISPONIBLE";
			sincSl.guardadoPrueba();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
	public ResultObtPersonasNoSinc obtPersonasNoSinc(ParamObtPersonasNoSinc param) {
		if(param != null) {
			
		}
		return null;
	}
	
}
