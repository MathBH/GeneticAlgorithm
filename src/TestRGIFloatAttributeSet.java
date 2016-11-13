import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRGIFloatAttributeSet {

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
		RGIFloatAttributeSet rgifas = new RGIFloatAttributeSet(7);
		
		for (int i = 0; i < 100; i++){
			Inquiry attributeSetInquiry = rgifas.randomGenerate();
			System.out.println(attributeSetInquiry);
		}
	}

}
