import java.util.Random;
/**
 * 
 * This object gives the user a boolean value by an entered probability. Probability is 0.5 by default;
 * @author Mathieu
 *
 */
public class Coin {
	private final float DEFAULT_PROB = 0.5f;
	private Random random;
	
	public Coin(){
		random = new Random();
	}
	
	public boolean flip(){
		return random.nextDouble() < DEFAULT_PROB;
	}
	
	public boolean flip(double odds){
		return random.nextDouble() < odds;
	}
}
