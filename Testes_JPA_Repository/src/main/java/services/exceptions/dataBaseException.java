package services.exceptions;

public class dataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public dataBaseException(String msg) {
		super(msg);
	}

}
