package fileEditor;
import scannerExtended.IterationScanner;
import fileEditor.lineEdit.*;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Collection;
import java.io.File;
import java.io.FileNotFoundException;

public class FileEditor {
	private IterationScanner scanner;
	private PrintWriter writer;
	private LinkedList<LineEdit> edits;
	private File myFile;
	
	public FileEditor(){
	}

	public FileEditor(File file) throws FileNotFoundException{
		myFile = file;
		initialize();
	}
	
	public FileEditor(String inputPath) throws FileNotFoundException{
		myFile = new File(inputPath);
		initialize();
	}
	
	public void edit(String outputPath) throws FileNotFoundException{
		if (!initialized())
			return;
		writer = new PrintWriter(outputPath);
		for (String line : scanner){
			for (LineEdit edit : edits){
				line = edit.execute(line);
			}
			writer.println(line);
		}
		writer.close();
	}
	
	public void addEdit(LineEdit newEdit){
		if (!initialized())
			return;
		this.edits.add(newEdit);
	}
	
	public void addEdit(Collection<LineEdit> newEdits){
		for (LineEdit newEdit: newEdits){
			this.edits.add(newEdit);
		}
	}
	
	public void initForFile(File file) throws FileNotFoundException{
		this.myFile = file;
		initialize();
	}
	
	public void initForFile(String filePath) throws FileNotFoundException{
		this.myFile = new File(filePath);
		initialize();
	}
	
	public LinkedList<LineEdit> getEdits(){
		if (!initialized())
			return null;
		return this.edits;
	}
	
	public File getFile(){
		return this.myFile;
	}
	
	private void initialize() throws FileNotFoundException{
		scanner = new IterationScanner(myFile);
		edits = new LinkedList<LineEdit>();
	}
	
	private boolean initialized(){
		return this.scanner != null;
	}
}
