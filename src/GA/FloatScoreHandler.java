package GA;
import MObserve.*;

/**
 * Float Score Handler
 * @author Mathieu
 * Decorator-like class for Float objects adding the functionality
 * of its being observable.
 * 
 * TODO: pick more descriptive name, check if can extend Float or not
 * (to make it an actual decorator)
 */

public class FloatScoreHandler extends Observable<Float>{
	
	Float score;
	
	public Float getScore(){
		return this.score;
	}
	
	public void score(float score){
		this.score = score;
		this.notifyObservers(score);
	}

}
