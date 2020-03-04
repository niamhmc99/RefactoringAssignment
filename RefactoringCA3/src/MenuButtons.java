  
import javax.swing.JFrame;

public class MenuButtons extends JFrame {
	private static final long serialVersionUID = 1L;

	public void returnButton() {
		Menu menu1 = new Menu();
		Menu.jFrame.dispose();
		menu1.menuStart();
	}

	public void returnAdmin() {
		Menu menu1 = new Menu();
		Menu.jFrame.dispose();
		menu1.admin();
	}

	public void returnCustomer() {
		Menu menu1 = new Menu();
		Menu.jFrame.dispose();
		menu1.customer(menu1.customer);
	}

}