package default_package;

import java.awt.Dimension;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class File_Chooser extends JFrame{

	public String open_file_chooser(){
		//variables
		JFileChooser chooser = new JFileChooser();
		JButton open_b = new JButton();
		
		//design of chooser
		chooser.setPreferredSize(new Dimension(500, 500));
		chooser.setDialogTitle("Select Audio File (.wav)");
		
		//finds person's desktop directory
		File desktop = new File(System.getProperty("user.home") + "\\Desktop");
		chooser.setCurrentDirectory(desktop);
		
		//only searchs for wav files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Sound Files", "wav");
		chooser.setFileFilter(filter);
		
		
		if (chooser.showOpenDialog(open_b) == JFileChooser.APPROVE_OPTION){
			return chooser.getSelectedFile().getAbsolutePath();
		}
		
		else 
			return null;
		
	}
}
