package default_package;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Control_Panel {

	public void open_cpanel(){
		
	JFrame cpanel = new JFrame();
	JButton test = new JButton("Wow");
	
	Dimension b_dim = new Dimension(75, 40);
	

	cpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cpanel.setLocationRelativeTo(null);

	cpanel.setResizable(false);
	cpanel.setTitle("Sound Control Panel");
	
	test.setPreferredSize(b_dim);
	
	cpanel.add(test);
	cpanel.pack();
	
	cpanel.setVisible(true);
	}
}
