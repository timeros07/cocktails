package pl.cocktails.common;

public class JSONMessage {
	
	private String field;
	private String message;
	
	public JSONMessage(){}
	
	public JSONMessage(String message){
		this.message = message;
	}
	
	public JSONMessage(String field, String message){
		this.field = field;
		this.message = message;
	}
	
	public String getField() {
		return field;
	}

	public String getMessage(){
		return this.message;
	}
}
