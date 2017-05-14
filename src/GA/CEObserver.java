package GA;
import java.util.List;

/**
 * Classifier Evaluator Observer
 * @author Mathieu
 *
 */

public class CEObserver implements REEObserver<List<Boolean>>{
	@Override
	public void notify(REEResult<List<Boolean>> r) {
		System.out.println(REClassTools.toInt(r.getResult()) + "," + REClassTools.toInt(r.getExpected()));
	}

}
