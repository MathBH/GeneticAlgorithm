import java.util.ArrayList;

public interface DTPathTracer<P,C> {
	public ArrayList<DecisionTree<P,C>> randomPath(DecisionTree<P, C> tree);
}
