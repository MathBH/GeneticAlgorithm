package fileFormater;
import fileEditor.lineEdit.*;
public class DropAttribute implements LineEdit{
	private String DEFAULT_DELIMITER = ",";
	private String delimiter;
	private int attributeNumber;
	
	public DropAttribute(int attributeNumber){
		this.attributeNumber = attributeNumber;
		this.delimiter = DEFAULT_DELIMITER;
	}
	
	public String execute(String line){
		String[] attributes = line.split(delimiter);
		String output = "";
		
		if (attributeNumber >= attributes.length){
			System.err.println("Error: attribute index " + attributeNumber + " is out of bounds for " + line.length() + " attributes.");
			return line;
		}
		
		for (int i = 0; i < attributes.length; i++){
			if (i == attributeNumber)
				continue;
			if (output.length() > 0)
				output += delimiter;
			output += attributes[i];
		}
		
		return output;
	}
	
	@Override
	public String toString(){
		return "DropAttribute("+"index:"+attributeNumber+")";
	}
}
