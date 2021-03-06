package GA;
import Inquiry.Inquiry;

/**
 * Decision Tree
 * @author Mathieu
 * 
 * Decision Trees are networks of nodes which allow for navigation
 * to a conclusion of type C from a premise of type P.
 * 
 * Navigation of these tress is how the AIs will make decisions.
 *
 * @param <P>
 * @param <C>
 */

public class DecisionTree<P,C>{
	private C conclusion;
	private DecisionTree<P,C> yesNode;
	private DecisionTree<P,C> noNode;
	private Inquiry<P> decisionFactor;
	
	//Constructor
	
	public DecisionTree() {
	}
	
	//Setters
	
	public void setConclusion(C conclusion){
		this.conclusion = conclusion;
	}
	
	public void setDecisionFactor(Inquiry<P> decisionFactor){
		this.decisionFactor = decisionFactor;
	}
	
	public void setYesNode(DecisionTree<P,C> binTree){
		this.yesNode = binTree;
	}
	
	public void setNoNode(DecisionTree<P,C> binTree){
		this.noNode = binTree;
	}
	
	//Getters
	
	public C getConclusion(){
		return this.conclusion;
	}
	
	public Inquiry<P> getDecisionFactor(){
		return this.decisionFactor;
	}
	
	public DecisionTree<P,C> getYesNode(){
		return this.yesNode;
	}
	
	public DecisionTree<P,C> getNoNode(){
		return this.noNode;
	}
	
	public DecisionTree<P,C> getNextNode(P premise){
		if (this.decisionFactor.isTrueFor(premise))
			return this.getYesNode();
		else
			return this.getNoNode();
	}
	
	public boolean hasChildren(){
		return !(this.yesNode == null && this.noNode == null);
	}
	
	@Override
	public String toString(){
		String output = "dT";
		if(this.decisionFactor != null)
			output += "(" + this.decisionFactor + ")";
		if(this.conclusion != null)
			output += "(" + this.conclusion + ")";
		return output;
	}

}
