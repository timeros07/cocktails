package pl.cocktails.admin;

public class SystemException extends RuntimeException {
	
	private static final long serialVersionUID = -2951534339484771757L;
	private String message;
	private int errorCode;
	
	public SystemException(){super();}
	public SystemException(int errorCode, String message){
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
