import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
public class TestLDReader {

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
		try{
		CLDReader reader = new CLDReader(new File("src/sampleData/iris.data"));
		while (reader.hasNext()){
			System.out.println(reader.next());
		}
		
		}catch(FileNotFoundException e){
			fail();
		}
	}

}
