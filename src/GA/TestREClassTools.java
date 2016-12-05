package GA;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestREClassTools {

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
		ArrayList<Boolean> cls1 = new ArrayList<Boolean>();
		ArrayList<Boolean> cls2 = new ArrayList<Boolean>();
		ArrayList<Boolean> cls3 = new ArrayList<Boolean>();
		ArrayList<Boolean> cls4 = new ArrayList<Boolean>();
		
		cls1.add(true);cls1.add(false);cls1.add(false);
		cls2.add(false);cls2.add(false);cls2.add(false);cls2.add(true);
		cls3.add(true);
		cls4.add(false);cls4.add(true);
		
		assertTrue(REClassTools.toInt(cls1) == 1);
		assertTrue(REClassTools.toInt(cls2) == 4);
		assertTrue(REClassTools.toInt(cls3) == 1);
		assertTrue(REClassTools.toInt(cls4) == 2);
	}

}
