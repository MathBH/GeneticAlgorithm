package GA;
import java.util.ArrayList;

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
