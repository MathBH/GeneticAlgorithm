package Inquiry;

/**
 * Equals Inquiry
 * @author Mathieu
 *
 * Asks if a given input of type E is equal to private value criteria.
 *
 * @param <E>
 */

public class EqualsInquiry<E extends Comparable> implements ValInquiry<E>{
	private E criteria;
	
	public EqualsInquiry(E criteria){
		this.criteria = criteria;
	}

	@Override
	public boolean isTrueFor(E input) {
		return input.compareTo(criteria) == 0;
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
		return "="+criteria;
	}
}
