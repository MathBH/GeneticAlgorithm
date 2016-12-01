import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCoin {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Coin coin = new Coin();
		System.out.println(coin.flip(0.0));
		System.out.println(coin.flip(0.0));
		System.out.println(coin.flip(0.0));
		System.out.println(coin.flip(1.0));
		System.out.println(coin.flip(1.0));
		System.out.println(coin.flip(1.0));
		System.out.println(coin.flip(0.5));
		System.out.println(coin.flip(0.5));
		System.out.println(coin.flip(0.5));
		System.out.println();
		System.out.println(coin.flip(0.333));
		System.out.println(coin.flip(0.333));
		System.out.println(coin.flip(0.333));
	}

}
