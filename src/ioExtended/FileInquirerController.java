package ioExtended;

import java.io.File;
import java.util.Scanner;

/**
 * File Inquirer Controller
 * @author Mathieu
 *
 */

public class FileInquirerController {
	private Scanner scanner;
	private FileInquirer inquirer;
	
	public FileInquirerController(FileInquirer inquirer){
		this.inquirer = inquirer;
		scanner = new Scanner(System.in);
	}
	
	public File requestFile(){
		while(inquirer.waitingForPath()){
			inquirer.loadFile(scanner.next());
		}
		
		return inquirer.getFile();
	}

}
