package GA;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFloatDataSet {

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
		FloatDataSet dataSet = new FloatDataSet();
		
		dataSet.add(7.1f);
		assertTrue(dataSet.getMin() == 7.1f);
		assertTrue(dataSet.getAverage() == 7.1f);
		assertTrue(dataSet.getMax() == 7.1f);
		dataSet.add(7.1f);dataSet.add(7.1f);
		assertTrue(dataSet.getMin() == 7.1f);
		assertTrue(dataSet.getAverage() == 7.1f);
		assertTrue(dataSet.getMax() == 7.1f);
		
		dataSet = new FloatDataSet();
		
		dataSet.add(3.1f);
		dataSet.add(4.9f);
		assertTrue(dataSet.getMin() == 3.1f);
		assertTrue(dataSet.getAverage() == 4.0f);
		assertTrue(dataSet.getMax() == 4.9f);
		dataSet.add(1.0f);
		assertTrue(dataSet.getMin() == 1.0f);
		assertTrue(dataSet.getAverage() == 3.0f);
		assertTrue(dataSet.getMax() == 4.9f);
		
	}

}
