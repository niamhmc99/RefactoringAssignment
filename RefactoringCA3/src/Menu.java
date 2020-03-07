import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Menu extends JFrame implements CommonWindowFunctions{
	
	protected static ArrayList<Customer> customerList = new ArrayList<Customer>();
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
		   //populating customerList for testing purpose
		// ArrayList<CustomerAccount> ca = new ArrayList<>(Arrays.asList(new CustomerDepositAccount(1.5,"D1234", 2000.0, new ArrayList<AccountTransaction>())));
		// ca.add(new CustomerCurrentAccount(new ATMCard(1234, true),"C1234",1000.0, new ArrayList<AccountTransaction>()));
		// ArrayList customerList = new ArrayList<>(Arrays.asList());
		 //driver.customerList.add(new Customer ("1234","Joe","Bloggs","11061998","ID1234","1234",ca));
		driver.test(); 
		driver.menuStart();
	}
	
	public void menuStart() {
		
		StartMenu start = new StartMenu();
		MenuGUI gui = new MenuGUI();
		gui.menuGui();
		final ButtonGroup userType = new ButtonGroup();
	}
	
	public void admin()
	{
		AdminGUI gui = new AdminGUI();
		gui.adminGui();
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
		CustomerGUI custContinued = new CustomerGUI();
		
		if(customer.getAccounts().size() == 0) {
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
			
			for (int i =0; i < customer.getAccounts().size(); i++) {
				box.addItem(customer.getAccounts().get(i).getNumber());
			}
			for(int i = 0; i<customer.getAccounts().size(); i++) {
				if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() ) {
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
	public void addCustomer(Customer customer) {
		this.customerList.add(customer);
	}

	public void test () {
		 //populating customerList for testing purpose
		 ArrayList<CustomerAccount> ca = new ArrayList<>(Arrays.asList(new CustomerDepositAccount(1.5,"D123", 10000.0, new ArrayList<AccountTransaction>())));
		 ca.add(new CustomerCurrentAccount(new ATMCard(1234, true),"C123",5000.0, new ArrayList<AccountTransaction>()));
		 //ArrayList customerList = new ArrayList<>(Arrays.asList());
		 customer =(new Customer ("1234","Mary","Doe","17031999","ID1234","1234567",ca));
		 addCustomer(customer);
	}
	@Override
	public void closeWindow() {
		userTypeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});		
	}
	
	@Override
	public boolean isNumeric(String str) {  
	  try  {  
	    double d = Double.parseDouble(str);  
	  } 
	  catch(NumberFormatException nfe) {  
	    return false;  
	  }  return true;  
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}