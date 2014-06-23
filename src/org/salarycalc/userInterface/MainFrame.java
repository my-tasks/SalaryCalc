package org.salarycalc.userInterface;

import javax.swing.JFrame;
import static org.salarycalc.userInterface.properties.UIConstants.*;

public class MainFrame extends JFrame {

	public MainFrame(String title) {
		setTitle(title);
		setBounds(X_MAIN, Y_MAIN, JFRAME_WIDTH, JFRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
