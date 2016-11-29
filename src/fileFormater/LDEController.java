package fileFormater;

import java.util.Observer;
import java.util.Scanner;
import java.util.regex.Pattern;

import fileEditor.lineEdit.LineEdit;

import java.util.regex.*;

import java.io.File;
import java.io.FileNotFoundException;

import ioExtended.*;

import observers.*;

public class LDEController {
	private final String COMMAND_FORMAT = "(.*):(.*)";
	private final String EDIT_FORMAT = "(.*)\\((.*)\\)";
	
	private final String ADD_TOKEN = "add";
	private final String REMOVE_TOKEN = "remove";
	private final String FILE_TOKEN = "file";
	private final String OUTPUT_TOKEN = "output";
	private final String EXECUTE_TOKEN = "execute";
	private final String EXIT_TOKEN = "exit";
	
	private final String DROP_ATTRIBUTE_TOKEN = "drop";
	private final String FORMAT_CLASS_TOKEN = "class";
	
	private final String DEFAULT_ARGS_DELIMITER = ",";
	
	private final int DROP_ATTR_ARG_COUNT = 1;
	
	private final int FORMAT_CLASS_ARG_COUNT_MIN = 2;
	private final int FORMAT_CLASS_ARG_COUNT_MAX = 3;
	
	private Matcher matcher;
	
	private LDEModel model;
	
	private Scanner scanner;
	
	public LDEController (LDEModel model){
		this.model = model;
	}
	
	public void run(){
		scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			parse(scanner.nextLine());
		}
	}
	
	public void parse(String userInput){
		
		String command;
		String arg;

		Pattern pattern = Pattern.compile(COMMAND_FORMAT);
		this.matcher = pattern.matcher(userInput);
		
		if (this.matcher.find()){
			command = this.matcher.group(1).trim();
			arg = this.matcher.group(2).trim();
		} else {
			model.invalidComm(userInput);
			return;
		}
		
		switch(command){
			case ADD_TOKEN:
				model.addEdit(buildLineEdit(arg));
				break;
			case REMOVE_TOKEN:
				model.removeEdit(parseIndex(arg));
				break;
			case FILE_TOKEN:
				model.setFile(arg);
				break;
			case OUTPUT_TOKEN:
				model.setOutputPath(arg);
				break;
			case EXECUTE_TOKEN:
				model.execute();
				break;
			case EXIT_TOKEN:
				System.exit(0);
			default:
				model.invalidComm(command);
		}
		
	}
	
	private int parseIndex(String indexDescription){
		try{
			int index = Integer.parseInt(indexDescription.trim());
			return index;
		} catch(NumberFormatException e){
			return -1;
		}
	}
	
	private LineEdit buildLineEdit(String editDescription){
		
		String editType;
		String editArgs;
		
		Pattern pattern = Pattern.compile(EDIT_FORMAT);
		this.matcher = pattern.matcher(editDescription);
		
		if (this.matcher.find()){
			editType = this.matcher.group(1).trim();
			editArgs = this.matcher.group(2).trim();
		} else {
			return null;
		}
		
		switch(editType){
			case DROP_ATTRIBUTE_TOKEN:
				return buildDropAttribute(editArgs);
			case FORMAT_CLASS_TOKEN:
				return buildFormatClass(editArgs);
			default:
				return null;
		}
	}
	
	private DropAttribute buildDropAttribute(String argDescription){
		String[] tokens = argDescription.split(DEFAULT_ARGS_DELIMITER);
		if (tokens.length != DROP_ATTR_ARG_COUNT)
			return null;
		
		int arg0;
		
		try{
			arg0 = Integer.parseInt(tokens[0].trim());
		} catch (NumberFormatException e){
			return null;
		}
		
		return new DropAttribute(arg0);
	}
	
	private FormatClass buildFormatClass(String argDescription){
		String[] tokens = argDescription.split(DEFAULT_ARGS_DELIMITER);
		if (tokens.length < FORMAT_CLASS_ARG_COUNT_MIN || tokens.length > FORMAT_CLASS_ARG_COUNT_MAX)
			return null;
		
		int arg0;
		int arg1;
		
		try{
			arg0 = Integer.parseInt(tokens[0].trim());
			arg1 = Integer.parseInt(tokens[1].trim());
		} catch (NumberFormatException e){
			return null;
		}
		
		if (tokens.length == FORMAT_CLASS_ARG_COUNT_MAX){
			String arg2 = tokens[2].trim();
			return new FormatClass(arg0,arg1,arg2);
		}
		
		return new FormatClass(arg0,arg1);
	}
	
}
