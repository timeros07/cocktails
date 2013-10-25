package pl.cocktails.admin;
import java.io.Serializable;
import java.util.List;

import org.springframework.validation.ObjectError;


public class JSONResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String OPERATION_SUCCESS = "response.success";
	
	private Boolean success;
	private String message;
	private String redirect;
	
	public JSONResponse(){}
	
	public JSONResponse(Boolean success, String messageKey){
		this.success = success;
		this.message = MessageUtils.getMessage(messageKey);
	}
	
	public JSONResponse(Boolean success, String messageKey, String redirect){
		this.success = success;
		this.message = MessageUtils.getMessage(messageKey);
		this.redirect = redirect;
	}
	
	public JSONResponse(boolean success, List<ObjectError> allErrors) {
		this.success = success;
		for(ObjectError error : allErrors){
			this.message = MessageUtils.getMessage(error.getCode(), error.getArguments());
		}
	}

	public Boolean getSuccess(){
		return this.success;
	}
	
	public String getMessage(){
		return this.message;
	}

	public String getRedirect() {
		return redirect;
	}

}
