package default_package;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;

public class Sound {

	public void playSound(String Sound, float Volume){
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
}
