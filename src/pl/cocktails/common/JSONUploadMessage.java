package pl.cocktails.common;

import java.io.Serializable;

public class JSONUploadMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String UPLOAD_SUCCESS = "response.upload.success";
	
	private Boolean success;
	
	private String blobKey;
	
	private String imageUrl;
	
	private String message;

	public JSONUploadMessage(){}
	
	public JSONUploadMessage(Boolean success, String messageKey, String blobkey, String imageUrl){
		this.success = success;
		this.message = MessageUtils.getMessage(messageKey);
		this.blobKey = blobkey;
		this.imageUrl = imageUrl;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
