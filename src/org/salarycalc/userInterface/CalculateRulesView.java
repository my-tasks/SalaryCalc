package org.salarycalc.userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import static org.salarycalc.userInterface.properties.UIConstants.*;

public class CalculateRulesView extends MainFrame {
	private JButton addBotton = new JButton(BUTTON_ADD);

	public CalculateRulesView() {
		super(CALCULATE_RULE_VIEW_NAME);
		JPanel panel = new JPanel(null);
		addBotton.setBounds(20, 20, 100, 20);
		addBotton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CalculateRuleAdd();
			}
		});
		panel.add(addBotton);
		this.add(panel);
	}

}
