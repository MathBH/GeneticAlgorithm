import java.util.ArrayList;

public class AntTrailTracer<P,C> implements DTPathTracer<P,C>{
	
	private Coin coin;
	
	public AntTrailTracer(){
		coin = new Coin();
	}

	@Override
	public ArrayList<DecisionTree<P, C>> randomPath(DecisionTree<P, C> tree) {
		if(tree == null)
			return null;
		ArrayList<DecisionTree<P,C>> trail = new ArrayList<DecisionTree<P,C>>();
		DecisionTree<P, C> myTree = tree;
		trail.add(myTree);
		while(tree.hasChildren()){
			if (coin.flip())
				myTree = tree.getYesNode();
			else
				myTree = tree.getNoNode();
		}
		return trail;
	}
	
}
