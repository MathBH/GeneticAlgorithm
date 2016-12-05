package GA;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CLDReader extends DataSetReader<Example<ArrayList<Float>,ArrayList<Boolean>>>{
	private final String ERR_NUMBER_FORMAT = "Number format error at ";
	private final String NUM_ATTR = "num attributes.";
	private final String NUM_CLASS = "num classes.";
	
	private Scanner scanner;
	private CLDParser parser;
	
	private ClassifierEx buffer;
	
	public CLDReader(File file) throws FileNotFoundException{
		int nAttr = -1;
		int nClass = -1;
		
		scanner = new Scanner(file);
		
		try {nAttr = parseInt(scanner.nextLine());
		}catch(NumberFormatException e){
			System.err.println(ERR_NUMBER_FORMAT+NUM_ATTR);
		}
		try{nClass = parseInt(scanner.nextLine());
		}catch(NumberFormatException e){
			System.err.println(ERR_NUMBER_FORMAT+NUM_CLASS);
		}
		
		parser = new CLDParser(nAttr, nClass);
		buffer = readData();
	}
	
	public boolean hasNext() {
		return buffer != null;
	}

	public ClassifierEx next() {
		if (!hasNext())
			return null;
		ClassifierEx temp = buffer;
		buffer = readData();
		return temp;
	}
	
	private ClassifierEx readData(){
		return parser.read(scanner);
		
	}
	
	private int parseInt(String s) throws NumberFormatException{
		return Integer.parseInt(s.trim());
	}

}
