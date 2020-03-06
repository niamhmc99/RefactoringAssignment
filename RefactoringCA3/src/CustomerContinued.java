import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerContinued extends Menu{
	
	public void customerContinue() {	
		Menu menu = new Menu();
		MenuButtons button = new MenuButtons();
		CustomerFunctionality custFunctionality = new CustomerFunctionality();

			userTypeFrame.dispose();
			userTypeFrame = new JFrame("Customer Menu");
			userTypeFrame.setSize(400, 300);
			userTypeFrame.setLocation(200, 200);
			closeWindow();         
			userTypeFrame.setVisible(true);
	
			statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			statementButton = new JButton("Display Bank Statement");
			statementButton.setPreferredSize(new Dimension(250, 20));
	
			statementPanel.add(statementButton);
			
			lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			lodgementButton = new JButton("Lodge money into account");
			lodgementPanel.add(lodgementButton);
			lodgementButton.setPreferredSize(new Dimension(250, 20));
	
			withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			withdrawButton = new JButton("Withdraw money from account");
			withdrawalPanel.add(withdrawButton);
			withdrawButton.setPreferredSize(new Dimension(250, 20));
		
			returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			returnButton = new JButton("Exit Customer Menu");
			returnPanel.add(returnButton);

			label = new JLabel("Please select an option");
			content = menu.userTypeFrame.getContentPane();
			content.setLayout(new GridLayout(5, 1));
			content.add(label);
			content.add(statementPanel);
			content.add(lodgementPanel);
			content.add(withdrawalPanel);
			content.add(returnPanel);
			
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