
public interface GeneticShuffler<P,C> {
	public GeneticChildPair<P,C> shuffle(DecisionTree<P,C> parent1, DecisionTree<P,C> parent2);
}
