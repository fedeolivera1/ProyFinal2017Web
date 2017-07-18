package gpw.ejb;

import javax.ejb.Local;

@Local
public interface SincronizadorStatelessLocal {
	
	public String devolverMensaje(String nombre) throws Exception;

}
