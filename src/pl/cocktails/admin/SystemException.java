package pl.cocktails.admin;

public class SystemException extends RuntimeException {
	
	private static final long serialVersionUID = -2951534339484771757L;
	private String message;
	
	public SystemException(String message){
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
