package checkers_client;

import java.io.Serializable;

public class LoginData implements Serializable {

	private String username;
	private String password;

	public String  getUserName() {
		return this.username;
	}
	
	public void  setUserName(String username ) {
		this.username = username;
	}
	
	public String  getPassword() {
		return this.password;
	}
	
	public void  setPassword(String password) {
		this.password = password;
	}
	
	
	public LoginData(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
