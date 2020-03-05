import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Account extends Admin{

public void newAccount() {
	menu.userTypeFrame.dispose();
	
	if(customerList.isEmpty())
	{
		CustomerListEmpty();
	} else {
		boolean loop = true;
		boolean found = false;

		while(loop){
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
				} else if(reply == JOptionPane.NO_OPTION) {
					loop = false;
					button.returnAdmin();
				}
			} else {
				loop = false;
				String[] choices = { "Current Account", "Deposit Account" };
				String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
						"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]); 
	    
				if(account.equals("Current Account")){
					currentAccount();
				}else if(account.equals("Deposit Account")) {			    	
					depositAccount();
				}
			}			   
    	}
	}
}

	public void deleteAccount() {
		boolean found = true, loop = true;
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
	
	public void currentAccount() {
		boolean valid = true;
		double balance = 0;
		String number = String.valueOf("C" + (customerList.indexOf(menu.customer)+1) * 10 + (menu.customer.getAccounts().size()+1));
		ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
		int randomPIN = (int)(Math.random()*9000)+1000;
		String pin = String.valueOf(randomPIN);

		ATMCard atm = new ATMCard(randomPIN, valid);
		CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);
		menu.customer.getAccounts().add(current);
		JOptionPane.showMessageDialog(menu.userTypeFrame, "Account number = " + number +"\n PIN = " + pin  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
		button.returnAdmin();
	}
	
	public void depositAccount() {
		double balance = 0, interest = 0;
		String number = String.valueOf("D" + (customerList.indexOf(menu.customer)+1) * 10 + (menu.customer.getAccounts().size()+1));//this simple algorithm generates the account number
		ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
	
		CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);
		menu.customer.getAccounts().add(deposit);
		JOptionPane.showMessageDialog(menu.userTypeFrame, "Account number = " + number ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
		button.returnAdmin();
	}
}