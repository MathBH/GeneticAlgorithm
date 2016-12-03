
public class GeneticChildPair {
	private DecisionTree firstChild;
	private DecisionTree secondChild;
	
	public GeneticChildPair(DecisionTree firstChild, DecisionTree secondChild){
		this.firstChild = firstChild;
		this.secondChild = secondChild;
	}
	
	public DecisionTree getFirstChild(){
		return this.firstChild;
	}
	
	public DecisionTree getSecondChild(){
		return this.secondChild;
	}
}
