package Inquiry;
public class SmallerThanInquiry<E extends Comparable> extends Inquiry<E>{
	private E criteria;
	
	public SmallerThanInquiry(E criteria){
		this.criteria = criteria;
	}
	
	@Override
	public boolean isTrueFor(E input) {
		return input.compareTo(criteria) < 0;
	}

}
