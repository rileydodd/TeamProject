package checkers_client;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import checkers_multiplayer.Piece;
import checkers_multiplayer.CheckersPiece;

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

		
		client = new CheckersClient(this, "10.252.161.60", 8300, cl, container);
		try {
			client.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		cl.show(container, "1");

		this.setSize(800, 800);
		this.setVisible(true);
	}
	
	public void drawBoard(String board[][], int[][] teams) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].equals("CheckersPiece") && teams[i][j] == 0) {
					labels[i][j].setIcon(new ImageIcon(CheckersGUI.class.getResource("/checkers_client/redPiece.png")));
				}
				/////////////////////////////////////////////////////////////////////////////////////////////

				else if (board[i][j].equals("CheckersPiece") && teams[i][j] == 1) {
					labels[i][j].setIcon(new ImageIcon(CheckersGUI.class.getResource("/chechers_client/blackPiece.png")));
				} else if (board[i][j].equals("empty slot")) {
					labels[i][j].setIcon(null);
				}
			}
		}
	}

	public void drawBoardReverse(String board[][], int[][] teams) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].equals("CheckersPiece") && teams[i][j] == 0) {
					//labels[7-i][7-j] = new JButton(new ImageIcon(checkersGUI.class.getResource("/chechers_client/redPiece.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(CheckersGUI.class.getResource("/chechers_client/redPiece.png")));
				} 
				/////////////////////////////////////////////////////////////////////////////////////////////

				else if (board[i][j].equals("CheckersPiece") && teams[i][j] == 1) {
					//labels[7-i][7-j]= new JButton(new ImageIcon(checkersGUI.class.getResource("/chechers_client/blackPiece.png")));
					labels[7 - i][7 - j].setIcon(new ImageIcon(CheckersGUI.class.getResource("/chechers_client/blackPiece.png")));
				} else if (board[i][j].equals("empty slot")) {
					labels[7 - i][7 - j].setIcon(null);
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckersGUI gui = new CheckersGUI("");
	}

}

