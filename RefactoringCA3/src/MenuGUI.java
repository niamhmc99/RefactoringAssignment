import java.awt.Container;
import java.awt.GridLayout;
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
 }
}
