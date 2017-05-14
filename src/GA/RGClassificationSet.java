package GA;
import java.util.ArrayList;
import java.util.List;

/**
 * Random Generator Classification Set
 * @author Mathieu
 *
 * A random generator of classification sets.
 *
 * TODO: find a better name
 * TODO: fix the fact that "classification set" is currently synonynmous to its implementation
 */

public class RGClassificationSet extends RandomGenerator<ArrayList<Boolean>>{
	private int numClasses;
	private Die die;
	
	public RGClassificationSet(int numClasses){
		this.die = new Die();
		this.numClasses = numClasses;
	}
	
	
	/**
	 * randomly generates a classification set (ie: a boolean value list)
	 */
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
