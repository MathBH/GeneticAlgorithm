package scannerExtended;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScanner {

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
	public void testIterationScanner() {
		try {
			IterationScanner scanner = new IterationScanner("src/scannerExtended/sample1");
			for (String line: scanner)
				System.out.println(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPushScanner() {
		try {
			PushScanner scanner = new PushScanner("src/scannerExtended/sample1");
			while (scanner.pushLine())
				System.out.println(scanner.getBuffer());
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		try {
			PushScanner scanner = new PushScanner("src/scannerExtended/sample1");
			while (scanner.push())
				System.out.println(scanner.getBuffer());
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
