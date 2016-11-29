package fileFormater;

import fileEditor.*;
import fileEditor.lineEdit.*;

import java.util.Scanner;

import ExceptionHandler.*;
import Notification.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import java.io.File;
import java.io.FileNotFoundException;

public class LDEModel extends Observable{
	
	private final String NONE = "(/)";
	private final String EDIT_QUEUE_EMPTY = "No edits present in edit queue. No changes were made.";
	private final String EDITS_PERFORMED = "Edits performed. File saved to: ";
	private final String INVALID_COMMAND = "Command not recognized: ";
	
	private FileEditor editor;
	private ExceptionHandler eHandler;
	
	private ArrayList<LineEdit> edits;
	private String filePath;
	private String outputPath;
	private File fileToEdit;
	
	public LDEModel(File file,Observer view, ExceptionHandler eHandler) throws FileNotFoundException{
		this.fileToEdit = file;
		this.filePath = file.getPath();
		this.initEditor();
		this.edits = new ArrayList<LineEdit>();
		this.addObserver(view);
		this.eHandler = eHandler;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setFile(String filePath){
		File file = new File(filePath);
		if (file.exists()){
			this.fileToEdit = file;
			this.filePath = filePath;
		}
		this.setChanged();
		this.notifyObservers(file);
	}
	
	public void setOutputPath(String outputPath){
		this.outputPath = outputPath;
		this.setChanged();
		this.notifyObservers(outputPath);
	}
	
	public void addEdit(LineEdit edit){
		if (edit != null)
			this.edits.add(edit);
		this.setChanged();
		this.notifyObservers(edit);
	}
	
	public void removeEdit(int i){
		if (0 <= i && i < this.edits.size()){
			this.edits.remove(i);
			this.setChanged();
			this.notifyObservers(i);
		}else
			this.setChanged();
			this.notifyObservers(-1);
	}
	
	public void execute(){
		
		try {
			this.editor.initForFile(fileToEdit);
		} catch (FileNotFoundException e) {
			this.eHandler.alert(e);
		}
		
		if (edits.isEmpty()){
			this.setChanged();
			this.notifyObservers(new EditQueueNotification(EDIT_QUEUE_EMPTY));
			return;
		}	
		this.editor.addEdit(edits);
		
		try {
			this.editor.edit(outputPath);
			this.setChanged();
			this.notifyObservers(new EditsPerformedNotification(EDITS_PERFORMED + outputPath));
		} catch (FileNotFoundException e) {
			this.eHandler.alert(e);
		}
	}

	public void invalidComm(String command){
		this.setChanged();
		this.notifyObservers(new CommandNotification(INVALID_COMMAND + command));
	}
	
	public String getFilePath(){
		return this.filePath;
	}
	
	public File getFile(){
		return this.fileToEdit;
	}
	
	public String getOutputPath(){
		if (this.outputPath == null)
			return NONE;
		return this.outputPath;
	}
	
	public ArrayList<LineEdit> getEdits(){
		return this.edits;
	}
	
	private void initEditor(){
		this.editor = new FileEditor();
	}
}