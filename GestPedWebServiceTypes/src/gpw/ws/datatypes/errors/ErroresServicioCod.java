package gpw.ws.datatypes.errors;

public interface ErroresServicioCod {

	public static final int CODERR_EXCEP = 0;
	public static final int CODERR_WS_INDISPONIBLE = 1;
	public static final int CODERR_VAL_PARAM = 100;
	public static final int CODERR_SINC_UPDATE = 101; 
	public static final int CODERR_DATO_NO_EXISTE = 102;
	public static final int CODERR_WSAUTH = 103;
	
	public static final String WSERR_AUTH = "Error de autenticacion! Compruebe credenciales.";
	public static final String WSERR_WS_INDISPONIBLE = "El servicio no pudo completar el ciclo de disponibilidad.";
}
