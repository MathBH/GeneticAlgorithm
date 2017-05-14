package Inquiry;

/**
 * Inquirt
 * @author Mathieu
 *
 * Interface for Inquiries.
 * 
 * Inquiries return true or false for a given input of type T.
 *
 * @param <T>
 */

public interface Inquiry<T> {
	public boolean isTrueFor(T arg);
}