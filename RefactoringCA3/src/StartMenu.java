import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StartMenu extends Menu{
	Menu menu = new Menu();
	//ArrayList<Customer> customerList = menu.returnArray();
	CustomerAccount acc = Menu.returnAcc();
	MenuButtons button = new MenuButtons();
	MenuGUI menugui = new MenuGUI();
	
	public void newCustomerGUI() {

		createNewCustomerFrame = new JFrame("Create New Customer");
		createNewCustomerFrame.setSize(400, 300);
		createNewCustomerFrame.setLocation(200, 200);
	
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
			
			add.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					newCustomer();
				}
			});
									
			JButton cancel = new JButton("Cancel");					
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button.returnButton();
				}
			});	
			
			panel2.add(add);
			panel2.add(cancel);
			content.add(panel, BorderLayout.CENTER);
			content.add(panel2, BorderLayout.SOUTH);
			createNewCustomerFrame.setVisible(true);		
	}

	public void administrator() {
		boolean checkAdminUsername = true, checkAdminPassword = true;
		boolean cont = false;
	    while(checkAdminUsername) {
	    	Object adminUsername = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter Administrator Username:");

	    	if(!adminUsername.equals("admin")) {
	    		int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
	    		if (reply == JOptionPane.YES_OPTION) {
	    			checkAdminUsername = true;
	    		} else if(reply == JOptionPane.NO_OPTION) {
	    			menu.createNewCustomerFrame.dispose();
	    			checkAdminUsername = false;
	    			checkAdminPassword = false;
	    			button.returnButton();
	    		}
	    	} else {
	    		checkAdminUsername = false;
	    	}				    
	    }
	    
	    while(checkAdminPassword) {
	    	Object adminPassword = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter Administrator Password;");
	    	   if(!adminPassword.equals("admin11")){
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		
			    	} else if(reply == JOptionPane.NO_OPTION){
			    		checkAdminPassword = false;
			    		button.returnButton();
			    	}
			    } else {
	    		   checkAdminPassword =false;
	    		   cont = true;
	    	   }
	    }
	    if(cont) {
	    	checkAdminUsername = false;
	    	button.returnAdmin();
	    }					    
	}
	
	public void existingCustomer() {
		Customer customer = null;
		boolean checkCustomerId = true, checkCustomerPassword = true, cont = false, found = false;
		//testCustomer();
		while(checkCustomerId){
	    	Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter Customer ID:");
	    	for (Customer aCustomer: menu.customerList){
	    		if(aCustomer.getCustomerID().equals(customerID)) {
	    			found = true;
	    			customer = aCustomer;
	    		}					    	
	    	}
	    	if(found == false) {
	    		int reply  = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
	    		if (reply == JOptionPane.YES_OPTION) {
	    			checkCustomerId = true;
	    		} else if(reply == JOptionPane.NO_OPTION) {
	    		checkCustomerId = false;
	    		checkCustomerPassword = false;
	    		button.returnButton();
	    		}
	    	}else{
	    		checkCustomerId = false;
	    	}
	   }
	    while(checkCustomerPassword){
	    	Object customerPassword = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter Customer Password;");
	    	   if(!customer.getPassword().equals(customerPassword)) {
			    	int reply  = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    	} else if(reply == JOptionPane.NO_OPTION){
			    		checkCustomerPassword = false;
			    		button.returnButton();
			    	}
			    } else {
	    		   checkCustomerPassword =false;
	    		   cont = true;
	    	   }
	    }
	    if(cont) {
	    	checkCustomerId = false;
	    	button.returnCustomer();				    
	    }				    
	}
	
	public void newCustomer() {
				 PPS = pPSTextField.getText();
				 firstName = firstNameTextField.getText();
				 surname = surnameTextField.getText();
				 DOB = dOBTextField.getText();
				 setPassword("");
				 CustomerID = "ID" + PPS;
		
						 boolean passwordCorrect = true;
						 while(passwordCorrect){
							 String password = JOptionPane.showInputDialog(userTypeFrame, "Enter 7 character Password;");
							 if(password.length() != 7) {
								 JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
							 }else {
								 passwordCorrect = false;
							 }
						 }
						 ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
						 Customer customer = new Customer(PPS, surname, firstName, DOB,
									CustomerID, getPassword(), accounts);							
						 
						 //customerList.add(customer);
						 addCustomer(customer);
						 JOptionPane.showMessageDialog(userTypeFrame, "CustomerID = " + CustomerID + "\n Password = " + getPassword(),
									"Customer created.", JOptionPane.INFORMATION_MESSAGE);
						 menuStart();
						 userTypeFrame.dispose();
	}
	
	public void closeWindow() {
			userTypeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});
	}
	
//	public ArrayList testCustomer() {
//		   //populating customerList for testing purpose
////	      ArrayList<CustomerAccount> ca = new ArrayList<>(Arrays.asList(new CustomerDepositAccount(1.5,"D1234", 2000.0, new ArrayList<AccountTransaction>())));
////	      ca.add(new CustomerCurrentAccount(new ATMCard(1234, true),"C1234",1000.0, new ArrayList<AccountTransaction>()));
////	      ArrayList customerList = new ArrayList<>(Arrays.asList(new Customer ("1234","Joe","Bloggs","11061998","ID1234","1234",ca)));
//	      //customerList.add(
//		return ;
//	}
}
