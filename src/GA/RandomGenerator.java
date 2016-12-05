package GA;
import java.util.Random;

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
