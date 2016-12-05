package GA;
import java.util.Random;

public class Deviator {
	
	private Random random;
	private Coin coin;
	
	public Deviator(){
		random = new Random();
		coin = new Coin();
	}
	
	public float deviate(float val, float amount){
		return (coin.flip()) ? val + roll(amount) : val - roll(amount);
	}
	
	private float roll(float range){
		return random.nextFloat()*range;
	}

}
