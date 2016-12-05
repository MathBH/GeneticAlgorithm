package GA;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDecisionTree {

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
		int itterations = 1;
		
		RGIFloatAttributeSet inquiryGenerator = new RGIFloatAttributeSet(7);
		RGClassificationSet conclusionGenerator = new RGClassificationSet(3);
		RGDecisionTree decisionTreeGenerator = new RGDecisionTree(inquiryGenerator, conclusionGenerator);
		
		DecisionTree decisionTreeBuffer;
		
		for (int i = 0; i < itterations; i++){
			decisionTreeBuffer = decisionTreeGenerator.randomGenerate(3);
			System.out.println("NEW DECISION TREE\n-----------------\n");
			printDecisionTree(decisionTreeBuffer);
			System.out.println("=================\n-----------------\n");
		}
		
		decisionTreeGenerator.setLeafCap(10);
		
		for (int i = 0; i < itterations; i++){
			decisionTreeBuffer = decisionTreeGenerator.randomGenerate();
			System.out.println("NEW DECISION TREE\n-----------------\n");
			printDecisionTree(decisionTreeBuffer);
			System.out.println("=================\n-----------------\n");
		}
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
