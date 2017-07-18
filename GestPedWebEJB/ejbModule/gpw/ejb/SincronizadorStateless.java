package gpw.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Session Bean implementation class SincronizadorStateless
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SincronizadorStateless implements SincronizadorStatelessRemote, SincronizadorStatelessLocal {

    /**
     * Default constructor. 
     */
    public SincronizadorStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String devolverMensaje(String nombre) throws Exception {
		String retorno = "El EJB devuelve el nombre: " + nombre + "!!!!!!!!!!!!!!!!!!!!!!";
		return retorno;
	}

}
