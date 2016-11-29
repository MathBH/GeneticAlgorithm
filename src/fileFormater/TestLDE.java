package fileFormater;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ExceptionHandler.BasicExceptionHandler;
import ioExtended.FileInquirer;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLDE {
	private static LDEModel ldeModel;

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
		/*
		String filePath = "src/sampleData/glass.data";
		File file = new File(filePath);
		
		try{
			formater = new FileFormater(new FileFormaterView(), file);
			formater.
		}catch (FileNotFoundException e){
			fail("file not found exception");
		}
		Pattern pattern = Pattern.compile("(.*)\\((.*)\\)");
		Matcher matcher = pattern.matcher("fuwehfuewy( sad, assdz )");
		
		while (matcher.find()){
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
		}
		*/
		try {
			LDEModel model = new LDEModel("src/sampleData/glass.data", new LDEView(), new BasicExceptionHandler());
			
			model.addEdit(new DropAttribute(0));
			model.addEdit(new FormatClass(9,7));
			model.setOutputPath("src/sampleData/test.data");
			model.execute();
			model.setFile("src/sampleData/test.data");
			model.setOutputPath("src/sampleData/test2.data");
			model.removeEdit(1);
			model.execute();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
