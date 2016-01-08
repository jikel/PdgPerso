package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private JPanel pan;

	public MainFrame() {
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable");
		this.setSize(600, 180);
		
		this.add(pan);

	}

}
