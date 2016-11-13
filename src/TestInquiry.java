import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestInquiry {

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
	public void testEqualsInquiry() {
		EqualsInquiry<String> inquiry_sentenceMatch = new EqualsInquiry<String>("helloooo");
		
		assertTrue(inquiry_sentenceMatch.isTrueFor("helloooo"));
		assertFalse(inquiry_sentenceMatch.isTrueFor("hellooo"));
	}
	
	@Test
	public void testGreaterThanInquiry() {
		GreaterThanInquiry<Integer> inquiry_greaterThanFive = new GreaterThanInquiry<Integer>(5);
		
		assertTrue(inquiry_greaterThanFive.isTrueFor(6));
		assertFalse(inquiry_greaterThanFive.isTrueFor(5));
		assertFalse(inquiry_greaterThanFive.isTrueFor(4));
		assertFalse(inquiry_greaterThanFive.isTrueFor(-6));
	}
	
	@Test
	public void testSmallerThanInquiry() {
		SmallerThanInquiry<Integer> inquiry_smallerThanFive = new SmallerThanInquiry<Integer>(5);
		
		assertFalse(inquiry_smallerThanFive.isTrueFor(6));
		assertFalse(inquiry_smallerThanFive.isTrueFor(5));
		assertTrue(inquiry_smallerThanFive.isTrueFor(4));
		assertTrue(inquiry_smallerThanFive.isTrueFor(-6));
	}
	
	@Test
	public void testIndexInquiry(){
		SmallerThanInquiry<Float> inquiry_smallerThanOTwo = new SmallerThanInquiry<Float>(0.2f);
		EqualsInquiry<Float> inquiry_equalsOTwo = new EqualsInquiry<Float>(0.2f);
		GreaterThanInquiry<Float> inquiry_greaterThanOTwo = new GreaterThanInquiry<Float>(0.2f);
		
		IndexInquiry<ArrayList<Float>> idxIq_smallerThanOTwo = new IndexInquiry<ArrayList<Float>>(inquiry_smallerThanOTwo, 0);
		IndexInquiry<ArrayList<Float>> idxIq_equalsOTwo = new IndexInquiry<ArrayList<Float>>(inquiry_equalsOTwo, 1);
		IndexInquiry<ArrayList<Float>> idxIq_greaterThanOTwo = new IndexInquiry<ArrayList<Float>>(inquiry_greaterThanOTwo, 2);

		ArrayList<Float>dataSet1 = new ArrayList();
		dataSet1.add(0.1f);
		dataSet1.add(0.2f);
		dataSet1.add(0.3f);
		
		assertTrue(idxIq_smallerThanOTwo.isTrueFor(dataSet1));
		assertTrue(idxIq_equalsOTwo.isTrueFor(dataSet1));
		assertTrue(idxIq_greaterThanOTwo.isTrueFor(dataSet1));

		ArrayList<Float>dataSet2 = new ArrayList();
		dataSet2.add(0.2f);
		dataSet2.add(0.2f);
		dataSet2.add(0.3f);
		
		assertFalse(idxIq_smallerThanOTwo.isTrueFor(dataSet2));
		assertTrue(idxIq_equalsOTwo.isTrueFor(dataSet2));
		assertTrue(idxIq_greaterThanOTwo.isTrueFor(dataSet2));

		ArrayList<Float>dataSet3 = new ArrayList();
		dataSet3.add(0.1f);
		dataSet3.add(0.1f);
		dataSet3.add(0.3f);
		
		assertTrue(idxIq_smallerThanOTwo.isTrueFor(dataSet3));
		assertFalse(idxIq_equalsOTwo.isTrueFor(dataSet3));
		assertTrue(idxIq_greaterThanOTwo.isTrueFor(dataSet3));

		ArrayList<Float>dataSet4 = new ArrayList();
		dataSet4.add(0.1f);
		dataSet4.add(0.3f);
		dataSet4.add(0.3f);
		
		assertTrue(idxIq_smallerThanOTwo.isTrueFor(dataSet4));
		assertFalse(idxIq_equalsOTwo.isTrueFor(dataSet4));
		assertTrue(idxIq_greaterThanOTwo.isTrueFor(dataSet4));

		ArrayList<Float>dataSet5 = new ArrayList();
		dataSet5.add(0.1f);
		dataSet5.add(0.2f);
		dataSet5.add(0.2f);
		
		assertTrue(idxIq_smallerThanOTwo.isTrueFor(dataSet5));
		assertTrue(idxIq_equalsOTwo.isTrueFor(dataSet5));
		assertFalse(idxIq_greaterThanOTwo.isTrueFor(dataSet5));

		ArrayList<Float>dataSet6 = new ArrayList();
		dataSet6.add(0.3f);
		dataSet6.add(0.1f);
		dataSet6.add(0.1f);
		
		assertFalse(idxIq_smallerThanOTwo.isTrueFor(dataSet6));
		assertFalse(idxIq_equalsOTwo.isTrueFor(dataSet6));
		assertFalse(idxIq_greaterThanOTwo.isTrueFor(dataSet6));
	}

}
