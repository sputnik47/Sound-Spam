package default_package;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import javax.sound.sampled.AudioSystem;

public class Sound {

	public void playSound(String Sound, float Volume){ //for sounds located in jar file
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound.class.getResource(Sound)));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Volume);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playSound_ext(String Sound, float Volume){ //for external sounds
		try {
			Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(Sound)));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Volume);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
