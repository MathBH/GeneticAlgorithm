package GA;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classifier Learning Data Parser
 * @author Mathieu
 * 
 * This class takes a scanner and outputs the next example
 * the scanner would read in a file.
 * 
 * It can be thought of as an extension to a Scanner so to allow it to
 * "getNextClassifierEx".
 *
 */

public class CLDParser implements DataParser<ClassifierEx>{
	private final String ERR_NUMBER_VAL = "Number value error at ";
	private final String ERR_BOOL = "ERR: Number could not be formatted to a boolean value: ";
	private final String ERR_NUMBER_FORMAT = "Number format error at ";
	private final String NUM_ATTRS = "num attributes: ";
	private final String NUM_CLASS = "num classes: ";
	private final String ATTR_VAL = "attribute value: ";
	private final String CLASS_VAL = "class value: ";
	private final String DELIMITER = ",";

	private final int DEFAULT_NUM_ATTRS = 1;
	private final int DEFAULT_NUM_CLASS = 1;
	private int nAttrs;
	private int nClass;
	
	public CLDParser(int nAttr, int nClass){
		
		this.nAttrs = nAttr;
		this.nClass = nClass;
		
		if (nAttr <= 0)
			this.nAttrs = DEFAULT_NUM_ATTRS;

		if (nClass <= 0)
			this.nClass = DEFAULT_NUM_CLASS;
	}
	
	/**
	 * returns next available Classifier Example from
	 * the file being read by scanner s
	 */
	public ClassifierEx read(Scanner s){
		if (!s.hasNextLine())
			return null;
		String[] tokens = s.nextLine().split(DELIMITER);
		
		ArrayList<Float> attrs = new ArrayList(nAttrs);
		ArrayList<Boolean> classes = new ArrayList(nClass);
		
		try{
		for(int i = 0; i < nAttrs; i++)
			attrs.add(i, parseFloat(tokens[i]));
		
		for(int i = 0; i < nClass; i++)
			classes.add(i, parseBoolean(tokens[nAttrs+i]));
		} catch(NumberFormatException e){
			System.err.println(ERR_NUMBER_FORMAT+tokens);
			e.printStackTrace();
			return null;
		} catch(NumberValueException e){
			e.printStackTrace();
			return null;
		}
		
		return new ClassifierEx(attrs, classes);
		
	}
	
	private float parseFloat(String s) throws NumberFormatException{
		return Float.parseFloat(s.trim());
	}
	
	private boolean parseBoolean(String s) throws NumberFormatException, NumberValueException{
		int i = Integer.parseInt(s.trim());
		if (i == 1)
			return true;
		if (i == 0)
			return false;
		throw new NumberValueException(ERR_BOOL + i);
	}

}
