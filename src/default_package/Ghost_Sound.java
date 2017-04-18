package default_package;

public class Ghost_Sound {

	public void start() throws InterruptedException{
		System.out.println("Ghost Running");
		
		Save_Edit save = new Save_Edit();
		Sound audio = new Sound();
		
		
		String default_sound = "sample_sound.wav";
		String sound_file = save.readSave(4);
		int volume = Integer.valueOf(save.readSave(3));
		int interval = Integer.valueOf(save.readSave(2));
		boolean def_audio;
		
		
		if (save.readSave(4).equals("null")) //sees if a audio clip is chosen
			def_audio = true;
		else
			def_audio = false;
		
		
		
		while(true){
			for(int min = 0; min < interval; min++){
				Thread.sleep(60000);
				System.out.println(min + 1);}
			if (def_audio == false)
				audio.playSound_ext(sound_file, volume);
			else
				audio.playSound(default_sound, volume);
		}
		
	}
}
