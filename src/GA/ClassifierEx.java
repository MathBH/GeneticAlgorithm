package GA;
import java.util.ArrayList;

/**
 * 
 * @author Mathieu
 * An example to be used by a Classifier Reasoning Engine to learn.
 * 
 * The Premise for Classifiers is a list of float values and the conclusion is
 * a list of boolean truth values.
 * 
 * The conclusion is a list of boolean truth values because each slot in this
 * list represents a class. (So, in most cases, only one slot will be true.)
 * 
 */

public class ClassifierEx extends Example<ArrayList<Float>,ArrayList<Boolean>>{
	
	public ClassifierEx(ArrayList<Float> attr, ArrayList<Boolean> classifier) {
		super(attr,classifier);
	}
	
	@Override
	public String toString(){
		return this.getPremise().toString() + " : " + this.getConclusion().toString();
	}
}
