import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerContinued {
	JLabel label;
	JPanel statementPanel, lodgementPanel, withdrawalPanel, returnPanel;
	JButton  statementButton, lodgementButton, withdrawButton, returnButton;
	
	public void customerContinue() {
		
		Menu menu = new Menu();
		MenuButtons button = new MenuButtons();
		CustomerFunctionality custFunctionality = new CustomerFunctionality();

			menu.userTypeFrame.dispose();
			menu.userTypeFrame = new JFrame("Customer Menu");
			menu.userTypeFrame.setSize(400, 300);
			menu.userTypeFrame.setLocation(200, 200);
			
			menu.userTypeFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});          
			menu.userTypeFrame.setVisible(true);
	
			menu.statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			menu.statementButton = new JButton("Display Bank Statement");
			menu.statementButton.setPreferredSize(new Dimension(250, 20));
	
			menu.statementPanel.add(statementButton);
			
			menu.lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			menu.lodgementButton = new JButton("Lodge money into account");
			menu.lodgementPanel.add(lodgementButton);
			menu.lodgementButton.setPreferredSize(new Dimension(250, 20));
	
			menu.withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			menu.withdrawButton = new JButton("Withdraw money from account");
			menu.withdrawalPanel.add(withdrawButton);
			menu.withdrawButton.setPreferredSize(new Dimension(250, 20));
		
			menu.returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			menu.returnButton = new JButton("Exit Customer Menu");
			menu.returnPanel.add(returnButton);

			label = new JLabel("Please select an option");
	
			menu.content = menu.userTypeFrame.getContentPane();
			menu.content.setLayout(new GridLayout(5, 1));
			menu.content.add(label);
			menu.content.add(statementPanel);
			menu.content.add(lodgementPanel);
			menu.content.add(withdrawalPanel);
			menu.content.add(returnPanel);
			
			statementButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					custFunctionality.statement();
				}
			});

			lodgementButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					custFunctionality.lodgement();
				}
			});

			withdrawButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					custFunctionality.withdraw();
				}
			});

			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					button.returnCustomer();
				}
			});
	}

}
