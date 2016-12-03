

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import FileReader.CAOReader;

public abstract class DataReader<T> extends CAOReader{
	
	public DataReader(File sourceFile) throws FileNotFoundException{
		super(sourceFile);
		
		if (!this.readData())
			System.err.println("Warning: No data could be read from file!");
	}
	
	public abstract boolean readData();
	
	public abstract T getDataRead();
}
