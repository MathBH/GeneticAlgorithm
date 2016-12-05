import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestClassifierIncubator {
	private static final String LEARNING_DATA_PATH = "src/sampleData/iris.data";

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
		int leafCap = 25;
		int populationSize = 64;
		float mutationRate = 2.0f;
		float treshold = 0.95f;
		File learningData = new File(LEARNING_DATA_PATH);
		assertTrue(learningData.exists());
		
		ClassifierIncubator incubator = new ClassifierIncubator();
		incubator.addObserver(new CIObserver());
		
		Classifier potatowedge = incubator.generateReasoningEngine(leafCap, populationSize, mutationRate, learningData, treshold);
		
		ClassifierEvaluator eval = new ClassifierEvaluator();
		FloatScoreHandler scoreHandler = new FloatScoreHandler();
		
		eval.addObserver(new CEObserver());
		scoreHandler.addObserver(new FloatScoreObserver());
		
		scoreHandler.score(eval.evaluate(potatowedge, learningData));
	}

}
