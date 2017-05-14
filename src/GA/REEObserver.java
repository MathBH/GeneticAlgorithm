package GA;
import java.lang.reflect.Method;
import MObserve.*;

/**
 * Reasoning Engine Evaluator Observer
 * @author Mathieu
 *
 * Interface for objects that can be assigned to Reasoning Engine Evaluators
 * to output information to users. 
 *
 * @param <T>
 */

public interface REEObserver<T> extends Observer<REEResult<T>>{

	public void notify(REEResult<T> r);
}
