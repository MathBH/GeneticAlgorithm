package GA;

/**
 * Data Shell
 * @author Mathieu
 *
 * Interface for a wrapper class for data set files.
 * All Data Shells provide DataSetReaders with which to iterate over their contents.
 * 
 * TODO: change name so it is specific to data sets.
 *
 * @param <T>
 */

public interface DataShell<T> {
	public DataSetReader<T> getReader();
}
