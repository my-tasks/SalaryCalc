package org.salarycalc.userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import org.salarycalc.userInterface.properties.UIConstants;

public class CalculateRuleAdd extends MainFrame {
	private JPanel addPanel = new JPanel();
	private JRadioButtonMenuItem global = new JRadioButtonMenuItem();

	public CalculateRuleAdd() {
		super(UIConstants.CALCULATE_RULE_ADD_NAME);
		addPanel.setBounds(200, 100, 900, 600);
		addPanel.setVisible(true);
		global.setBounds(20, 20, 20, 40);

		addPanel.add(global);
		this.add(addPanel);
	}

}
