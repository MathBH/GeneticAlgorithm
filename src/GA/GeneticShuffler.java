package GA;

/**
 * Genetic Shuffler
 * @author Mathieu
 *
 * Interface for classes that will be responsible for taking two Decision Trees
 * and outputing two other Decision Trees that are mixes of the two.
 *
 * @param <P> Premise type
 * @param <C> Conclusion type
 * 
 * TODO: modify to take two Reasoning Engines and output the genetic child pair.
 */

public interface GeneticShuffler<P,C> {
	public GeneticChildPair<P,C> shuffle(DecisionTree<P,C> parent1, DecisionTree<P,C> parent2);
}
