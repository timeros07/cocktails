package pl.cocktails.portal.accounts;

import java.io.Serializable;

public class UserData implements Serializable{
	
	private static final long serialVersionUID = 6184334129330755462L;

	private String login;
	
	private String email;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
