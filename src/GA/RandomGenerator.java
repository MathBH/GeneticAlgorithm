package GA;
import java.util.Random;

/**
 * Random Generator
 * @author Mathieu
 *
 * Abstract class for Random Generators with functions one could expect to
 * be necessary for random generation.
 *
 * @param <E> E type of object it will generate
 * 
 * TODO: refactor out. Just use classes that provide easy handles for tools for random generation.
 */

public abstract class RandomGenerator<E> {
	
	Random random;

	RandomGenerator(){
		random = new Random();
	}
	
	float roll(){
		return random.nextFloat();
	}
	
	int rollForInterval(int interval){
		return (int) (this.roll()*interval);
	}

	float rollForInterval(float interval){
		return (this.roll()*interval);
	}
	public abstract E randomGenerate();
}
