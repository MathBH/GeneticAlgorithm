import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRGClassifier {

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
		int itterations = 10;
		ClassifierGenerator gen = new ClassifierGenerator(4,3);
		gen.setLeafCap(100);
		
		Classifier classifierBuffer;
		for (int i = 0; i < itterations; i++){
			classifierBuffer = gen.generateRandom();
			System.out.println("NEW CLASSIFIER\n-----------------\n");
			printClassifier(classifierBuffer);
			System.out.println("=================\n-----------------\n");
		}
		
	}
	
	private void printClassifier(Classifier c){
		printDecisionTree(c.getDecisionTree());
	}
	
	private void printDecisionTree(DecisionTree dt){
		if (dt == null)
			return;
		System.out.print(dt);
		if (dt.hasChildren())
			System.out.print(": [" + dt.getNoNode() + ", " + dt.getYesNode() + "]");
		System.out.println();
		printDecisionTree(dt.getNoNode());
		printDecisionTree(dt.getYesNode());
	}

}
