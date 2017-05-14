package GA;

/**
 * Genetic Child Pair
 * @author Mathieu
 *
 * Pair of Decision Trees. Used for holding two children resultant from
 * mixing the parent's algorithms
 * 
 * @param <P> Premise type
 * @param <C> Conclusion type
 * 
 * TODO: Change to hold two Reasoning Engines instead of Decision Trees.
 */

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
