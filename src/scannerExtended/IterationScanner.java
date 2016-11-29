package scannerExtended;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class IterationScanner implements Iterable<String>{
	private Scanner scanner;
	
	public IterationScanner(String filePath) throws FileNotFoundException{
		File file = new File(filePath);
		scanner = new Scanner(file);
	}
	
	public IterationScanner(File file) throws FileNotFoundException{
		scanner = new Scanner(file);
	}

	@Override
	public Iterator<String> iterator() {
		return new ScanIterator();
	}
	
	private class ScanIterator implements Iterator<String>{

		@Override
		public boolean hasNext() {
			return scanner.hasNextLine();
		}

		@Override
		public String next(){
			return scanner.nextLine();
		}
		
	}

}
