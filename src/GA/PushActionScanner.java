package GA;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class PushActionScanner{
	
	private File sourceFile;
	private String lineBuffer;
	private Scanner scanner;
	
	public PushActionScanner(File file){
		this.sourceFile = file;
		this.reset();
	}
	
	public boolean pushNextLine(){
		if (this.scanner.hasNextLine()){
			this.lineBuffer = scanner.nextLine();
			return true;
		}
		return false;
	}
	
	public String getLine(){
		return this.lineBuffer;
	}
	
	public void reset(){
		try {
			this.scanner = new Scanner(sourceFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
