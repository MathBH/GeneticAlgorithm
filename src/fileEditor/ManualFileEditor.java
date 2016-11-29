package fileEditor;
import java.io.FileNotFoundException;

import fileEditor.lineEdit.*;

public class ManualFileEditor {
	
	private static final int MIN_REPLACE_ARGS = 4;
	private static final int MIN_DELETE_ARGS = 3;
	
	public static void main(String[] args){
		if (args.length < 1){
			System.err.println("Error: insuficient arguments.");
			System.exit(1);
		}
		
		String token = args[0];
		
		if(token.equals("replace"))
			if (replace(args))
				System.exit(0);
			else
				System.exit(1);
		else if(token.equals("delete"))
			if (delete(args))
				System.exit(0);
			else
				System.exit(1);
		
		System.err.println("Error: command \"" + token +"\" not recognized.");
		System.exit(1);
	}
	
	private static boolean replace(String[] args){
		if (args.length < MIN_REPLACE_ARGS + 1){
			System.err.println("Error: insuficient arguments.");
			return false;
		}
		
		String filePath = args[1];
		String regex = args[2];
		String replacement = args[3];
		String outputPath = args[4];
		
		try {
			FileEditor editor = new FileEditor(filePath);
			ReplaceEdit edit = new ReplaceEdit(regex, replacement);
			editor.addEdit(edit);
			editor.edit(outputPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private static boolean delete(String[] args){
		if (args.length < MIN_DELETE_ARGS + 1){
			System.err.println("Error: insuficient arguments.");
			return false;
		}
		
		String filePath = args[1];
		String regex = args[2];
		String outputPath = args[3];
		
		try {
			FileEditor editor = new FileEditor(filePath);
			DeleteEdit edit = new DeleteEdit(regex);
			editor.addEdit(edit);
			editor.edit(outputPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}