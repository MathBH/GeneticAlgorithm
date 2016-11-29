package ioExtended;

import java.util.Observable;
import java.util.Observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInquirer extends Observable{
	
	private boolean waitingForPath;
	private File myFile;
	
	public FileInquirer(Observer view){
		addObserver(view);
		waitForPath();
	}
	
	public void loadFile(String filePath){
		
		File newFile = new File(filePath);
		
		if(!newFile.exists()){
			this.setChanged();
			this.notifyObservers(new FileNotFoundException());
		} else {
			this.myFile = newFile;
			this.notWaiting();
			this.setChanged();
			this.notifyObservers(newFile);
		}
	}
	
	public File getFile(){
		return this.myFile;
	}
	
	public boolean waitingForPath(){
		return this.waitingForPath;
	}
	
	public void waitForPath(){
		this.waitingForPath = true;
		this.setChanged();
		this.notifyObservers();
	}
	
	private void notWaiting(){
		this.waitingForPath = false;
	}
}