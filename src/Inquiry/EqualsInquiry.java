package Inquiry;
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
}
