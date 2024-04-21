package checkers_multiplayer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import checkers_client.*;


import java.awt.*;
import java.io.IOException;
import java.lang.Runnable;

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
		            board[i][j] = new CheckersPiece("Red", j, j, j, pos);
		        }
		        // Set up black pieces
		        else if ((i + j) % 2 == 0 && i > 4) {
		            board[i][j] = new CheckersPiece("Black", j, j, j, pos);
		        }
		        // Set up empty squares
		        else {
		            board[i][j] = new Piece("Empty", j, j, j, pos);
		        }
		    }

		    //instantiate two players 
		    player1 = new Player("player1", 0, board, player_1_pieces);
		    player2 = new Player("player2", 1, board, player_2_pieces);

		    this.ce = new CheckersEngine(board, player1, player2);

		    try {
		    	this.listen();
		    } catch (IOException e) {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
		    }
		}
	}
	
	public void setLog(JTextArea log) {
		this.log = log;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

	public JLabel getStatus() {
		return status;
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
		       System.out.print("dfasdfrfrf");
				//databasefile.checkLogin(loginData.getUserName(), loginData.getPassword());
				//boolean x = databasefile.checkLogin(loginData.getUserName(), loginData.getPassword());
				String[] login_data = databasefile.load_data();
				System.out.println("in login "+ x );
				
				 for(int i = 0; i < login_data.length; i++)
				  {
					 System.out.println(login_data[i]);
					  String[] temp = login_data[i].split("/");
					  System.out.println(temp[0]);
					  System.out.println(temp[1]);

					  if(!temp[1].contains(""+loginData.getUserName()) )
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
		        	   
		        	 
					databasefile.writeCredentials(creatAccountData.getUserName(), creatAccountData.getPassword());
					x = databasefile.checkCredentials(creatAccountData.getUserName(), creatAccountData.getPassword() + "/n");
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

		           System.out.println("Username/Password    " + creatAccountData.getUserName() + "/" + creatAccountData.getPassword() + "/" + creatAccountData.getrePassword());
		           
		        }

	}
	
	@Override
	protected void listeningException(Throwable exception) {

	}

	@Override
	protected void clientConnected(ConnectionToClient client) {

		System.out.println("Server Connected");
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

	public static void main(String[] args) {
		CheckersServer server = new CheckersServer(8300, 500);
	}
}
