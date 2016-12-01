import java.util.Scanner;

public interface DataParser<T> {
	public T read(Scanner s);
}
