package services.exceptions;

public class resourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public resourceNotFoundException(String msg) {
		super(msg);
	}
}
