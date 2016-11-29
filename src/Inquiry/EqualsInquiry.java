package Inquiry;
public class EqualsInquiry<E extends Comparable> extends Inquiry<E>{
	private E criteria;
	
	public EqualsInquiry(E criteria){
		this.criteria = criteria;
	}

	@Override
	public boolean isTrueFor(E input) {
		return input.compareTo(criteria) == 0;
	}
}
