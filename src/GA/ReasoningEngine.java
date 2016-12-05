package GA;
import java.util.Stack;

public class ReasoningEngine<P,C> {
	private DecisionTree<P,C> decisionTree;
	
	public ReasoningEngine(){
		this.decisionTree = null;
	}
	
	public ReasoningEngine(DecisionTree<P,C> decisionTree){
		this.decisionTree = decisionTree;
	}
	
	public void setDecisionTree(DecisionTree<P,C> decisionTree){
		this.decisionTree = decisionTree;
	}

	public C concludeFrom(P premise){
		Stack<DecisionTree<P,C>> unexploredTrees = new Stack<DecisionTree<P,C>>();
		unexploredTrees.push(decisionTree);
		
		DecisionTree<P,C> currTree;
		
		while (!unexploredTrees.isEmpty()){
			currTree = unexploredTrees.pop();
			if (!currTree.hasChildren()){
				return currTree.getConclusion();}
			currTree = currTree.getNextNode(premise);
			if(currTree != null)
				unexploredTrees.push(currTree);
		}
		
		return null;
	}
	
	DecisionTree<P,C> getDecisionTree(){
		return this.decisionTree;
	}
}
