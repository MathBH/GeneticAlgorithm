package kandyToolKit.fileEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import kandyToolKit.KandyFileReader;

public class FileMod {
	private KandyFileReader fileReader;
	private File myFile;
	private PrintWriter printWriter;
	
	public FileMod(){
		//edit to generalize
	}
	
	public static void performEdit(String inputPath, LineEditor lineEditor, String regex, String outputPath){
		setup(inputPath, outputPath);
		while(fileReader.readLine())
			if (fileReader.getLineRead().matches(regex))
				printWriter.println(lineEditor.performEdit(fileReader.getLineRead()));
		tearDown();
	}
	
	private static void setup(String inputPath, String outputPath){
		try {
			printWriter = new PrintWriter(outputPath);
		} catch (FileNotFoundException e) {
			System.err.println("friend I could not write your output file, sry :/");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			fileReader = new KandyFileReader(inputPath);
		} catch (FileNotFoundException e){
			System.err.println("Sry bro ur file was not found. :/");
			e.printStackTrace();
			System.exit(2);
		}
	}
	
	private static void tearDown(){
		printWriter = null;
		fileReader = null;
	}
}
