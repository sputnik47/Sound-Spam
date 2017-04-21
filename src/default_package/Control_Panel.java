package default_package;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Control_Panel extends JFrame{

	public void open_cpanel(){
	
	//variables
	Save_Edit save = new Save_Edit();
	Sound audio = new Sound();
	
	String default_sound = "sample_sound.wav";
	
	JLabel label = new JLabel();//label for everything to go on
	
	JButton save_b = new JButton("Save");//buttons and etc
	JButton start_b = new JButton("Start");
	JButton play_sound_b = new JButton("Play \u25B6");
	JButton select_audio_b = new JButton("Select Audio");
	JButton reset = new JButton("Reset");
	JCheckBox silent_startup = new JCheckBox("Silent Startup");
	
	JSlider volume = new JSlider(-24, 6, 0); //sliders
	
	String[] time_intervals = {"Milliseconds", "Seconds", "Minutes"};
	JComboBox<?> interval = new JComboBox<Object>(time_intervals);
	JTextField time = new JTextField("500");
	
	JLabel border = new JLabel("_________________________________________");//labels
	JLabel selected_audio_text = new JLabel("Selected Audio File:", SwingConstants.CENTER);
	JLabel selected_audio = new JLabel(sound_saved(), SwingConstants.CENTER);
	JLabel volume_label = new JLabel("Volume: 0.0f");
	JLabel interval_label = new JLabel("Interval:");
	
	Dimension window_size = new Dimension(300, 300); //dimensions
	
	//updating to saved values
	silent_startup.setSelected(Boolean.valueOf(save.readSave(1)));
	interval.setSelectedIndex(Integer.valueOf(save.readSave(2)));
	time.setText(save.readSave(7));
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
	interval_label.setBounds(45, 57, 100, 20);
	interval.setLocation(185, 55);
	interval.setSize(100, 25);
	time.setLocation(108, 55);
	time.setSize(70, 25);
	volume.setLocation(98, 90);
	volume.setSize(100, 40);
	volume_label.setBounds(15, 100, 100, 20);
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
	label.add(time);
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
	volume.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent ce) {
			
			volume_label.setText("Volume: "+ volume.getValue() + ".0f");
		}
	});
	
	//action listeners
	start_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			boolean isInt;
			
			try{
				int a = Integer.parseInt(time.getText());
				if (a > 0)
					isInt = true;
				else 
					isInt = false;}
			catch(NumberFormatException e1){
				isInt = false;}
			
			if (isInt == true){
				save.writeSave(1, String.valueOf(silent_startup.isSelected()));
				save.writeSave(2, String.valueOf(interval.getSelectedIndex()));
				save.writeSave(3, String.valueOf(volume.getValue()));
				save.writeSave(5, "false");
				save.writeSave(6, "true");
				save.writeSave(7, time.getText());//tells program to start sound
				dispose();}
			else
				time.setText("ERROR");
		}
	});
	
	save_b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			boolean isInt;
			try{
				int a = Integer.parseInt(time.getText());
				if (a > 0)
					isInt = true;
				else 
					isInt = false;}
			catch(NumberFormatException e1){
				isInt = false;}
			
			if (isInt == true){
				save.writeSave(1, String.valueOf(silent_startup.isSelected()));
				save.writeSave(2, String.valueOf(interval.getSelectedIndex()));
				save.writeSave(3, String.valueOf(volume.getValue()));
				save.writeSave(7, time.getText());}
			else
				time.setText("ERROR");
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
			interval.setSelectedItem(0);
			volume.setValue(0);
			selected_audio.setText("Nothing");
			silent_startup.setSelected(false);
			
		}
	});
	
	addWindowListener(new WindowAdapter()
	{
	    public void windowClosing(WindowEvent e){
	    save.writeSave(5, "false");
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
