package checkers_multiplayer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import checkers_client.*;
import java.awt.*;
import java.io.IOException;
import java.lang.Runnable;
import java.sql.SQLException;
import java.util.*;

//Checkers server for communication
public class CheckersServer extends AbstractServer{
	private JTextArea log;
	private JLabel status;
	private DatabaseFile databasefile;
	private boolean x;
	private CheckersEngine ce;
	private Player player1;
	private Player player2;
	private int num_players;
	private Piece[][] board;
	private boolean running = false;
	
	public CheckersServer() {
		super(8300);
		num_players = 0;
		
		try {
			databasefile = new DatabaseFile(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public CheckersServer(int port, int timeout) {
		super(port);
		num_players = 0;
		this.setTimeout(timeout);

		ArrayList<Piece> player_1_pieces = new ArrayList<Piece>();
		ArrayList<Piece> player_2_pieces = new ArrayList<Piece>();
		
		this.board = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
		    for (int j = 0; j < 8; j++) {
		        int[] pos = new int[2];
		        pos[0] = i;
		        pos[1] = j;

		        // Set up white pieces
		        if ((i + j) % 2 == 0 && i < 3) {
		            board[i][j] = new CheckersPiece("Red", pos);
		        }
		        // Set up black pieces
		        else if ((i + j) % 2 == 0 && i > 4) {
		            board[i][j] = new CheckersPiece("Black", pos);
		        }
		        // Set up empty squares
		        else {
		            board[i][j] = new Piece("Empty", pos);
		        }
		    }

		    //instantiate two players 
		    player1 = new Player("player1", 0, board, player_1_pieces, 0);
		    player2 = new Player("player2", 1, board, player_2_pieces, 1);

		    this.ce = new CheckersEngine(board, player1, player2);

		    try {
		    	this.listen();
		    } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		    }
		}
	}
	
	// Getter that returns whether the server is currently running.
	public boolean isRunning()
	{
		return running;
	}
	
	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log)
	{
		this.log = log;
	}

	public void setStatus(JLabel status)
	{
		this.status = status;
	}
	
	public JLabel getStatus() {
		return status;
	}
	
	public JTextArea getLog() {
		return log;
	}

	// When the server starts, update the GUI.
	public void serverStarted()
	{
		running = true;
		System.out.println("Listening");
		log.append("Server started\n");
	}
	
	// When the server stops listening, update the GUI.
	public void serverStopped()
	{
		System.out.println("Stopped");
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}
	
	// When the server closes completely, update the GUI.
	public void serverClosed()
	{
		running = false;
		System.out.println("Close");
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a client connects or disconnects, display a message in the log.
	public void clientConnected(ConnectionToClient client)
	{
		log.append("Client " + client.getId() + " connected\n");
		if (num_players == 0) {
			try {
				client.sendToClient(player1);
				num_players = 1;
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (num_players == 1) {
			try {
				client.sendToClient(player2);
				num_players = 2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Override
	protected void clientDisconnected(ConnectionToClient arg0) {
		this.stopListening();
	}

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		if (arg0 instanceof Move) {
			String[][] pieces = new String[8][8];
			int[][] teams = new int[8][8];
			System.out.println(arg0);
			System.out.println(arg1);
			Move mv = (Move) arg0;
			if (ce.validate(mv)) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						pieces[i][j] = ce.getBoard()[i][j].getName();
						teams[i][j] = ce.getBoard()[i][j].getTeam();
					}
				}
				this.sendToAllClients(new Response(pieces, teams));
			}
		}

		 if (arg0 instanceof LoginData)
		    {
			   try {
				arg1.sendToClient("START");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		       LoginData loginData = (LoginData)arg0;
				String[] login_data = databasefile.load_data();
				System.out.println("in login "+ x );
				
				 for(int i = 0; i < login_data.length; i++)
				  {
					 System.out.println(login_data[i]);
					  String[] temp = login_data[i].split("/");
					  System.out.println(temp[0]);
					  System.out.println(temp[1]);

					  if(!temp[1].contains(""+loginData.getUsername()) )
					  {
						 
						 if(!temp[1].contains(""+loginData.getPassword()) )
						 {
							 Error new_message = new Error("Password not exist", 2);
							 try
							{
								arg1.sendToClient(new_message);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
						 else
						 {
							Error new_message = new Error("Welcome", 6);
							try
							{
								arg1.sendToClient(new_message);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
					  }
					  else
						 {
							Error new_message = new Error("Welcomme", 6);
							try
							{
								arg1.sendToClient(new_message);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
					  
				  }
		       
		    }
		        else if (arg0 instanceof CreateAccountData)
		        {
		           
		           CreateAccountData creatAccountData = (CreateAccountData)arg0;
		         
		           try {
		        	   
		        	 
					databasefile.writeCredentials(creatAccountData.getUsername(), creatAccountData.getPassword());
					x = databasefile.checkCredentials(creatAccountData.getUsername(), creatAccountData.getPassword() + "/n");
					creatAccountData.setCheck(x);
					if(x)
					{
						arg1.sendToClient(new Error("Username Exists", 1));
								
						return;
					}
					else
					{
						arg1.sendToClient(new Error(" ", 5));
						
					}
					
					System.out.println("in server "+ x );
					//arg1.sendToClient(creatAccountData);
		           } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		           System.out.println("Username/Password    " + creatAccountData.getUsername() + "/" + creatAccountData.getPassword() + "/" + creatAccountData.getPassword());
		        }
	}
	
	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) 
	{
		running = false;
		System.out.println("Exception occurred while listening");
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
	  
	//Setter for database
	public void setDatabase(DatabaseFile database)
	{
		this.databasefile = database;
	}
	  
	public static void main(String[] args) {
		new CheckersServer(8300, 500);
	}
}
