package default_package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Save_Edit {
	
	public String readSave(int line){

		try {
			FileReader fr = new FileReader(getDir() + "/settings.txt");
			BufferedReader br = new BufferedReader(fr);
			String line_text = "";
			
			int current_line = 1;
			while(current_line < line){
				line_text = br.readLine();
				current_line++;}
			
			line_text = br.readLine();
			String[] variableArray = line_text.split(" ");
			br.close();
			
			return variableArray[2];
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}	
	
	public void writeSave(int line, String replace_with){

		try {
			//reading
			FileReader fr = new FileReader(getDir() + "/settings.txt");
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> text_file = new ArrayList<String>();
			String line_text = ".";
			String[] select_var = null;
			
			// make it read lines into array and get line requested split
			// for writer and make all the lines after that line unless 
			// it is the last.
			
			int current_line = 1;
			boolean iterating = true;
			
			while(iterating == true){
				line_text = br.readLine();
					if(line_text == null)
						iterating = false;
					if(!(current_line == line))
						text_file.add(line_text);
					if(current_line == line)
						select_var = line_text.split(" ");
					current_line++;
			}
			
			int total_lines = current_line - 1;
			text_file.remove(text_file.size() - 1); //removes null that shouldnt be in array
			
			br.close(); // closes reading

			
			select_var[2] = replace_with;
			String finished_var = String.join(" ", select_var);
			text_file.add(line - 1, finished_var);
			
			
			//writing
			PrintWriter writeText = new PrintWriter(getDir() + "/settings.txt", "UTF-8");
			for(int x = 1; x < total_lines; x++){
				writeText.println(text_file.get(x - 1));
				}
			writeText.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeNewSave(){//makes new settings file
		try {
			
			File f = new File(getDir() + "/settings.txt");
			if(f.exists() && !f.isDirectory())
				System.out.println("File Found");
			else{
				PrintWriter writeText = new PrintWriter(getDir() + "/settings.txt", "UTF-8");
				writeText.println("silent = false");
				writeText.println("interval = 0");
				writeText.println("volume = 0");
				writeText.println("sound_dir = null");
				writeText.println("cpanel_open = false");
				writeText.println("run_sound = false");
				writeText.println("time = 500");
				writeText.close();
				System.out.println("File Not Found");}
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public String short_file_dir(String directory){ //shortend directory of file for display
		String file_name = directory.substring(directory.lastIndexOf('\\') + 1, directory.length());
		System.out.println(file_name);
		return file_name;
	}
	
	public void reset(){ //sets all vaiables in text file to default
		writeSave(1, "false");
		writeSave(2, "15");
		writeSave(3, "0");
		writeSave(4, "null");
	}
	
	public String getDir(){ //returns directory of where processes take place
		String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		absolutePath = absolutePath.substring(1, absolutePath.lastIndexOf("/"));
		absolutePath = absolutePath.replaceAll("%20"," "); // Surely need to do this here
		return absolutePath;
	}
}
