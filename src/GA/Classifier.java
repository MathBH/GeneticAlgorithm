package GA;
import java.util.ArrayList;

/**
 * 
 * @author Mathieu
 * 
 * A kind of Reasoning Engine built for outputting a boolean value list in response
 * to a list of float attributes.
 * 
 * This class is designed for use in classification problems, as these are
 * represented in the {float list, boolean list} format in project.
 * 
 * TODO: maybe refactor so that the interface does not rely on the implementations
 * of classification problems.
 *
 */

public class Classifier extends ReasoningEngine<ArrayList<Float>,ArrayList<Boolean>>{
	
	public Classifier(){
		super();
	}
	
	public Classifier(DecisionTree<ArrayList<Float>,ArrayList<Boolean>> decisionTree){
		super(decisionTree);
	}
	
	@Override
	public String toString(){
		return this.getDecisionTree().toString();
	}
}
