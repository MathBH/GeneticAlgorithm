
public interface GeneticMutator<P,C> {
	public DecisionTree<P,C> mutate(DecisionTree<P,C> decisionTree, float entropy);
}
