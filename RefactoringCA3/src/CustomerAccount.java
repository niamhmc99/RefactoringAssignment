import java.util.ArrayList; 

public class CustomerAccount  {
   
	String number;
	double balance;
	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

	public CustomerAccount(){
		this.number = "";
		this.balance = 0;
		this.transactionList = null;
	}
	
	public CustomerAccount(String number, double balance, ArrayList<AccountTransaction> transactionList){
		this.number = number;
		this.balance = balance;
		this.transactionList = transactionList;
	}
		
	public String getNumber(){
		return this.number;
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public ArrayList<AccountTransaction> getTransactionList(){
		return this.transactionList;
	}

	public void setNumber(String number){
		this.number = number;
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	public void setTransactionList(ArrayList<AccountTransaction> transactionList){
		this.transactionList = transactionList;
	}
}