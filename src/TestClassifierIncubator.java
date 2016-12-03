import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestClassifierIncubator {
	private static final String LEARNING_DATA_PATH = "src/sampleData/iris_small.data";

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
		float treshold = 0.95f;
		File learningData = new File(LEARNING_DATA_PATH);
		assertTrue(learningData.exists());
		
		ClassifierIncubator incubator = new ClassifierIncubator();
		
		Classifier drClassy = incubator.generateReasoningEngine(learningData, treshold);
		
		CLD cld = null;
		
		try {
			cld = new CLD(learningData);
		} catch (FileNotFoundException e) {
			fail();
		}
		
		DataSetReader<ClassifierEx> reader = cld.getReader();
		
		while (reader.hasNext()){
			ClassifierEx test = reader.next();
			System.out.println("Test: "+test);
			System.out.println("AI answer: "+ drClassy.concludeFrom(test.getPremise()));
			System.out.println("Correct answer: "+ test.getConclusion());
		}
		
		printDecisionTree(drClassy.getDecisionTree());
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
