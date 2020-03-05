import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Admin extends JFrame implements CommonWindowFunctions{

	private static final long serialVersionUID = 1L;
	ArrayList<Customer> customerList = Menu.returnArray();
	Menu menu = new Menu();
	MenuButtons button = new MenuButtons();
	CustomerAccount acc = Menu.returnAcc();
	JPanel boxPanel,buttonPanel, gridPanel, cancelPanel, textPanel, returnPanel;
	JButton continueButton,returnButton;
	JLabel label1;
	JTextArea textArea;
	JButton cancelBtn, saveBtn;

	public void bankCharges() {
			boolean loop = true;
			boolean found = false;
		
			if(customerList.isEmpty())
			{
				CustomerListEmpty();	
			} else {
				while(loop)
				{
					Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Customer ID of Customer You Wish to Apply Charges to:");
		    
					for (Customer aCustomer: customerList){
		    	
						if(aCustomer.getCustomerID().equals(customerID))
						{
							found = true;
							menu.customer = aCustomer; 
							loop = false;
						}					    	
					}
					if(found == false)
					{
						int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							loop = true;
						} else if(reply == JOptionPane.NO_OPTION){
							menu.userTypeFrame.dispose();
							loop = false;
							button.returnAdmin();
						}
					} else {
						menu.userTypeFrame.dispose();
						menu.userTypeFrame = new JFrame("Administrator Menu");
						menu.userTypeFrame.setSize(400, 300);
						menu.userTypeFrame.setLocation(200, 200);
						closeWindow();        
						menu.userTypeFrame.setVisible(true);
			
						JComboBox<String> box = new JComboBox<String>();
						for (int i =0; i < menu.customer.getAccounts().size(); i++)
						{
							box.addItem(menu.customer.getAccounts().get(i).getNumber());
						}
				
			    
			     box.getSelectedItem();
			     boxPanel = new JPanel();
				 boxPanel.add(box);

				 buttonPanel = new JPanel();
				 continueButton = new JButton("Apply Charge");
				 returnButton = new JButton("Return");
				 buttonPanel.add(continueButton);
				 buttonPanel.add(returnButton);
				 
				 Container content = menu.userTypeFrame.getContentPane();
			   	 content.setLayout(new GridLayout(2, 1));
				
			   	 content.add(boxPanel);
			   	 content.add(buttonPanel);
		
					if(menu.customer.getAccounts().isEmpty())
					{
						JOptionPane.showMessageDialog(menu.userTypeFrame, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
						menu.userTypeFrame.dispose();
						button.returnAdmin();
					} else {
					
						for(int i = 0; i < menu.customer.getAccounts().size(); i++)
						{
							if(menu.customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
							{
								acc = menu.customer.getAccounts().get(i);
							}
						}
					continueButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						String euro = "\u20ac";
	
						if(acc instanceof CustomerDepositAccount)
						{
							JOptionPane.showMessageDialog(menu.userTypeFrame, "25" + euro + " deposit account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
							acc.setBalance(acc.getBalance()-25);
							JOptionPane.showMessageDialog(menu.userTypeFrame, "New balance = " + acc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
						}

						if(acc instanceof CustomerCurrentAccount)
						{
							JOptionPane.showMessageDialog(menu.userTypeFrame, "15" + euro + " current account fee aplied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
							acc.setBalance(acc.getBalance()-25);
							JOptionPane.showMessageDialog(menu.userTypeFrame, "New balance = " + acc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
						}
						menu.userTypeFrame.dispose();				
						button.returnAdmin();			
					}		
			     });
				
					returnButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							button.returnButton();				
						}
					});	
				
					}
					}
				}
			}
		    }

	public void interest(){

			boolean loop = true;
			boolean found = false;
		
			if(customerList.isEmpty())
			{
				CustomerListEmpty();
				
			} else {
				while(loop)
				{
					Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Customer ID of Customer You Wish to Apply Interest to:");
		    
					for (Customer aCustomer: customerList){
		    	
						if(aCustomer.getCustomerID().equals(customerID))
						{
							found = true;
							menu.customer = aCustomer; 
							loop = false;
						}					    	
					}
					if(found == false)
					{
						int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							loop = true;
						} else if(reply == JOptionPane.NO_OPTION) {
							menu.userTypeFrame.dispose();
							loop = false;
							menu.admin();
						}
					} else {
						menu.userTypeFrame.dispose();
						menu.userTypeFrame = new JFrame("Administrator Menu");
						menu.userTypeFrame.setSize(400, 300);
						menu.userTypeFrame.setLocation(200, 200);
						closeWindow();                 
						menu.userTypeFrame.setVisible(true);
			
						JComboBox<String> box = new JComboBox<String>();
						for (int i =0; i < menu.customer.getAccounts().size(); i++)
						{
							box.addItem(menu.customer.getAccounts().get(i).getNumber());
						}
				
			    
			    box.getSelectedItem();
			    boxPanel = new JPanel();
				
				JLabel label = new JLabel("Select an account to apply interest to:");
				boxPanel.add(label);
				boxPanel.add(box);
				buttonPanel = new JPanel();
				continueButton = new JButton("Apply Interest");
				returnButton = new JButton("Return");
				buttonPanel.add(continueButton);
				buttonPanel.add(returnButton);
				Container content = menu.userTypeFrame.getContentPane();
				content.setLayout(new GridLayout(2, 1));
				
				content.add(boxPanel);
				content.add(buttonPanel);
				if(menu.customer.getAccounts().isEmpty())
					{
						CustomerAccountsEmpty();
					} else {
					
						for(int i = 0; i < menu.customer.getAccounts().size(); i++)
						{
							if(menu.customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
							{
								acc = menu.customer.getAccounts().get(i);
							}
						}
									
					continueButton.addActionListener(new ActionListener(  ) {
					
						public void actionPerformed(ActionEvent ae) {
							String euro = "\u20ac";
							double interest = 0;
							boolean loop = true;
					 	
					 	while(loop)
					 	{
					 		String interestString = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");//the isNumeric method tests to see if the string entered was numeric. 
					 			if(isNumeric(interestString)) {
					 				interest = Double.parseDouble(interestString);
					 				loop = false;
					 				acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest/100)));
					 				JOptionPane.showMessageDialog(menu.userTypeFrame, interest + "% interest applied. \n new balance = " + acc.getBalance() + euro ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
					 			} else {
					 				JOptionPane.showMessageDialog(menu.userTypeFrame, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					 			}
					 	} button.returnAdmin();				
					}		
			     });
				
				returnButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						button.returnButton();				
					}
			     });	
					}
				}
		    }
		  }
		}	
   
	

	public void editCustomer() {
			
			boolean loop = true;
			boolean found = false;
		
			if(customerList.isEmpty())
			{
				CustomerListEmpty();
			} else
			{
				while(loop)
				{
					Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter Customer ID:");
		    
					for (Customer aCustomer: customerList){
		    	
						if(aCustomer.getCustomerID().equals(customerID))
						{
							found = true;
							menu.customer = aCustomer;
						}					    	
					}
					if(found == false)
					{
						int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							loop = true;
						} else if(reply == JOptionPane.NO_OPTION) {
							menu.userTypeFrame.dispose();
							loop = false;
		    	
							menu.admin();
						}
					} else {
						loop = false;
					}
				}
			
			menu.userTypeFrame.dispose();
			
			menu.userTypeFrame.dispose();
			menu.userTypeFrame = new JFrame("Administrator Menu");
			menu.userTypeFrame.setSize(400, 300);
			menu.userTypeFrame.setLocation(200, 200);
			closeWindow();      
			
			menu.firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			menu.surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			menu.pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			menu.dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			menu.customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			menu.passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			menu.firstNameTextField = new JTextField(20);
			menu.surnameTextField = new JTextField(20);
			menu.pPSTextField = new JTextField(20);
			menu.dOBTextField = new JTextField(20);
			menu.customerIDTextField = new JTextField(20);
			menu.passwordTextField = new JTextField(20);
			
			textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
			cancelPanel = new JPanel();
			
			textPanel.add(menu.firstNameLabel);
			textPanel.add(menu.firstNameTextField);
			textPanel.add(menu.surnameLabel);
			textPanel.add(menu.surnameTextField);
			textPanel.add(menu.pPPSLabel);
			textPanel.add(menu.pPSTextField);
			textPanel.add(menu.dOBLabel);
			textPanel.add(menu.dOBTextField);
			textPanel.add(menu.customerIDLabel);
			textPanel.add(menu.customerIDTextField);
			textPanel.add(menu.passwordLabel);
			textPanel.add(menu.passwordTextField);

			menu.firstNameTextField.setText(menu.customer.getFirstName());
			menu.surnameTextField.setText(menu.customer.getSurname());
			menu.pPSTextField.setText(menu.customer.getPPS());
			menu.dOBTextField.setText(menu.customer.getDOB());
			menu.customerIDTextField.setText(menu.customer.getCustomerID());
			menu.passwordTextField.setText(menu.customer.getPassword());	

			 saveBtn = new JButton("Save");
			 cancelBtn = new JButton("Exit");
			
			cancelPanel.add(cancelBtn, BorderLayout.SOUTH);
			cancelPanel.add(saveBtn, BorderLayout.SOUTH);
			
			Container content = menu.userTypeFrame.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(textPanel, BorderLayout.NORTH);
			content.add(cancelPanel, BorderLayout.SOUTH);
			
			menu.userTypeFrame.setContentPane(content);
			menu.userTypeFrame.setSize(340, 350);
			menu.userTypeFrame.setLocation(200, 200);
			menu.userTypeFrame.setVisible(true);
			menu.userTypeFrame.setResizable(false);
			
			saveBtn.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					menu.customer.setFirstName(menu.firstNameTextField.getText());
					menu.customer.setSurname(menu.surnameTextField.getText());
					menu.customer.setPPS(menu.pPSTextField.getText());
					menu.customer.setDOB(menu.dOBTextField.getText());
					menu.customer.setCustomerID(menu.customerIDTextField.getText());
					menu.customer.setPassword(menu.passwordTextField.getText());		
					JOptionPane.showMessageDialog(null, "Changes Saved.");
				}		
			});
			
			cancelBtn.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					button.returnAdmin();				
				}		
		     });		
			}}

	
	public void summary() {
		menu.userTypeFrame.dispose();
		menu.userTypeFrame = new JFrame("Summary of Transactions");
		menu.userTypeFrame.setSize(400, 700);
		menu.userTypeFrame.setLocation(200, 200);
		closeWindow();         
		menu.userTypeFrame.setVisible(true);
			
			label1 = new JLabel("Summary of all transactions: ");
			
			returnPanel = new JPanel();
			returnButton = new JButton("Return");
			returnPanel.add(returnButton);
			
			textPanel = new JPanel();
			textPanel.setLayout( new BorderLayout() );
			textArea = new JTextArea(40, 20);
			textArea.setEditable(false);
			textPanel.add(label1, BorderLayout.NORTH);
			textPanel.add(textArea, BorderLayout.CENTER);
			textPanel.add(returnButton, BorderLayout.SOUTH);
			
			JScrollPane scrollPane = new JScrollPane(textArea);
			textPanel.add(scrollPane);
			
			for (int a = 0; a < customerList.size(); a++)//For each customer, for each account, it displays each transaction.
			{
				for (int b = 0; b < customerList.get(a).getAccounts().size(); b ++ )
				{
					acc = customerList.get(a).getAccounts().get(b);
					for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++)
					{
						textArea.append(acc.getTransactionList().get(c).toString());						
					}				
				}				
			}
			
			textPanel.add(textArea);			
			
			Container content = menu.userTypeFrame.getContentPane();
			content.setLayout(new GridLayout(1, 1));
			content.add(textPanel);
			
			returnButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					button.returnAdmin();				
				}		
		     });	
		}	
	
	public void navigate() {
		menu.userTypeFrame.dispose();
			
			if(customerList.isEmpty())
			{
				CustomerListEmpty();
			} else {
				
			JButton first, previous, next, last, cancel;

			Container content = getContentPane();
			content.setLayout(new BorderLayout());
			
			buttonPanel = new JPanel();
			gridPanel = new JPanel(new GridLayout(8, 2));
			cancelPanel = new JPanel();
							
			menu.firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			menu.surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			menu.pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			menu.dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			menu.customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			menu.passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			menu.firstNameTextField = new JTextField(20);
			menu.surnameTextField = new JTextField(20);
			menu.pPSTextField = new JTextField(20);
			menu.dOBTextField = new JTextField(20);
			menu.customerIDTextField = new JTextField(20);
			menu.passwordTextField = new JTextField(20);
			
			first = new JButton("First");
			previous = new JButton("Previous");
			next = new JButton("Next");
			last = new JButton("Last");
			cancel = new JButton("Cancel");
			
			menu.firstNameTextField.setText(customerList.get(0).getFirstName());
			menu.surnameTextField.setText(customerList.get(0).getSurname());
			menu.pPSTextField.setText(customerList.get(0).getPPS());
			menu.dOBTextField.setText(customerList.get(0).getDOB());
			menu.customerIDTextField.setText(customerList.get(0).getCustomerID());
			menu.passwordTextField.setText(customerList.get(0).getPassword());
			
			menu.firstNameTextField.setEditable(false);
			menu.surnameTextField.setEditable(false);
			menu.pPSTextField.setEditable(false);
			menu.dOBTextField.setEditable(false);
			menu.customerIDTextField.setEditable(false);
			menu.passwordTextField.setEditable(false);
			
			gridPanel.add(menu.firstNameLabel);
			gridPanel.add(menu.firstNameTextField);
			gridPanel.add(menu.surnameLabel);
			gridPanel.add(menu.surnameTextField);
			gridPanel.add(menu.pPPSLabel);
			gridPanel.add(menu.pPSTextField);
			gridPanel.add(menu.dOBLabel);
			gridPanel.add(menu.dOBTextField);
			gridPanel.add(menu.customerIDLabel);
			gridPanel.add(menu.customerIDTextField);
			gridPanel.add(menu.passwordLabel);
			gridPanel.add(menu.passwordTextField);
			
			buttonPanel.add(first);
			buttonPanel.add(previous);
			buttonPanel.add(next);
			buttonPanel.add(last);
			
			cancelPanel.add(cancel);
	
			content.add(gridPanel, BorderLayout.NORTH);
			content.add(buttonPanel, BorderLayout.CENTER);
			content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
			
			first.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					menu.position = 0;
					menu.firstNameTextField.setText(customerList.get(0).getFirstName());
					menu.surnameTextField.setText(customerList.get(0).getSurname());
					menu.pPSTextField.setText(customerList.get(0).getPPS());
					menu.dOBTextField.setText(customerList.get(0).getDOB());
					menu.customerIDTextField.setText(customerList.get(0).getCustomerID());
					menu.passwordTextField.setText(customerList.get(0).getPassword());				
						}		
				     });
			
			previous.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					if(menu.position < 1)
					{
					} else {
						menu.position = menu.position - 1;
				
						menu.firstNameTextField.setText(customerList.get(menu.position).getFirstName());
						menu.surnameTextField.setText(customerList.get(menu.position).getSurname());
						menu.pPSTextField.setText(customerList.get(menu.position).getPPS());
						menu.dOBTextField.setText(customerList.get(menu.position).getDOB());
						menu.customerIDTextField.setText(customerList.get(menu.position).getCustomerID());
						menu.passwordTextField.setText(customerList.get(menu.position).getPassword());	
					}			
				}		
			});
			
			next.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					if(menu.position == customerList.size()-1)
					{
					} else {
						menu.position = menu.position + 1;
						
						menu.firstNameTextField.setText(customerList.get(menu.position).getFirstName());
						menu.surnameTextField.setText(customerList.get(menu.position).getSurname());
						menu.pPSTextField.setText(customerList.get(menu.position).getPPS());
						menu.dOBTextField.setText(customerList.get(menu.position).getDOB());
						menu.customerIDTextField.setText(customerList.get(menu.position).getCustomerID());
						menu.passwordTextField.setText(customerList.get(menu.position).getPassword());	
					}		
				}		
			});
			
			last.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
				
					menu.position = customerList.size() - 1;
			
					menu.firstNameTextField.setText(customerList.get(menu.position).getFirstName());
					menu.surnameTextField.setText(customerList.get(menu.position).getSurname());
					menu.pPSTextField.setText(customerList.get(menu.position).getPPS());
					menu.dOBTextField.setText(customerList.get(menu.position).getDOB());
					menu.customerIDTextField.setText(customerList.get(menu.position).getCustomerID());
					menu.passwordTextField.setText(customerList.get(menu.position).getPassword());								
						}		
				     });
			
			cancel.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {				
					button.returnAdmin();
				}		
			});		
			
			setContentPane(content);
			setSize(400, 300);
		    setVisible(true);
			}		
		}

	
	public void createAccount() {
			menu.userTypeFrame.dispose();
			if(customerList.isEmpty())
			{
				CustomerListEmpty();
			} else {
				boolean loop = true;
				boolean found = false;
		
			while(loop)
		    {
				Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Customer ID of Customer You Wish to Add an Account to:");
		    
					for (Customer aCustomer: customerList){
						if(aCustomer.getCustomerID().equals(customerID))
						{
							found = true;
							menu.customer = aCustomer; 	
						}					    	
					}
					if(found == false){
						int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
							
						if (reply == JOptionPane.YES_OPTION) {
							loop = true;
						}
						else if(reply == JOptionPane.NO_OPTION)
						{
							menu.userTypeFrame.dispose();
							loop = false;
							menu.admin();
						}
					} else {
						loop = false;
						String[] choices = { "Current Account", "Deposit Account" };
						String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
								"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]); 
			    
						if(account.equals("Current Account"))
						{
							boolean valid = true;
							double balance = 0;
							String number = String.valueOf("C" + (customerList.indexOf(menu.customer)+1) * 10 + (menu.customer.getAccounts().size()+1));//this simple algorithm generates the account number
							ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
							int randomPIN = (int)(Math.random()*9000)+1000;
							String pin = String.valueOf(randomPIN);
			    
							ATMCard atm = new ATMCard(randomPIN, valid);
							CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);
			    	
							menu.customer.getAccounts().add(current);
							JOptionPane.showMessageDialog(menu.userTypeFrame, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
							menu.userTypeFrame.dispose();
							menu.admin();
						}
						if(account.equals("Deposit Account"))
						{			    	
							double balance = 0, interest = 0;
							String number = String.valueOf("D" + (customerList.indexOf(menu.customer)+1) * 10 + (menu.customer.getAccounts().size()+1));//this simple algorithm generates the account number
							ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
			        	
							CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);
							menu.customer.getAccounts().add(deposit);
							JOptionPane.showMessageDialog(menu.userTypeFrame, "Account number = " + number ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
			    		
							menu.userTypeFrame.dispose();
							menu.admin();
						}
					}			   
		    	}
			}
		}

		public void deleteCustomer() {
			boolean found = true;
			
			if(customerList.isEmpty())
			{
				CustomerListEmpty();
			} else {
				 {
					    Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Customer ID of Customer You Wish to Delete:");
					    
					    for (Customer aCustomer: customerList){
					    	
					    	if(aCustomer.getCustomerID().equals(customerID))
					    	{
					    		found = true;
								menu.customer = aCustomer;
					    	}					    	
					    }
					    
					    if(found == false)
					    {
					    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
					    	if (reply == JOptionPane.YES_OPTION) {
					    	} else if(reply == JOptionPane.NO_OPTION){
					    		menu.userTypeFrame.dispose();
					    		menu.admin();
					    	}
					    } else {
					    	if(menu.customer.getAccounts().size()>0)
					    	{
					    		JOptionPane.showMessageDialog(menu.userTypeFrame, "This customer has accounts. \n You must delete a customer's accounts before deleting a customer " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					    	} else {
					    		customerList.remove(menu.customer);
					    		JOptionPane.showMessageDialog(menu.userTypeFrame, "Customer Deleted " ,"Success.",  JOptionPane.INFORMATION_MESSAGE);
					    	}
					    }
				 }  
				 }
			}
	
	
			public void deleteAccount() {
				boolean found = true, loop = true;
					{
					    Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Customer ID of Customer from which you wish to delete an account");
					    for (Customer aCustomer: customerList){
					    	if(aCustomer.getCustomerID().equals(customerID))
					    	{
					    		found = true;
					    		menu.customer = aCustomer; 
					    		loop = false;
					    	}					    	
					    }
					    if(found == false)
					    {
					    	int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
					    	if (reply == JOptionPane.YES_OPTION) {
					    		loop = true;
					    	} else if(reply == JOptionPane.NO_OPTION) {
					    		menu.userTypeFrame.dispose();
					    		loop = false;
					    		menu.admin();
					    	}
					    }  
					}
			}
				
	

	public void CustomerListEmpty() {
		JOptionPane.showMessageDialog(Menu.jFrame, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
		button.returnAdmin();
	}

	public void CustomerAccountsEmpty() {
		JOptionPane.showMessageDialog(Menu.jFrame,
			"This customer has no accounts! \n The admin must add acounts to this customer.", "Oops!",
			JOptionPane.INFORMATION_MESSAGE);
		button.returnAdmin();
	}
	
	@Override
	public boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	@Override
	public void closeWindow() {
		menu.userTypeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}
}