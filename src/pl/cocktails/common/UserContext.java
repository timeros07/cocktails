package pl.cocktails.common;

import java.io.Serializable;

import pl.cocktails.data.UserData;

public class UserContext implements Serializable {

	private static final long serialVersionUID = -1224723867036841895L;
	
	private UserData user;
	
	private String loginUrl;
	
	private String logoutUrl;
	
	private Boolean isAdmin;

	public UserContext(){
		user = null;
	}
	
	public UserContext(UserData user, String logoutURL){
		this.user = user;
		this.logoutUrl = logoutURL;
	}
	
	public UserContext(String loginURL){
		this.loginUrl = loginURL;
	}
	
	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
