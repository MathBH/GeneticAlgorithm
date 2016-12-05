package GA;
import MObserve.*;

public class FloatScoreObserver implements Observer<Float>{
	
	public void notify(Float score){
		System.out.println(score);
	}

}
