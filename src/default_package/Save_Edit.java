package default_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Save_Edit {
	
	public String readSave(int line){
		try {
			int current_line = 0;
			Scanner fileIn = new Scanner(new File("src\\default_package\\settings.txt"));
			
			while(current_line < line){
			fileIn.nextLine();
			current_line++;}
			
			fileIn.next();
			fileIn.next();
			String a = fileIn.next();
			return a;} 
		
		catch (FileNotFoundException e) {
			return "Error";}
		
	}	
	
	public void writeSave(int line){

			int current_line = 0;
			Scanner fileIn;
			try {
				fileIn = new Scanner(new File("src\\default_package\\settings.txt"));

			
			while(current_line < line){
				fileIn.nextLine();
				current_line++;}
				
				fileIn.next();
				fileIn.next();}
			//	fileIn.remove();} no work
				
		catch (FileNotFoundException e) {
				System.out.println("Error");}
	}
}
