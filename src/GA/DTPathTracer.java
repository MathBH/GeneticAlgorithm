package GA;
import java.util.ArrayList;

/**
 * Decision Tree Path Tracer
 * @author Mathieu
 *
 * Class reponsible for taking a decision tree for which premises are of type P
 * and conclusions of type C, and returning node lists representing a path.
 * 
 * @param <P> Premise type
 * @param <C> Conclusion type
 * 
 * TODO: Consider changing param to be a decision tree type. Or also refactoring this out of the code.
 */

public interface DTPathTracer<P,C> {
	public ArrayList<DecisionTree<P,C>> randomPath(DecisionTree<P, C> tree);
}
