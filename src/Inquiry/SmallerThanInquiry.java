package Inquiry;

/**
 * Smaller Than Inquiry
 * @author Mathieu
 *
 * Asks if input of type E is smaller than the private value criteria
 *
 * @param <E> E input type
 */

public class SmallerThanInquiry<E extends Comparable> implements ValInquiry<E>{
	private E criteria;
	
	public SmallerThanInquiry(E criteria){
		this.criteria = criteria;
	}
	
	@Override
	public boolean isTrueFor(E input) {
		return input.compareTo(criteria) < 0;
	}

	@Override
	public E getCriteria() {
		return this.criteria;
	}

	@Override
	public void setCriteria(E criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public String toString(){
		return "<"+criteria;
	}

}
