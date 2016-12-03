import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestClassifierMutator {

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
		float entropy = 0.5f;
		int itterations = 1;
		int mutations = 4;
		int numAttr = 7;
		
		RGIFloatAttributeSet inquiryGenerator = new RGIFloatAttributeSet(numAttr);
		RGClassificationSet conclusionGenerator = new RGClassificationSet(3);
		RGDecisionTree decisionTreeGenerator = new RGDecisionTree(inquiryGenerator, conclusionGenerator);
		
		DecisionTree decisionTreeBuffer;
		
		ClassifierMutator mut = new ClassifierMutator(numAttr);
		
		decisionTreeGenerator.setLeafCap(2);
		for (int i = 0; i < itterations; i++){
			decisionTreeBuffer = decisionTreeGenerator.randomGenerate();
			System.out.println("NEW DECISION TREE\n-----------------\n");
			printDecisionTree(decisionTreeBuffer);
			System.out.println("=================\n-----------------\n");

			for (int j = 0; j < mutations; j++){
				decisionTreeBuffer = mut.mutate(decisionTreeBuffer, entropy);
				System.out.println("MUTATED TREE: "+ entropy+ "\n-----------------\n");
				printDecisionTree(decisionTreeBuffer);
				System.out.println("=================\n-----------------\n");
			}
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
