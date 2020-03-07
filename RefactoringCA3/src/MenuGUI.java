import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuGUI extends Menu {
	

	public void menuGui() {

		userTypeFrame = new JFrame("User Type");
		userTypeFrame.setSize(400, 300);
		userTypeFrame.setLocation(200, 200);
		closeWindow();
		userTypePanel = new JPanel();
		
		ButtonGroup userType = new ButtonGroup();
		JRadioButton radioButton;
		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);
		
		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);
		
		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		continuePanel = new JPanel();
		continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = userTypeFrame.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		StartMenu start = new StartMenu();
		continueButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand(  );
				
				if(user.equals("New Customer")){
					start.newCustomerGUI();
				}
				if(user.equals("Administrator")) {
					start.administrator();
				}
				if(user.equals("Customer")){
					start.existingCustomer();
				}
			}
		});
		userTypeFrame.setVisible(true);	
	}
}
