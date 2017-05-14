package GA;

/**
 * Genetic Mutator
 * @author Mathieu
 *
 * Takes in a Decision tree and outputs a mutated one
 *
 * @param <P> Premise type
 * @param <C> Conclusion type
 * 
 * TODO: Change to take in a Reasoning engine and output a new mutated Reasoning Engine.
 */

public interface GeneticMutator<P,C> {
	public DecisionTree<P,C> mutate(DecisionTree<P,C> decisionTree, float entropy);
}
