package Inquiry;
public class GreaterThanInquiry<E extends Comparable> extends Inquiry<E>{
	private E criteria;
	
	public GreaterThanInquiry(E criteria){
		this.criteria = criteria;
	}

	@Override
	public boolean isTrueFor(E input) {
		return input.compareTo(criteria) > 0;
	}

}