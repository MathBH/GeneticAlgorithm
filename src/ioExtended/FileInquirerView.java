package ioExtended;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

/**
 * File Inquirer View
 * @author Mathieu
 *
 */

public class FileInquirerView implements Observer{

	private final String DELIMITER = "-----------------------------------------";
	
	private final String FILE_NOT_FOUND = "File not found.";
	private final String FILE_PATH = "Please enter file path...";
	private final String FILE_LOADED = "File loaded.";

	@Override
	public void update(Observable arg0, Object arg1) {
		FileInquirer inquirer = (FileInquirer) arg0;
		
		parseArg(arg1);
		
		if (inquirer.waitingForPath())
			System.out.println(FILE_PATH);
		
		delimiter();
		
	}
	
	private void parseArg(Object arg){
		if (arg !=null){
			if (arg instanceof File)
				System.out.println(FILE_LOADED);
			else if (arg instanceof FileNotFoundException)
				System.out.println(FILE_NOT_FOUND);
		}
			
	}
	
	private void delimiter(){
		System.out.println(DELIMITER);
	}
	
}
