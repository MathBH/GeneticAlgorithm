package GA;
import MObserve.*;

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
