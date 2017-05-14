package GA;
import java.util.ArrayList;

/**
 * Ant Trail Tracer
 * @author Mathieu
 *
 * A specific implementation of a Decision Tree Path Tracer.
 * It is designed to return random paths from a given decision tree.
 *
 * @param <P> Premise type
 * @param <C> Conclusion type
 */

public class AntTrailTracer<P,C> implements DTPathTracer<P,C>{
	
	private Coin coin;
	
	public AntTrailTracer(){
		coin = new Coin();
	}

	/** 
	 * The Ant Trail Tracer starts at the root of the decision tree and takes
	 * random turns until it reaches a leaf. It collects each node it passes
	 * by in a list (called the "trail") which it will then return.
	 */
	@Override
	public ArrayList<DecisionTree<P, C>> randomPath(DecisionTree<P, C> tree) {
		if(tree == null)
			return null;
		ArrayList<DecisionTree<P,C>> trail = new ArrayList<DecisionTree<P,C>>();
		DecisionTree<P, C> myTree = tree;
		trail.add(myTree);
		while(myTree.hasChildren()){
			if (coin.flip())
				myTree = myTree.getYesNode();
			else
				myTree = myTree.getNoNode();
			trail.add(myTree);
		}
		return trail;
	}
	
}
