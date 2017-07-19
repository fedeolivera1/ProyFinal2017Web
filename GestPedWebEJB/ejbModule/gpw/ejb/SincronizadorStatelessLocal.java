package gpw.ejb;

import javax.ejb.Local;

@Local
public interface SincronizadorStatelessLocal {
	
	public Boolean servicioFuncional() throws Exception;
	public void guardadoPrueba() throws Exception;

}
