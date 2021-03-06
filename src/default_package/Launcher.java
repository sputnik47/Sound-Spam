package default_package;

public class Launcher {

	public static void main(String[] args) throws InterruptedException {
		Control_Panel test = new Control_Panel();
		Save_Edit save = new Save_Edit();
		Ghost_Sound ghost = new Ghost_Sound();
		
		String run_sound = save.readSave(6);
		String silent_run = save.readSave(1);
		
		save.writeNewSave();//makes a default save file if one is not located
		save.writeSave(5, "true");
		
		if(save.readSave(1).equals("false")){//sees if ghost_startup is on
			test.open_cpanel();
			while(save.readSave(5).equals("true")){ //while control panel is running
				Thread.sleep(1000);}//wait
		}
		else 								//else it wont open cpanel and will open next time run
			System.out.println("Silent Mode = True");
		
		
		if(run_sound.equals("true") || silent_run.equals("true")){
			save.writeSave(6, "false");
			ghost.start();}
			
			
	}

}
