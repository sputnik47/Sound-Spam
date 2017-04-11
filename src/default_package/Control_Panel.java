package default_package;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Control_Panel extends JFrame {

	public void open_cpanel(){
	
	//variables
	File_Edit save_file = new File_Edit();
		
	JLabel label = new JLabel();//label for everything to go on
	
	JButton save = new JButton("Save");//buttons and etc
	JButton start = new JButton("Start");
	JCheckBox silent_startup = new JCheckBox("Silent Startup");
	
	JSlider volume = new JSlider(-6, 6, 0); //sliders
	JSlider interval = new JSlider(1, 120, 15);
	
	JLabel volume_label = new JLabel("Volume: 0.0f"); //labels
	JLabel interval_label = new JLabel("Every 15 Minutes");
	
	
	Dimension window_size = new Dimension(300, 200); //dimensions

	//frame initialization
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	setResizable(false);
	setTitle("Sound Control Panel");
	setSize(window_size);
	
	//location and size
	silent_startup.setBounds(10, 10, 125, 25);
	save.setBounds(10, 140, 80, 25);
	start.setBounds(205, 140, 80, 25);
	interval.setLocation(5, 45);
	interval.setSize(150, 40);
	interval_label.setBounds(165, 55, 125, 20);
	volume.setLocation(5, 90);
	volume.setSize(150, 40);
	volume_label.setBounds(165, 100, 100, 20);
	
	//adding onto frame
	add(label);
	label.add(silent_startup);
	label.add(interval);
	label.add(interval_label);
	label.add(save);
	label.add(start);
	label.add(volume);
	label.add(volume_label);
	
	//action events
	
	interval.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent ce) {
			if (interval.getValue() == 1)
				interval_label.setText("Every Minute");
			else
				interval_label.setText("Every " + interval.getValue() + " Minutes");
		}
	});
	
	volume.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent ce) {
			volume_label.setText("Volume: "+ volume.getValue() + ".0f");
		}
	});
	
	save.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.out.println(save_file.readSave(0));
		}
	});
	
	setVisible(true);
	}
	//methods
	

}
