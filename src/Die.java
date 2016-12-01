import java.util.Random;

/**
 * The Die class is an instantiable object that returns a random number smaller or equal to its
 * number of faces.
 *
 */

public class Die {

	private Random random;
	
	public Die(){
		random = new Random();
	}
	
	public int roll(int sides){
		return (int) (random.nextFloat()*sides);
	}
}
