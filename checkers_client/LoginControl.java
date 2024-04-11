package checkers_client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginControl implements ActionListener {

	private CardLayout cl;
	private JPanel container;
	private CheckersClient client;
	
	public LoginControl(CardLayout cl, JPanel container, CheckersClient client) {
		this.cl = cl;
		this.container = container;
		this.client = client;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent ae) {

		JButton command = (JButton) ae.getSource();
		int count = container.getComponentCount();
		LoginPanel lp = (LoginPanel) container.getComponent(1);

		if (command.getText().equals("Submit")) {
			String username = lp.getUserName().getText();
			String password = lp.getPassword().getText();
			LoginData loginData = new LoginData(username, password);
			try {
				client.sendToServer(loginData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (command.equals("Cancel")) {
			cl.show(container, "1");
		}
	}
}
