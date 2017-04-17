package default_package;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Control_Panel extends JFrame {

	public void open_cpanel(){
	
	//variables
	Save_Edit save = new Save_Edit();
	Sound audio = new Sound();
	save.writeNewSave(); //makes a default save file if one is not located
	
	String default_sound = "sample_sound.wav";
	
	JLabel label = new JLabel();//label for everything to go on
	
	JButton save_b = new JButton("Save");//buttons and etc
	JButton start_b = new JButton("Start");
	JButton play_sound_b = new JButton("Play \u25B6");
	JButton select_audio_b = new JButton("Select Audio");
	JButton reset = new JButton("reset");
	JCheckBox silent_startup = new JCheckBox("Silent Startup");
	
	JSlider volume = new JSlider(-24, 6, 0); //sliders
	JSlider interval = new JSlider(1, 120, 15);
	
	JLabel volume_label = new JLabel("Volume: 0.0f", SwingConstants.CENTER); //labels
	JLabel interval_label = new JLabel("Every 15 Minutes");
	JLabel border = new JLabel("_________________________________________");
	JLabel selected_audio_text = new JLabel("Selected Audio File:", SwingConstants.CENTER);
	JLabel selected_audio = new JLabel(sound_saved(), SwingConstants.CENTER);
	
	
	Dimension window_size = new Dimension(300, 300); //dimensions
	
	//updating to saved values
	silent_startup.setSelected(Boolean.valueOf(save.readSave(1)));
	interval.setValue(Integer.valueOf(save.readSave(2)));
	interval_label.setText("Every " + save.readSave(2) + " Minutes");
	volume.setValue(Integer.valueOf(save.readSave(3)));
	volume_label.setText("Volume: " + save.readSave(3) + ".0f");
	
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
	volume_label.setBounds(100, 100, 100, 20);
	play_sound_b.setBounds(205, 100, 80, 25);
	select_audio_b.setBounds(155, 10, 130, 25);
	selected_audio_text.setBounds(50, 195, 200, 25);
	border.setBounds(3, 160, 350, 25);
	selected_audio.setBounds(50, 225, 200, 25);
	reset.setBounds(108, 140, 80, 25);
	
	//adding onto frame
	add(label);
	label.add(silent_startup);
	label.add(interval);
	label.add(interval_label);
	label.add(save_b);
	label.add(start_b);
	label.add(volume);
	label.add(volume_label);
	label.add(play_sound_b);
	label.add(select_audio_b);
	label.add(selected_audio_text);
	label.add(border);
	label.add(selected_audio);
	label.add(reset);
	
	
	//action events
	
	//change listeners
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
	
	//action listeners
	save_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			save.writeSave(1, String.valueOf(silent_startup.isSelected()));
			save.writeSave(2, String.valueOf(interval.getValue()));
			save.writeSave(3, String.valueOf(volume.getValue()));
			System.out.println(save.readSave(0));
		}
	});
	
	play_sound_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String current_sound = save.readSave(4);
			
			System.out.println(current_sound);
			
			if (current_sound.equals("null"))
				audio.playSound(default_sound, volume.getValue());
			else
				audio.playSound_ext(current_sound, volume.getValue());
		}
	});
	
	select_audio_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			File_Chooser fc = new File_Chooser();
			String selected_sound = fc.open_file_chooser();
			System.out.println(selected_sound);
			save.writeSave(4, selected_sound);
			selected_audio.setText(save.short_file_dir(selected_sound));
		}
	});
	
	reset.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			save.reset();
			interval.setValue(15);
			volume.setValue(0);
			selected_audio.setText("Nothing");
			silent_startup.setSelected(false);
			
		}
	});
	
	setVisible(true);
	
	}
	
	//methods
	public String sound_saved(){
		Save_Edit save = new Save_Edit();
		if(!save.readSave(4).equals("null"))
			return save.short_file_dir(save.readSave(4));
		else
			return "Nothing";}
	
}
