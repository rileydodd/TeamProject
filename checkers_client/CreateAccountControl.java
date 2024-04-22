package checkers_client;

import java.awt.*;
import javax.swing.*;

import lab7out.ClientGUI;
import lab7out.CreateAccountPanel;
import ocsf.client.AbstractClient;

import java.awt.event.*;
import java.io.IOException;

public class CreateAccountControl implements ActionListener {

	private CardLayout cl;
	private JPanel container;
	private CheckersClient client;
	private JLabel label;
	
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	public CreateAccountControl(CardLayout cl, JPanel container, CheckersClient client, JLabel status) {
		this.cl = cl;
		this.container = container;
		this.client = client;
		this.label = status;
	}
	
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
	    int count = container.getComponentCount();
	  
		try {
			client.sendToServer("hello server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    CreateAccount lp = (CreateAccount)container.getComponent(2);
	    
	    if(command.equals("Submit"))
	    {
	    	String username = lp.getUserName().getText();
			String password = lp.getPassword().getText();
	    	String repassword = lp.getrePassword().getText();
	    	
	    	if(username.equals(""))
	    	{
	    		label.setText("");
	    		label.setText(label.getText() + "Username field is empty");
	    	}
	    	else if(password.equals(""))
	    	{
	    		label.setText("");
	    		label.setText(label.getText() + "Password field is empty");
	    	}
	    	else if(password.length() < 6)
	    	{
	    		label.setText("");
	    		label.setText(label.getText() + "Password should be atleast 6 characters");
	    	}
	    	else if(!password.equals(repassword))
	    	{
	    		label.setText("");
	    		label.setText(label.getText() + "Passwords do not match");
	    	}
	    	else
	    	{
	    		CreateAccountData createAccountData = new CreateAccountData(username, password, repassword);
	    	
	    		try {
	    			client.sendToServer(createAccountData);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			displayError("Error Connecting to Server.");
	    		}
	    	}	
	    }
	}
	
	public void createAccountSuccess() {
		CreateAccount createAccount = (CreateAccount)container.getComponent(2);
		CheckersGUI checkersGUI = (CheckersGUI)SwingUtilities.getWindowAncestor(createAccount);
		//clientGUI.setUser(new User(createAccountPanel.getUsername(), createAccountPanel.getPassword()));
		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "4");
	}
	
	public void checker(boolean bool) {
		System.out.print("In checker " + bool);
		CreateAccount lp = (CreateAccount)container.getComponent(2);
		System.out.println("In checker");
	  	if(bool)
	  	{
	  		label.setText("Username already exist");
	  	}
	  	else
	  	{
	  		System.out.print("in else");
	  		this.cl = lp.getCL();
	  		cl.show(container, "1");
	  	}
	}
	
	public void displayError(String error)
	{
		CreateAccount createAccount = (CreateAccount)container.getComponent(2);
		createAccount.setError(error);
	}
}
