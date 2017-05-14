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
	
	/**
	 * 
	 * Set what kind of data this classifier will have to be dealing with.
	 * 
	 * @param numAttr number of attributes
	 * @param numClass number of classifications
	 */
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
	
	/**
	 * Generate a random Classifier
	 * @return random Classifier
	 */
	public Classifier generateRandom(){
		if (decisionTreeGenerator == null)
			return null;
		Classifier cl = new Classifier();
		cl.setDecisionTree(decisionTreeGenerator.randomGenerate());
		return cl;
	}
	
	/**
	 * Generate a random Classifier with a set number of leaves.
	 * @param numLeaves
	 * @return random Classifier
	 */
	public Classifier generateRandom(int numLeaves){
		if (decisionTreeGenerator == null)
			return null;
		Classifier cl = new Classifier();
		cl.setDecisionTree(decisionTreeGenerator.randomGenerate(numLeaves));
		return cl;
	}

}
