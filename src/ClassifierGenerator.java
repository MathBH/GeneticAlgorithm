/**
 * 
 * PROBABLY DELETE
 */

import java.util.ArrayList;

public class ClassifierGenerator {
	private final int DEFAULT_LEAF_LIMIT = 64;
	private RGIFloatAttributeSet inquiryGenerator;
	private RGClassificationSet conclusionGenerator;
	private RGDecisionTree<ArrayList<Float>,ArrayList<Boolean>> decisionTreeGenerator;
	private int leafLimit;
	
	private int numAttr;
	private int numClass;
	
	public ClassifierGenerator(){
		this.leafLimit = DEFAULT_LEAF_LIMIT;
	}
	
	public ClassifierGenerator(int numAttr, int numClass){
		this.leafLimit = DEFAULT_LEAF_LIMIT;
		setDataParamaters(numAttr,numClass);
	}
	
	public void setDataParamaters(int numAttr, int numClass){
		this.numAttr = numAttr;
		this.numClass = numClass;
		
		inquiryGenerator = new RGIFloatAttributeSet(this.numAttr);
		conclusionGenerator = new RGClassificationSet(this.numClass);
		decisionTreeGenerator = new RGDecisionTree(inquiryGenerator,conclusionGenerator);
		decisionTreeGenerator.setLeafCap(leafLimit);
	}
	
	public Classifier generateRandom(){
		if (decisionTreeGenerator == null)
			return null;
		Classifier cl = new Classifier();
		cl.setDecisionTree(decisionTreeGenerator.randomGenerate());
		return cl;
	}

}
