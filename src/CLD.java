import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CLD implements DataShell<ClassifierEx>{

	private File srcFile;
	private int numAttrs;
	private int numClass;
	
	public CLD(File file) throws FileNotFoundException{
		srcFile = file;
		scanProperties();
	}
	
	private void scanProperties() throws FileNotFoundException{
		Scanner scan = new Scanner(srcFile);
		
		numAttrs = scan.nextInt();
		numClass = scan.nextInt();
		
		scan.close();
	}
	
	public DataSetReader<ClassifierEx> getReader() {
		try {
			return new CLDReader(srcFile);
		} catch (FileNotFoundException e) {
			System.err.println("FILE NOT FOUND:" + srcFile);
			e.printStackTrace();
			return null;
		}
	}
	
	public int getNumAttrs(){
		return numAttrs;
	}
	
	public int getNumClass(){
		return numClass;
	}

}
