import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CustomerFunctionality extends JFrame implements CommonWindowFunctions{
	
	ArrayList<Customer> customerList = Menu.returnArray();
	Menu menu = new Menu();
	MenuButtons button = new MenuButtons();
	JLabel label;
	JPanel textPanel, returnPanel;
	JTextArea textArea;
	JButton returnButton;


	public void statement() {
			menu.userTypeFrame.dispose();
			menu.userTypeFrame = new JFrame("Customer Menu");
			menu.userTypeFrame.setSize(400, 600);
			menu.userTypeFrame.setLocation(200, 200);
			closeWindow();        
			menu.userTypeFrame.setVisible(true);
			
			label = new JLabel("Summary of account transactions: ");
			
			returnPanel = new JPanel();
			returnButton = new JButton("Return");
			returnPanel.add(returnButton);
			
			textPanel = new JPanel();
			
			textPanel.setLayout( new BorderLayout() );
			textArea = new JTextArea(40, 20);
			textArea.setEditable(false);
			textPanel.add(label, BorderLayout.NORTH);
			textPanel.add(textArea, BorderLayout.CENTER);
			textPanel.add(returnButton, BorderLayout.SOUTH);
			
			JScrollPane scrollPane = new JScrollPane(textArea);
			textPanel.add(scrollPane);
			
			for (int i = 0; i < menu.acc.getTransactionList().size(); i ++) {
				textArea.append(menu.acc.getTransactionList().get(i).toString());
			}
			
			textPanel.add(textArea);
			menu.content.removeAll();
			
			Container content = menu.userTypeFrame.getContentPane();
			content.setLayout(new GridLayout(1, 1));
			content.add(textPanel);
			
			returnButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
				button.returnButton();				
				}		
		     });										
		}	
   
	
	public void lodgement() {
		boolean loop = true;
		boolean on = true;
		double balance = 0;

		if( menu.acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount)  menu.acc).getAtm().getPin();
			loop = true;
			
			while(loop) {
				if(count == 0) {
					JOptionPane.showMessageDialog( menu.userTypeFrame, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount)  menu.acc).getAtm().setValid(false);
					 menu.customer( menu.customer); 
					loop = false;
					on = false;
				}
				String Pin = JOptionPane.showInputDialog( menu.userTypeFrame, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);
				
			   if(on) {
				if(checkPin == i) {
					loop = false;
					JOptionPane.showMessageDialog( menu.userTypeFrame, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
				} else {
					count --;
					JOptionPane.showMessageDialog( menu.userTypeFrame, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);					
				}
			  }
		   }
		}		
		
		if(on == true) {
			String balanceTest = JOptionPane.showInputDialog( menu.userTypeFrame, "Enter amount you wish to lodge:");//the isNumeric method tests to see if the string entered was numeric. 
			if(isNumeric(balanceTest)) {
				balance = Double.parseDouble(balanceTest);
				loop = false;
			} else {
				JOptionPane.showMessageDialog( menu.userTypeFrame, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		String euro = "\u20ac";
		menu.acc.setBalance( menu.acc.getBalance() + balance);
		Date date = new Date();
		String date2 = date.toString();
		String type = "Lodgement";
		double amount = balance;
	
		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		menu.acc.getTransactionList().add(transaction);
			
		JOptionPane.showMessageDialog( menu.userTypeFrame, balance + euro + " added do you account!" ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog( menu.userTypeFrame, "New balance = " + menu.acc.getBalance() + euro ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void withdraw() {
			boolean loop = true;
			boolean on = true;
			double withdraw = 0;

			if( menu.acc instanceof CustomerCurrentAccount) {
				int count = 3;
				int checkPin = ((CustomerCurrentAccount)  menu.acc).getAtm().getPin();
				loop = true;
				
				while(loop) {
					if(count == 0) {
						JOptionPane.showMessageDialog( menu.userTypeFrame, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
						((CustomerCurrentAccount)  menu.acc).getAtm().setValid(false);
						 menu.customer( menu.customer); 
						loop = false;
						on = false;
					}
					String Pin = JOptionPane.showInputDialog( menu.userTypeFrame, "Enter 4 digit PIN;");
					int i = Integer.parseInt(Pin);
					
				   if(on) {
					  if(checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog( menu.userTypeFrame, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
					  } else {
						count --;
						JOptionPane.showMessageDialog( menu.userTypeFrame, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);		
					  }
				  }
			   }
			}	
			
			if(on == true) {
				String balanceTest = JOptionPane.showInputDialog( menu.userTypeFrame, "Enter amount you wish to withdraw (max 500):");//the isNumeric method tests to see if the string entered was numeric. 
				
				if(isNumeric(balanceTest)) {
					withdraw = Double.parseDouble(balanceTest);
					loop = false;
				} else {
					JOptionPane.showMessageDialog( menu.userTypeFrame, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
				}
				
				if(withdraw > 500) {
					JOptionPane.showMessageDialog( menu.userTypeFrame, "500 is the maximum you can withdraw at a time." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					withdraw = 0;
				} if(withdraw >  menu.acc.getBalance()) {
					JOptionPane.showMessageDialog( menu.userTypeFrame, "Insufficient funds." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					withdraw = 0;					
				}
			
			 String euro = "\u20ac";
			 menu.acc.setBalance( menu.acc.getBalance()-withdraw);
			 Date date = new Date();
			 String date2 = date.toString();
			 
			 String type = "Withdraw";
			 double amount = withdraw;
	
			 AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			 menu.acc.getTransactionList().add(transaction);
				
			 JOptionPane.showMessageDialog( menu.userTypeFrame, withdraw + euro + " withdrawn." ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
			 JOptionPane.showMessageDialog( menu.userTypeFrame, "New balance = " +  menu.acc.getBalance() + euro ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
			}	
	
			returnButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					button.returnButton();								
				}
			});		
		}
	
	@Override
	public void closeWindow() {
    	menu.userTypeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});			
	}	
    
    @Override
	public boolean isNumeric(String str) {
    	  try {  
    		    double d = Double.parseDouble(str);  
    		  }  catch(NumberFormatException nfe){  
    			  		return false;  
    		  }   return true;  			
	}
}