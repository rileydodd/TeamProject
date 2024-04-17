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
	private CheckersGUI gui;
	private CardLayout cl;
	private JPanel container;
	private Player user;
	private int id;

	public CheckersClient(CheckersGUI clientGUI, String host_name, int port, CardLayout cl, JPanel container) {
		super(host_name, port);
		this.gui = clientGUI;
		this.cl = cl;
		this.container = container;
		
		try {
			this.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

    public void set_user(Player user) {
		this.user = user;
	}

	@Override
	public void handleMessageFromServer(Object arg0) {
		if (arg0 instanceof String) {
			if (arg0.equals("START")) {
				this.cl.show(this.container, "4");
			}
			if (arg0.equals("SUCCESS")) {
				this.cl.show(this.container, "1");
			}
		}
		//this will be recieved when logged in, server will return the player profile
		if (arg0 instanceof Player) {

			String[][] pieces = new String[8][8];
			int[][] teams = new int[8][8];
			Player p = (Player) arg0;
			this.set_user(p);
			this.set_id(p.getID());
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					pieces[i][j] = p.getBoard()[i][j].getName();
					teams[i][j] = p.getBoard()[i][j].getTeam();
				}
			}
			
			if (this.user.getID() == 0) {
				this.gui.drawBoard(pieces, teams);

			}
			//change perspectove if different player
			else {
				this.gui.drawBoardReverse(pieces, teams);
			}

		}

		if (arg0 instanceof Response) {

			Response res = (Response) arg0;
			//show board depending on perspective
			if (this.user.getID() == 0) {
				this.gui.drawBoard(res.pieces, res.teams);
			}
			//
			else {
				this.gui.drawBoardReverse(res.pieces, res.teams);
			}
		}
	}
	
	public void set_id(int id) {
		this.id = id;
	}

	public int get_id() {
		return this.id;
	}

	public void send_move(int[] old_pos, int[] new_pos) {
		Move mv = new Move(this.user.getID(), old_pos[0], old_pos[1], new_pos[0], new_pos[1]);

		try {
			this.sendToServer(mv);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connectionException(Throwable exception) {
		//Add your code here
	}

	@Override
	public void connectionClosed() {
		try {
			this.closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void connectionEstablished() {

	}

}
