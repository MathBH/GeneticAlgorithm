package fileEditor;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import fileEditor.FileEditor;
import fileEditor.lineEdit.ReplaceEdit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFileEditor {

	private final static String ROOT_DIR = "src/fileEditor/";
	
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
	public void testInitialize() {
		try {
			FileEditor editor = new FileEditor(ROOT_DIR+"sample1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("FileNotFoundException");
		}
		
		try {
			FileEditor editor = new FileEditor(ROOT_DIR+"sample1as");
		} catch (FileNotFoundException e) {
			return;
		}
		
		fail("FileNotFoundException not detected");
	}

	@Test
	public void testReplace() {
		try {
			FileEditor editor = new FileEditor(ROOT_DIR+"sample1");
			ReplaceEdit hoEdit = new ReplaceEdit("blah","HO!");
			ReplaceEdit rillEdit = new ReplaceEdit("jack","rill");
			ReplaceEdit bloodEdit = new ReplaceEdit("r","b");
			editor.addEdit(hoEdit);
			editor.addEdit(rillEdit);
			editor.addEdit(bloodEdit);
			editor.edit(ROOT_DIR+"testOutput.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("FileNotFoundException");
		}
		
	}

}
