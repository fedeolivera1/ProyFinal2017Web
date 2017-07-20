package gpw.exceptions;

public class EjbException extends Exception {

	static final long serialVersionUID = 1L;
	
	public EjbException() {
		super();
	}
	public EjbException(String arg0) {
		super(arg0);
	}
	public EjbException(Throwable cause) {
		super(cause);
	}
	public EjbException(String message, Throwable cause) {
		super(message, cause);
	}
}
