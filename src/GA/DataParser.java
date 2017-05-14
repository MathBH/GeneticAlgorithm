package GA;
import java.util.Scanner;

/**
 * Data Parser
 * @author Mathieu
 * 
 * Class responsible for reading a file and outputting the corresponding
 * data of type T.
 *
 * @param <T> Data Type
 */

public interface DataParser<T> {
	public T read(Scanner s);
}
