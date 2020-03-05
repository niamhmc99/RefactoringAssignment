import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Menu extends JFrame implements CommonWindowFunctions{
	
	private static ArrayList<Customer> customerList;
    int position = 0;
	Customer customer;
	static CustomerAccount acc = new CustomerAccount();
	MenuButtons button = new MenuButtons();
	JTextArea textArea;
	static JFrame jFrame;
	JFrame userTypeFrame;
	JFrame createNewCustomerFrame;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel, label1, label;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	Container content;
	JPanel panel2, userTypePanel, continuePanel, deleteCustomerPanel, deleteAccountPanel, bankChargesPanel, interestPanel, editCustomerPanel, navigatePanel, summaryPanel, accountPanel, returnPanel,labelPanel, boxPanel, buttonPanel, textPanel,cancelPanel, statementPanel, withdrawalPanel, lodgementPanel,gridPanel ;
	JButton add, continueButton, cancel,deleteCustomer, deleteAccount, bankChargesButton, interestButton, editCustomerButton, navigateButton, summaryButton, accountButton, returnButton, saveButton, withdrawButton, lodgementButton, statementButton, first, previous, next, last; 
	String 	PPS,firstName,surname,DOB,CustomerID, password;
	
	public static void main(String[] args)
	{
		Menu driver = new Menu();
		driver.menuStart();
	}
	
	public void menuStart()
	{
		StartMenu start = new StartMenu();

			userTypeFrame = new JFrame("User Type");
			userTypeFrame.setSize(400, 300);
			userTypeFrame.setLocation(200, 200);
			closeWindow();
			userTypePanel = new JPanel();
			final ButtonGroup userType = new ButtonGroup();
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

			continueButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					String user = userType.getSelection().getActionCommand(  );
					
					if(user.equals("New Customer")){
						start.newCustomer();
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
	
	public void admin()
	{
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

		label1 = new JLabel("Please select an option");
		content = userTypeFrame.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);	
		content.add(deleteCustomerPanel);
		content.add(returnPanel);
		adminActionListener();
	}
	
	private void adminActionListener() {
		Admin admin = new Admin();
		bankChargesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.bankCharges();
			}
		});
		interestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.interest();
			}
		});
		editCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.editCustomer();
			}
		});
		summaryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.summary();
			}
		});
		navigateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.navigate();
			}
		});
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.createAccount();
			}
		});
		deleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.deleteCustomer();
			}
		});
		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				admin.deleteAccount();
			}
		});
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				button.returnButton();
			}
		});		
	}

	public void customer(Customer e) {	
		CustomerContinued custContinued = new CustomerContinued();
		userTypeFrame = new JFrame("Customer Menu");
		
		userTypeFrame.setSize(400, 300);
		userTypeFrame.setLocation(200, 200);
		closeWindow();         
		userTypeFrame.setVisible(true);
		
		if(customer.getAccounts().size() == 0)
		{
			JOptionPane.showMessageDialog(userTypeFrame, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			userTypeFrame.dispose();				
			menuStart();
		} else {
			buttonPanel = new JPanel();
			boxPanel = new JPanel();
			labelPanel = new JPanel();
		
			label = new JLabel("Select Account:");
			labelPanel.add(label);
		
			returnButton = new JButton("Return");
			buttonPanel.add(returnButton);
			continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);
		
			JComboBox<String> box = new JComboBox<String>();
			
			for (int i =0; i < customer.getAccounts().size(); i++)
			{
				box.addItem(customer.getAccounts().get(i).getNumber());
			}
			for(int i = 0; i<customer.getAccounts().size(); i++)
			{
				if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
	    		{
					acc = customer.getAccounts().get(i);
	    		}
			}
		boxPanel.add(box);
		content = userTypeFrame.getContentPane();
		content.setLayout(new GridLayout(3, 1));
		content.add(labelPanel);
		content.add(boxPanel);
		content.add(buttonPanel);
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				button.returnButton();				
			}		
	     });
		
		continueButton.addActionListener(new ActionListener(  ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				custContinued.customerContinue();
			}
		});
	}
	}
	
	public static CustomerAccount returnAcc() {
		return (acc);
	}
	
	public static ArrayList<Customer> returnArray() {
		return customerList;
	}

	@Override
	public void closeWindow() {
		userTypeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});		
	}
	
	@Override
	public boolean isNumeric(String str) {  
	  try  {  
	    double d = Double.parseDouble(str);  
	  } 
	  catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}