package checkers_multiplayer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import checkers_client.*;


import java.awt.*;
import java.io.IOException;
import java.lang.Runnable;

import java.util.*;

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
		
	}
	
	@Override
	protected void listeningException(Throwable exception) {

	}

	@Override
	protected void clientConnected(ConnectionToClient client) {

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
