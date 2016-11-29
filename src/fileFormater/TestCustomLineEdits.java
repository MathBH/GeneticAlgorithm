package fileFormater;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCustomLineEdits {

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
	public void testFormatClassifier() {
		FormatClass edit1 = new FormatClass(4,5);
		assertTrue(edit1.execute("2,1,3,4,3").equals("2,1,3,4,0,0,1,0,0"));
		FormatClass edit2 = new FormatClass(3,3);
		assertTrue(edit2.execute("2.1,1.3,3.5,2,3.4").equals("2.1,1.3,3.5,0,1,0,3.4"));
	}

	@Test
	public void testDropAttribute() {
		DropAttribute edit1 = new DropAttribute(3);
		assertTrue(edit1.execute("2,1,3,4,3").equals("2,1,3,3"));
		DropAttribute edit2 = new DropAttribute(0);
		assertTrue(edit2.execute("2.3,1.0,3.2,4a,3").equals("1.0,3.2,4a,3"));
		DropAttribute edit3 = new DropAttribute(4);
		assertTrue(edit3.execute("2.3,1.0,3.2,4a,3").equals("2.3,1.0,3.2,4a"));
	}

}
