package Inquiry;

/**
 * Criteria Based
 * @author Mathieu
 *
 * Interface for classes that are criteria based.
 *
 * @param <T>
 */

public interface CriteriaBased<T> {
	public T getCriteria();
	public void setCriteria(T criteria);
}
