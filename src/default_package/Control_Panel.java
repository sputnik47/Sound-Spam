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
	Save_Edit save = new Save_Edit();
	
	String default_sound = "sample_sound.wav";
	String current_sound = save.readSave(3);
	System.out.println(current_sound);
	Sound audio = new Sound();
		
	JLabel label = new JLabel();//label for everything to go on
	
	JButton save_b = new JButton("Save");//buttons and etc
	JButton start_b = new JButton("Start");
	JButton t_sound_b = new JButton("Play");
	JButton set_sound_b = new JButton("Select Audio");
	JCheckBox silent_startup = new JCheckBox("Silent Startup");
	
	JSlider volume = new JSlider(-24, 6, 0); //sliders
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
	save_b.setBounds(10, 140, 80, 25);
	start_b.setBounds(205, 140, 80, 25);
	interval.setLocation(5, 45);
	interval.setSize(150, 40);
	interval_label.setBounds(165, 55, 125, 20);
	volume.setLocation(5, 90);
	volume.setSize(100, 40);
	volume_label.setBounds(115, 100, 100, 20);
	t_sound_b.setBounds(205, 100, 80, 25);
	set_sound_b.setBounds(155, 10, 130, 25);
	
	//adding onto frame
	add(label);
	label.add(silent_startup);
	label.add(interval);
	label.add(interval_label);
	label.add(save_b);
	label.add(start_b);
	label.add(volume);
	label.add(volume_label);
	label.add(t_sound_b);
	label.add(set_sound_b);
	
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
	
	save_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			System.out.println(save.readSave(0));
		}
	});
	
	t_sound_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if (current_sound.equals(null))
				audio.playSound(current_sound, volume.getValue());
			else
				audio.playSound(default_sound, volume.getValue());
		}
	});
	
	set_sound_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			File_Chooser fc = new File_Chooser();
			fc.open_file_chooser();
		}
	});
	
	setVisible(true);
	}
	//methods
	

}
