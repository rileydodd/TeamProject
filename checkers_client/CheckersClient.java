package checkers_client;

import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ocsf.client.AbstractClient;
import checkers_multiplayer.Player;
import checkers_multiplayer.Piece;
import checkers_multiplayer.GameState;
import checkers_multiplayer.Response;
import checkers_multiplayer.Move;

public class CheckersClient extends AbstractClient {
	// Private data fields for storing the GUI controllers.
		private LoginControl loginControl;
		private CreateAccountControl createAccountControl;
		private int id;

		// Setters for the GUI controllers.
		public void setLoginControl(LoginControl loginControl)
		{
			this.loginControl = loginControl;
		}
		public void setCreateAccountControl(CreateAccountControl createAccountControl)
		{
			this.createAccountControl = createAccountControl;
		}

		// Constructor for initializing the client with default settings.
		public CheckersClient()
		{
			super("10.252.161.60", 8300);
		}
		
		public int get_id() {
			// TODO Auto-generated method stub
			return this.id;
		}
		
		public void send_move(int[] old_pos, int[] new_pos) {
			// TODO Auto-generated method stub
			
		}  
	  
		// Method that handles messages from the server.
		public void handleMessageFromServer(Object arg0)
		{
			// If we received a String, figure out what this event is.
			if (arg0 instanceof String)
			{
				// Get the text of the message.
				String message = (String)arg0;
	      
				// If we successfully logged in, tell the login controller.
				if (message.equals("LoginSuccessful"))
				{
					loginControl.loginSuccess();
				}
	      
				// If we successfully created an account, tell the create account controller.
				else if (message.equals("CreateAccountSuccessful"))
				{
					createAccountControl.createAccountSuccess();
				}
			}
	    
			// If we received an Error, figure out where to display it.
			else if (arg0 instanceof Error)
			{
				// Get the Error object.
				Error error = (Error)arg0;
	      
				// Display login errors using the login controller.
				if (error.getType().equals("Login"))
				{
					loginControl.displayError(error.getMessage());
				}
	      
				// Display account creation errors using the create account controller.
				else if (error.getType().equals("CreateAccount"))
				{
					createAccountControl.displayError(error.getMessage());
				}
			}
		}
}
