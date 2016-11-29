package fileFormater;

import java.util.Observable;
import java.util.Observer;

import Notification.Notification;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import fileEditor.lineEdit.*;

public class LDEView implements Observer{
	private final String DELIMITER = "-----------------------------------------";
	private final String EDIT_ADDED = "Edit added to queue: ";
	private final String EDIT_REMOVED = "Edit removed at index: ";
	private final String EDIT_NOT_REMOVED = "Edit not removed. Edit was not found.";
	private final String EDIT_NOT_RECOGNIZED = "Edit not recognized. Not action taken.";
	private final String FILE_LOADED = "File loaded: ";
	private final String FILE_NOT_FOUND = "File not found: ";
	private final String NEW_OUTPUT_PATH = "New output path: ";

	private final String PREFIX_FILE_PATH = "File: ";
	private final String PREFIX_OUTPUT_PATH = "Output path: ";
	private final String PREFIX_EDIT_QUEUE = "EditQueue: ";

	private final String MODEL_NOT_RECOGNIZED = "FATAL ERROR: Model not recognized: ";

	private final String LIST_DELIMITER = " ";
	
	private LDEModel state;
	
	public LDEView(){
	}
	
	@Override
	public void update(Observable model, Object arg) {
		runCrashGate(model);
		printModelInfo((LDEModel) model);
		
		newLine();
		
		parseArg(arg);
		printDelimiter();
		updateState((LDEModel) model);
	}
	
	/**
	 * A method with purpose to evaluate if assuming the given model
	 * is a LDEModel would cause the system to later crash and crashes it
	 * right away.
	 * @param model
	 */
	private void runCrashGate(Observable model){
		if(!model.getClass().equals(LDEModel.class)){
			System.err.println(MODEL_NOT_RECOGNIZED + model);
			System.exit(1);
		}
	}
	
	private void updateState(LDEModel newState){
		this.state = newState;
	}
	
	private void parseArg(Object arg){
		if (arg != null){
			if (arg instanceof Notification)
				respond((Notification) arg);
			else if (arg instanceof String)
				respond((String) arg);
			else if (arg instanceof File)
				respond((File) arg);
			else if (arg instanceof Integer)
				respond((int) arg);
			else if (arg instanceof LineEdit)
				respond((LineEdit) arg);
		}
	}
	
	private void printModelInfo(LDEModel model){
		System.out.println(PREFIX_FILE_PATH + model.getFilePath());
		System.out.println(PREFIX_EDIT_QUEUE + formatList(model.getEdits()));
		System.out.println(PREFIX_OUTPUT_PATH + model.getOutputPath());
	}
	
	private void printDelimiter(){
		System.out.println(DELIMITER);
	}
	
	private void respond(Notification notice){
		System.out.println(notice.getMessage());
	}
	
	private void respond(String outputPath){
		System.out.println(NEW_OUTPUT_PATH + outputPath);
	}
	
	private void respond(File file){
		if (this.state.getFile().equals(file))
			System.out.println(FILE_LOADED + file);
		else
			System.out.println(FILE_NOT_FOUND + file);
	}
	
	private void respond(int index){
		if (index != -1)
			System.out.println(EDIT_REMOVED + bracket(index));
		else
			System.out.println(EDIT_NOT_REMOVED);
	}
	
	private void respond(LineEdit edit){
		if (edit == null)
			System.out.println(EDIT_NOT_RECOGNIZED);
		else
			System.out.println(EDIT_ADDED + edit);
	}
	
	private String formatList(List list){
		String output = "";
		
		int index = 0;
		for (Object o: list){
			if (output.length() > 0)
				output += LIST_DELIMITER;
			output += bracket(index) + o.toString();
			index ++;
		}
		
		return output;
	}
	
	private String bracket(int index){
		return "[" + index + "]";
	}
	
	private void newLine(){
		System.out.println();
	}

}