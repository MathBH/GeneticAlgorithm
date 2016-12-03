package Inquiry;
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

}
