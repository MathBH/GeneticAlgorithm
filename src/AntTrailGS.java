import java.util.ArrayList;

public class AntTrailGS<P,C> implements GeneticShuffler<P,C>{
	private ArrayList<DecisionTree<P,C>> trail1;
	private ArrayList<DecisionTree<P,C>> trail2;
	
	private Die die;
	private Coin coin;
	
	public AntTrailGS(){
		die = new Die();
		coin = new Coin();
	}

	@Override
	public GeneticChildPair<P, C> shuffle(DecisionTree<P, C> parent1, DecisionTree<P, C> parent2) {
		DecisionTree<P, C> child1;
		DecisionTree<P, C> child2;
		
		trail1 = wander(parent1);
		trail2 = wander(parent2);
		
		int idx1 = die.roll(trail1.size());
		int idx2 = die.roll(trail2.size());
		
		if (idx1 >= 1){
			if(trail1.get(idx1-1).getYesNode().equals(trail1.get(idx1)))
				trail1.get(idx1-1).setYesNode(trail2.get(idx2));
			else if (trail1.get(idx1-1).getNoNode().equals(trail1.get(idx1)))
				trail1.get(idx1-1).setNoNode(trail2.get(idx2));
			else{
				System.err.println("Uhm, it wasn't the no node or the yes node???");
				return null;
			}
			child1 = trail1.get(0);
		} else {
			child1 = trail2.get(idx2);
		}
		
		if (idx2 >= 1){
			if(trail2.get(idx2-1).getYesNode().equals(trail2.get(idx2)))
				trail2.get(idx2-1).setYesNode(trail1.get(idx1));
			else if (trail2.get(idx2-1).getNoNode().equals(trail2.get(idx2)))
				trail2.get(idx2-1).setNoNode(trail1.get(idx1));
			else{
				System.err.println("Uhm, it wasn't the no node or the yes node???");
				return null;
			}
			child2 = trail2.get(0);
		} else {
			child2 = trail1.get(idx1);
		}
			
		return new GeneticChildPair<P,C>(child1,child2);
	}

	private ArrayList<DecisionTree<P,C>> wander(DecisionTree<P, C> tree){
		ArrayList<DecisionTree<P,C>> trail = new ArrayList<DecisionTree<P,C>>();
		trail.add(tree);
		if(!tree.hasChildren())
			return trail;
	}

}
