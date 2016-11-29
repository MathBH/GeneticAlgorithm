package fileFormater;
import fileEditor.lineEdit.*;

public class FormatClass implements LineEdit{

	private String DEFAULT_DELIMITER = ",";
	
	private int classIndex;
	private int numClasses;
	private String delimiter;
	
	public FormatClass(int classIndex, int numClasses, String delimiter){
		this.classIndex = classIndex;
		this.numClasses = numClasses;
		this.delimiter = delimiter;
	}
	
	public FormatClass(int classIndex, int numClasses){
		this.classIndex = classIndex;
		this.numClasses = numClasses;
		this.delimiter = DEFAULT_DELIMITER;
	}
	
	public String execute(String line){
		String[] attributeStrings = line.split(delimiter);
		
		try{
		int classNum = Integer.parseInt(attributeStrings[classIndex]);
		boolean[] classifier = new boolean[numClasses];
		classifier[classNum - 1] = true;
		attributeStrings[classIndex] = writeClassifier(classifier);
		
		return writeAttributes(attributeStrings);
		
		} catch(NumberFormatException e){
			e.printStackTrace();
			System.err.println("that attribute was not an integer dood! I only know how to handle integers dood!");
			return line;
		}
	}
	
	private String writeClassifier(boolean[] classifier){
		String output = "";
		for (boolean classValue : classifier){
			if (output.length() > 0)
				output += delimiter;
			output += (classValue) ? "1" : "0";
		}
		return output;
	}
	
	private String writeAttributes(String[] attributes){
		String output = "";
		for (String attribute : attributes){
			if (output.length() > 0)
				output += delimiter;
			output += attribute;
		}
		return output;
	}
	
	@Override
	public String toString(){
		return "FormatClass(index:"+classIndex+",cap:"+numClasses+",delim:\""+delimiter+"\")";
	}
}
