import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminGUI extends Menu{
	
	public void adminGui() {
	userTypeFrame = new JFrame("Administrator Menu");
	userTypeFrame.setSize(400, 400);
	userTypeFrame.setLocation(200, 200);
	closeWindow();        
	userTypeFrame.setVisible(true);
	
	deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	deleteCustomer = new JButton("Delete Customer");	
	deleteCustomer.setPreferredSize(new Dimension(250, 20));
	deleteCustomerPanel.add(deleteCustomer);
	
	deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	deleteAccount = new JButton("Delete Account");
	deleteAccount.setPreferredSize(new Dimension(250, 20));	
	deleteAccountPanel.add(deleteAccount);
	
	bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	bankChargesButton = new JButton("Apply Bank Charges");
	bankChargesButton.setPreferredSize(new Dimension(250, 20));	
	bankChargesPanel.add(bankChargesButton);
	
	interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	interestButton = new JButton("Apply Interest");
	interestPanel.add(interestButton);
	interestButton.setPreferredSize(new Dimension(250, 20));

	editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	editCustomerButton = new JButton("Edit existing Customer");
	editCustomerPanel.add(editCustomerButton);
	editCustomerButton.setPreferredSize(new Dimension(250, 20));
	
	navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	navigateButton = new JButton("Navigate Customer Collection");
	navigatePanel.add(navigateButton);
	navigateButton.setPreferredSize(new Dimension(250, 20));
	
	summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	summaryButton = new JButton("Display Summary Of All Accounts");
	summaryPanel.add(summaryButton);
	summaryButton.setPreferredSize(new Dimension(250, 20));
	
	accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	accountButton = new JButton("Add an Account to a Customer");
	accountPanel.add(accountButton);
	accountButton.setPreferredSize(new Dimension(250, 20));
	
	returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	returnButton = new JButton("Exit Admin Menu");
	returnPanel.add(returnButton);
	}
}
