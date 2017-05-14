package GA;
import MObserve.*;

/**
 * Float Score Observer
 * @author Mathieu
 *
 * Observers a Float Score Handler and prints the score on notification.
 */

public class FloatScoreObserver implements Observer<Float>{
	
	public void notify(Float score){
		System.out.println(score);
	}

}
