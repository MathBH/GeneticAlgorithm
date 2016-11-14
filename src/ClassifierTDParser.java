import java.io.File;
import java.util.ArrayList;

public class ClassifierTDParser extends TrainingDataParser<ArrayList<Float>,ArrayList<Boolean>>{
	
	TrainingExample<ArrayList<Float>, ArrayList<Boolean>> example;
	ArrayList<Float> attributeSet;
	ArrayList<Boolean> classificationSet;
	
	private int numAttributes;
	private int numClasses;

	public ClassifierTDParser(File sourceFile) {
		super(sourceFile);
		this.example = null;
		this.attributeSet = null;
		this.classificationSet = null;
		
		if(this.scanner.pushNextLine())
			this.parseFormat();
		//otherwise throw and error (todo)
	}

	@Override
	public boolean read() {
		if (this.scanner.pushNextLine())
			return this.parseExample();
		return false;
	}

	@Override
	public TrainingExample<ArrayList<Float>, ArrayList<Boolean>> getElement() {
		return this.example;
	}
	
	private boolean parseExample(){
		String[] tokens = this.scanner.getLine().split(",");
		
		this.attributeSet = new ArrayList<Float>();
		this.classificationSet = new ArrayList<Boolean>();
		
		for (int i = 0; i < this.numAttributes; i++)
			if (!this.parseAttributeValue(tokens[i]))
				return false;
		
		for (int i = this.numAttributes; i < (this.numAttributes + this.numClasses); i++)
			if (!this.parseClassifierValue(tokens[i]))
				return false;
		
		this.example = new TrainingExample<ArrayList<Float>, ArrayList<Boolean>>(this.attributeSet,this.classificationSet);
		
		return true;
	}
	
	private boolean parseAttributeValue(String stringValue){
		Float value;
		
		try{
			value = Float.parseFloat(stringValue);
		}catch (NumberFormatException e){
			e.printStackTrace();
			return false;
		}
		
		this.attributeSet.add(value);
		return true;
	}
	
	private boolean parseClassifierValue(String stringValue){
		Integer integerRepresentation;
		Boolean value;
		
		try{
			integerRepresentation = Integer.parseInt(stringValue);
			if (integerRepresentation != 0 && integerRepresentation != 1)
				return false;
			value = (integerRepresentation == 1);
		}catch (NumberFormatException e){
			e.printStackTrace();
			return false;
		}
		
		this.classificationSet.add(value);
		return true;
	}
	
	private void parseFormat(){
		String[] tokens = this.scanner.getLine().split(",");
		
		try{
			this.numAttributes = Integer.parseInt(tokens[0]);
			this.numClasses = Integer.parseInt(tokens[1]);
			
		}catch (NumberFormatException e){
			e.printStackTrace();
		}
	}

}
