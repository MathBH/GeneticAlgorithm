import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestClassifierTD {

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
		ClassifierTD trainingData = new ClassifierTD("sample.training.classifier");
		ClassifierTDParser parser = (ClassifierTDParser) trainingData.getParser();
		
		TrainingExample<ArrayList<Float>,ArrayList<Boolean>> example;
		
		while (parser.read()){
			example = parser.getElement();
			System.out.print(example.getPremise());
			System.out.println(example.getConclusion());
		}
	}

}
