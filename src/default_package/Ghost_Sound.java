package default_package;

public class Ghost_Sound {

	public void start() throws InterruptedException{
		System.out.println("Running...");
		
		Save_Edit save = new Save_Edit();
		Sound audio = new Sound();
		
		int interval = Integer.valueOf(save.readSave(2));
		int volume = Integer.valueOf(save.readSave(3));
		int time = Integer.valueOf(save.readSave(7));
		String default_sound = "sample_sound.wav";
		String sound_file = save.readSave(4);
		boolean def_audio;
		
		
		if (interval == 0)//milliseconds multiplier
			interval = 1;
		else if (interval == 1)//seconds multiplier
			interval = 1000;
		else //minutes multiplier
			interval = 60000;
		
		
		if (save.readSave(4).equals("null")) //sees if a audio clip is chosen
			def_audio = true;
		else
			def_audio = false;
		
		
		int wait = interval * time;
		System.out.println("Interval: " + wait + "Milliseconds");
		
		while(true){
			Thread.sleep(wait);
			
			if (def_audio == false)
				audio.playSound_ext(sound_file, volume);
			else
				audio.playSound(default_sound, volume);
		}
		
	}
}
