package scannerExtended;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PushScanner {
	private Scanner scanner;
	private String buffer;
	
	public PushScanner(String filePath) throws FileNotFoundException{
		File file = new File(filePath);
		scanner = new Scanner(file);
	}
	
	public PushScanner(File sourceFile) throws FileNotFoundException{
		scanner = new Scanner(sourceFile);
	}
	
	/**
	 * push next space separated word into the buffer
	 * @return true if there was a next word
	 */
	public boolean push(){
		if (!scanner.hasNext())
			return false;
		buffer = scanner.next();
		return true;
	}
	
	/**
	 * push next line into the buffer
	 * @return true if there was a next line
	 */
	public boolean pushLine(){
		if (!scanner.hasNextLine())
			return false;
		buffer = scanner.nextLine();
		return true;
	}
	
	/**
	 * 
	 * @return string from the buffer
	 */
	public String getBuffer(){
		return this.buffer;
	}

}