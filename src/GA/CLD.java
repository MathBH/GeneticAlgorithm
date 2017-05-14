package GA;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classifier Learning Data
 * @author Mathieu
 *
 * Learning Data specifically for Classifier Reasoning Engines.
 * These are represented as pairs of a float value list and a boolean value list.
 *
 */

public class CLD implements DataShell<Example<ArrayList<Float>,ArrayList<Boolean>>>{

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
	
	public DataSetReader<Example<ArrayList<Float>,ArrayList<Boolean>>> getReader() {
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
