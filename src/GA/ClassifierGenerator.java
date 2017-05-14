package GA;
import java.util.ArrayList;
/**
 * Classifier Generator
 * @author Mathieu
 * 
 * This class is responsible for random generation of Classifier AIs.
 *
 */

public class ClassifierGenerator {
	private RGIFloatAttributeSet inquiryGenerator;
	private RGClassificationSet conclusionGenerator;
	private RGDecisionTree<ArrayList<Float>,ArrayList<Boolean>> decisionTreeGenerator;
	
	private int numAttr;
	private int numClass;
	
	public ClassifierGenerator(){
	}
	
	public ClassifierGenerator(int numAttr, int numClass){
		setDataParamaters(numAttr,numClass);
	}
	
	public void setDataParamaters(int numAttr, int numClass){
		this.numAttr = numAttr;
		this.numClass = numClass;
		
		inquiryGenerator = new RGIFloatAttributeSet(this.numAttr);
		conclusionGenerator = new RGClassificationSet(this.numClass);
		decisionTreeGenerator = new RGDecisionTree(inquiryGenerator,conclusionGenerator);
	}
	
	public void setLeafCap(int leafCap){
		this.decisionTreeGenerator.setLeafCap(leafCap);
	}
	
	public Classifier generateRandom(){
		if (decisionTreeGenerator == null)
			return null;
		Classifier cl = new Classifier();
		cl.setDecisionTree(decisionTreeGenerator.randomGenerate());
		return cl;
	}
	
	public Classifier generateRandom(int numLeaves){
		if (decisionTreeGenerator == null)
			return null;
		Classifier cl = new Classifier();
		cl.setDecisionTree(decisionTreeGenerator.randomGenerate(numLeaves));
		return cl;
	}

}
