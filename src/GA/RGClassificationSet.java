package GA;
import java.util.ArrayList;
import java.util.List;

public class RGClassificationSet extends RandomGenerator<ArrayList<Boolean>>{
	private int numClasses;
	private Die die;
	
	public RGClassificationSet(int numClasses){
		this.die = new Die();
		this.numClasses = numClasses;
	}
	
	@Override
	public ArrayList<Boolean> randomGenerate() {
		ArrayList<Boolean> classificationSet = new ArrayList<Boolean>();
		for (int i = 0; i < this.numClasses; i ++){
			classificationSet.add(false);
		}
		
		classificationSet.set(die.roll(this.numClasses), true);
		
		return classificationSet;
	}

}
