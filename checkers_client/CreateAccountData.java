package checkers_client;

import java.io.Serializable;

import javax.swing.JLabel;

public class CreateAccountData implements Serializable {
	
	private String username;
	private String password;
	private String repassword;
	private boolean check;
	// Getters for the username and password.
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	  
	// Setters for the username and password.
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	  
		// Constructor that initializes the username and password.

	
	public void  setCheck(boolean bool) {
		this.check = bool;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public CreateAccountData(String username, String password)
	{
		setUsername(username);
		setPassword(password);
	}
}
