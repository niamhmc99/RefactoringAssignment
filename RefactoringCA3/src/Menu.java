import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Date;

public class Menu extends JFrame implements CloseWindow{
	
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
					
					if(user.equals("New Customer"))
					{
						userTypeFrame.dispose();		
						createNewCustomerFrame = new JFrame("Create New Customer");
						createNewCustomerFrame.setSize(400, 300);
						createNewCustomerFrame.setLocation(200, 200);
						createNewCustomerFrame.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent we) { System.exit(0); }
						});
							Container content = createNewCustomerFrame.getContentPane();
							content.setLayout(new BorderLayout());
							
							firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
							surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
							pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
							dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
							firstNameTextField = new JTextField(20);
							surnameTextField = new JTextField(20);
							pPSTextField = new JTextField(20);
							dOBTextField = new JTextField(20);
							JPanel panel = new JPanel(new GridLayout(6, 2));
							panel.add(firstNameLabel);
							panel.add(firstNameTextField);
							panel.add(surnameLabel);
							panel.add(surnameTextField);
							panel.add(pPPSLabel);
							panel.add(pPSTextField);
							panel.add(dOBLabel);
							panel.add(dOBTextField);
								
							panel2 = new JPanel();
							add = new JButton("Add");
							
							 add.addActionListener(new ActionListener() {
						
								 public void actionPerformed(ActionEvent e) {
									 
									 PPS = pPSTextField.getText();
									 firstName = firstNameTextField.getText();
									 surname = surnameTextField.getText();
									 DOB = dOBTextField.getText();
									 password="";
									 CustomerID = "ID"+PPS ;
						
						
							add.addActionListener(new ActionListener() {
							
								public void actionPerformed(ActionEvent e) {
								createNewCustomerFrame.dispose();
								
								boolean passwordCorrect = true;
								while(passwordCorrect){
								 password = JOptionPane.showInputDialog(userTypeFrame, "Enter 7 character Password;");
								
								 if(password.length() != 7)
								    {
								    	JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
								    }
								 else
								 {
									 passwordCorrect = false;
								 }
								}
								
							    ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
										Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);
											
										customerList.add(customer);
									
										JOptionPane.showMessageDialog(userTypeFrame, "CustomerID = " + CustomerID +"\n Password = " + password  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
										menuStart();
									userTypeFrame.dispose();
							}
						});	
								}
							});						
							cancel = new JButton("Cancel");					
							cancel.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									createNewCustomerFrame.dispose();
									menuStart();
								}
							});	
							
							panel2.add(add);
							panel2.add(cancel);
							
							content.add(panel, BorderLayout.CENTER);
							content.add(panel2, BorderLayout.SOUTH);
					
							createNewCustomerFrame.setVisible(true);		
						
					}
					
					
					
					if(user.equals("Administrator")	)
					{
						boolean checkAdminUsername = true, checkAdminPassword = true;
						boolean cont = false;
					    while(checkAdminUsername)
					    {
					    	Object adminUsername = JOptionPane.showInputDialog(userTypeFrame, "Enter Administrator Username:");

					    	if(!adminUsername.equals("admin"))//search admin list for admin with matching admin username
					    	{
					    		int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
					    		if (reply == JOptionPane.YES_OPTION) {
					    			checkAdminUsername = true;
					    		}
					    		else if(reply == JOptionPane.NO_OPTION)
					    		{
					    			createNewCustomerFrame.dispose();
					    			checkAdminUsername = false;
					    			checkAdminPassword = false;
					    			menuStart();
					    		}
					    }
					    else
					    {
					    	checkAdminUsername = false;
					    }				    
					    }
					    
					    while(checkAdminPassword)
					    {
					    	Object adminPassword = JOptionPane.showInputDialog(userTypeFrame, "Enter Administrator Password;");
					    	
					    	   if(!adminPassword.equals("admin11"))
							    {
							    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
							    	if (reply == JOptionPane.YES_OPTION) {
							    		
							    	}
							    	else if(reply == JOptionPane.NO_OPTION){
							    		createNewCustomerFrame.dispose();
							    		checkAdminPassword = false;
							    		menuStart();
							    	}
							    }
					    	   else
					    	   {
					    		   checkAdminPassword =false;
					    		   cont = true;
					    	   }
					    }
					    	
					    if(cont)
					    {
					    	createNewCustomerFrame.dispose();
					    	checkAdminUsername = false;
					    	admin();					    
					    }					    
					}
					
					if(user.equals("Customer")	)
					{
						boolean checkCustomerId = true, checkCustomerPassword = true, cont = false, found = false;
					    while(checkCustomerId)
					    {
					    	Object customerID = JOptionPane.showInputDialog(userTypeFrame, "Enter Customer ID:");
					    
					    	for (Customer aCustomer: customerList){
					    	
					    		if(aCustomer.getCustomerID().equals(customerID))//search customer list for matching customer ID
					    		{
					    			found = true;
					    			customer = aCustomer;
					    		}					    	
					    	}
					    
					    	if(found == false)
					    	{
					    		int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
					    		if (reply == JOptionPane.YES_OPTION) {
					    			checkCustomerId = true;
					    		}
					    	else if(reply == JOptionPane.NO_OPTION)
					    	{
					    		userTypeFrame.dispose();
					    		checkCustomerId = false;
					    		checkCustomerPassword = false;
					    		menuStart();
					    	}
					    	}
					    	else
					    	{
					    		checkCustomerId = false;
					    	}
					   
					   }
					    
					    while(checkCustomerPassword)
					    {
					    	Object customerPassword = JOptionPane.showInputDialog(userTypeFrame, "Enter Customer Password;");
					    	
					    	   if(!customer.getPassword().equals(customerPassword))
							    {
							    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
							    	if (reply == JOptionPane.YES_OPTION) {
							    		
							    	}
							    	else if(reply == JOptionPane.NO_OPTION){
							    		userTypeFrame.dispose();
							    		checkCustomerPassword = false;
							    		menuStart();
							    	}
							    }
					    	   else
					    	   {
					    		   checkCustomerPassword =false;
					    		   cont = true;
					    	   }
					    }
					    
					    if(cont)
					    {
					    	userTypeFrame.dispose();
					    	checkCustomerId = false;
					    	customer(customer);				    
					    }				    
					}
				}
			});
			userTypeFrame.setVisible(true);	
	}
	

	
	public void admin()
	{
		Admin admin = new Admin();
		MenuButtons button = new MenuButtons();
		dispose();
		
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
		} 
		else {
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

}