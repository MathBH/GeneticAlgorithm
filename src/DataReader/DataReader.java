package DataReader;

public interface DataReader<T>{
	
	public boolean readData();
	
	public T getDataRead();
}
