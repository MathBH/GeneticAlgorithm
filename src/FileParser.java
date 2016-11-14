import java.io.File;
import java.util.Iterator;

public abstract class FileParser<E>{
	protected PushActionScanner scanner;
	
	public FileParser(File sourceFile){
		scanner = new PushActionScanner(sourceFile);
	}
	
	public abstract boolean read();
	
	public abstract E getElement();
}
