package checkers_client;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GameControl implements MouseListener {

	private JLabel[][] labels;
	private CheckersClient client;
	private CheckersGUI gui;
	
	public GameControl(JLabel[][] labels2, CheckersClient client, CheckersGUI gui) {
		this.labels = labels2;
		this.client = client;
		this.gui = gui;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	private void setMousePosition(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
