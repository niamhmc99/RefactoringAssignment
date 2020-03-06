import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StartMenu {
	
	ArrayList<Customer> customerList = Menu.returnArray();
	CustomerAccount acc = Menu.returnAcc();
	MenuButtons button = new MenuButtons();
	Menu menu = new Menu();
	
	public void newCustomer() {
	
		menu.userTypeFrame.dispose();		
		menu.createNewCustomerFrame = new JFrame("Create New Customer");
		menu.createNewCustomerFrame.setSize(400, 300);
		menu.createNewCustomerFrame.setLocation(200, 200);
		closeWindow();
			
		Container content = menu.createNewCustomerFrame.getContentPane();
		content.setLayout(new BorderLayout());
			menu.firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
			menu.surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
			menu.pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
			menu.dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
			menu.firstNameTextField = new JTextField(20);
			menu.surnameTextField = new JTextField(20);
			menu.pPSTextField = new JTextField(20);
			menu.dOBTextField = new JTextField(20);
			JPanel panel = new JPanel(new GridLayout(6, 2));
			panel.add(menu.firstNameLabel);
			panel.add(menu.firstNameTextField);
			panel.add(menu.surnameLabel);
			panel.add(menu.surnameTextField);
			panel.add(menu.pPPSLabel);
			panel.add(menu.pPSTextField);
			panel.add(menu.dOBLabel);
			panel.add(menu.dOBTextField);
				
			menu.panel2 = new JPanel();
			menu.add = new JButton("Add");
			
			menu.add.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
					 
					 menu.PPS = menu.pPSTextField.getText();
					 menu.firstName = menu.firstNameTextField.getText();
					 menu.surname = menu.surnameTextField.getText();
					 menu.DOB = menu.dOBTextField.getText();
					 menu.setPassword("");
					 menu.CustomerID = "ID" + menu.PPS;
		
					 menu.add.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent e) {
							 menu.createNewCustomerFrame.dispose();
				
							 boolean passwordCorrect = true;
							 while(passwordCorrect){
								 String password = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter 7 character Password;");
								 if(password.length() != 7) {
									 JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
								 }else {
									 passwordCorrect = false;
								 }
							 }
							 ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
							 Customer customer = new Customer(menu.PPS, menu.surname, menu.firstName, menu.DOB,
										menu.CustomerID, menu.getPassword(), accounts);							
							 customerList.add(customer);
							 JOptionPane.showMessageDialog(menu.userTypeFrame, "CustomerID = " + menu.CustomerID + "\n Password = " + menu.getPassword(),
										"Customer created.", JOptionPane.INFORMATION_MESSAGE);
							 menu.menuStart();
							 menu.userTypeFrame.dispose();
						 }
					 });	
				}
			});						
			JButton cancel = new JButton("Cancel");					
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button.returnButton();
				}
			});	
			
			menu.panel2.add(menu.add);
			menu.panel2.add(cancel);
			content.add(panel, BorderLayout.CENTER);
			content.add(menu.panel2, BorderLayout.SOUTH);
			menu.createNewCustomerFrame.setVisible(true);		
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
	    if(cont)
	    {
	    	checkAdminUsername = false;
	    	button.returnAdmin();
	    }					    
	}
	
	public void existingCustomer() {
		Customer customer = null;
		boolean checkCustomerId = true, checkCustomerPassword = true, cont = false, found = false;
		while(checkCustomerId){
	    	Object customerID = JOptionPane.showInputDialog(menu.userTypeFrame, "Enter Customer ID:");
	    	for (Customer aCustomer: customerList){
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
	public void closeWindow() {
		menu.userTypeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});
	}
}