package checkers_client;

import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ocsf.client.AbstractClient;
import checkers_multiplayer.Player;
import checkers_multiplayer.Peice;

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
		//Add your code here
	}
	
	public void set_id(int id) {
		this.id = id;
	}

	public int get_id() {
		return this.id;
	}

	public void send_move(int[] old_pos, int[] new_pos) {
		
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
