package Inquiry;
import java.util.ArrayList;

/**
 * 
 * @author Mathieu
 *
 * @param <P>
 * 
 * problem that remains: no way of assuring the passed subInquiry is of the correct type for the List
 * 
 */

public class AttributeInquiry<T> implements Inquiry<ArrayList<T>>, CriteriaBased<T>{
	private ValInquiry<T> inquiry;
	private int index;
	
	public AttributeInquiry(ValInquiry<T> inquiry, int index){
		this.inquiry = inquiry;
		this.index = index;
	}

	@Override
	public boolean isTrueFor(ArrayList<T> input) {
		return inquiry.isTrueFor(input.get(index));
	}

	@Override
	public String toString(){
		return ("attr"+" [" + this.index +"]"+ this.inquiry);
	}

	@Override
	public T getCriteria() {
		return this.inquiry.getCriteria();
	}

	@Override
	public void setCriteria(T criteria) {
		this.inquiry.setCriteria(criteria);
	}
}
