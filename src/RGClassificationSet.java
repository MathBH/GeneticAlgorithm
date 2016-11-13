import java.util.ArrayList;
import java.util.List;

public class RGClassificationSet extends RandomGenerator<ArrayList<Boolean>>{
	private int numClasses;
	
	public RGClassificationSet(int numClasses){
		this.numClasses = numClasses;
	}
	
	@Override
	public ArrayList<Boolean> randomGenerate() {
		ArrayList<Boolean> classificationSet = new ArrayList<Boolean>();
		for (int i = 0; i < this.numClasses; i ++){
			classificationSet.add(this.coinFlip());
		}
		return classificationSet;
	}
	
	private boolean coinFlip(){
		return (this.roll() > 0.4);
	}

}
