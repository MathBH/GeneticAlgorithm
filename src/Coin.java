import java.util.Random;

public class Coin {
	private Random random;
	
	public Coin(){
		random = new Random();
	}
	
	public boolean flip(){
		return random.nextDouble() < 0.5;
	}
	
	public boolean flip(double odds){
		return random.nextDouble() < odds;
	}
}
