package pl.cocktails.admin;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


public class JSONResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String OPERATION_SUCCESS = "response.success";
	
	private Boolean success;

	private String redirect;
	
	private List<JSONMessage> messages;
	
	public JSONResponse(){}
	
	/**
	 * Create JSON response with one message
	 * 
	 * @param success - true if success message
	 * @param messageKey - the message
	 */
	public JSONResponse(Boolean success, String messageKey){
		this.success = success;
		this.messages = new ArrayList<JSONMessage>();
		this.messages.add(new JSONMessage(MessageUtils.getMessage(messageKey)));
	}
	
	public JSONResponse(Boolean success, String messageKey, Object ... args){
		this.success = success;
		this.messages = new ArrayList<JSONMessage>();
		this.messages.add(new JSONMessage(MessageUtils.getMessage(messageKey, args)));
	}
	
	public JSONResponse(Boolean success, String messageKey, String redirect){
		this.success = success;
		this.messages = new ArrayList<JSONMessage>();
		this.messages.add(new JSONMessage(MessageUtils.getMessage(messageKey)));
		this.redirect = redirect;
	}
	
	public JSONResponse(boolean success, List<ObjectError> allErrors) {
		this.success = success;
		this.messages = new ArrayList<JSONMessage>();
		for(ObjectError error : allErrors){
			String message = null, field = null;
			message = MessageUtils.getMessage(error.getCode(), error.getArguments());
			if(error instanceof FieldError){
				field = ((FieldError) error).getField();
			}
			this.messages.add(new JSONMessage(field, message));
		}
	}

	public Boolean getSuccess(){
		return this.success;
	}
	
	public String getRedirect() {
		return redirect;
	}

	public List<JSONMessage> getMessages(){
		return messages;
	}

}
