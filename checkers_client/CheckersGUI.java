package checkers_client;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import checkers_multiplayer.Peice;

public class CheckersGUI extends JFrame {
	
	public static int mouseX, mouseY;
	private CheckersClient client;
	private JLabel[][] labels = new JLabel[8][8];
	public int Old_pos[] = new int[2];
	@SuppressWarnings("rawtypes")
	Vector old_pos = new Vector();
	public int New_pos[] = new int[2];
	private int oldi, oldj = 0;
	public boolean isClicked = false;
	private boolean isClicked2 = false;
	private boolean[][] lblClicked = new boolean[8][8];
	private Thread thread;
	private checkers_client.InitialPanel view1;
	private JPanel view2;
	private CreateAccount view3;
	private InitialPanel view4;
	private JPanel container;
	
	public CheckersGUI(String title) {
		this.setTitle("Checkers");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout cl = new CardLayout();
		container = new JPanel(cl);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 800);
		panel.setLayout(null);

		int Xaxis = 0, Yaxis = -100;

		this.client = new CheckersClient(this, "127.0.0.1", 8300, cl, container);
		
		GameControl gc = new GameControl(labels, client, this);
		
		for(int i = 0; i < 8; i++) {
			Xaxis = 0;
			Yaxis = Yaxis + 100;
			for (int j = 0; j < 8; j++) {
				labels[i][j] = new JLabel("");
				labels[i][j].setBounds(Xaxis, Yaxis, 100, 100);
				labels[i][j].setOpaque(false);
				labels[i][j].setBackground(Color.red);

				Xaxis = Xaxis + 100;
				labels[i][j].addMouseListener(gc);

				panel.add(labels[i][j]);
			}
		}

		JLabel label = new JLabel("", JLabel.CENTER);
		label.setIcon(new ImageIcon(CheckersGUI.class.getResource("/checkers_client/7344419_orig.png")));
		label.setBounds(0, 0, 800, 800);
		view1 = new InitialPanel(cl, container);
		view2 = new LoginPanel(cl, container, client);
		view3 = new CreateAccount(cl, container, client);
		container.add(view1, "1");
		container.add(view2, "2");
		container.add(view3, "3");
		panel.add(label);
		panel.repaint();
		container.add(panel, "4");

		this.add(container, BorderLayout.CENTER);
		cl.show(container, "4");

		this.setSize(800, 800);
		this.setVisible(true);
	}
	
	//still need to implement certain parts of the game

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckersGUI gui = new CheckersGUI("");

	}

}

