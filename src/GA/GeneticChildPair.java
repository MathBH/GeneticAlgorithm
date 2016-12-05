package GA;

public class GeneticChildPair<P,C> {
	private DecisionTree<P,C> firstChild;
	private DecisionTree<P,C> secondChild;
	
	public GeneticChildPair(DecisionTree<P,C> firstChild, DecisionTree<P,C> secondChild){
		this.firstChild = firstChild;
		this.secondChild = secondChild;
	}
	
	public DecisionTree<P,C> getFirstChild(){
		return this.firstChild;
	}
	
	public DecisionTree<P,C> getSecondChild(){
		return this.secondChild;
	}
}
