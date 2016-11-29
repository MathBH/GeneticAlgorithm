package Inquiry;
import java.util.List;

/**
 * 
 * @author Mathieu
 *
 * @param <P>
 * 
 * problem that remains: no way of assuring the passed subInquiry is of the correct type for the List
 * 
 */

public class IndexInquiry<P extends List> extends Inquiry<P>{
	private Inquiry inquiry;
	private int index;
	
	public IndexInquiry(Inquiry inquiry, int index){
		this.inquiry = inquiry;
		this.index = index;
	}

	@Override
	public boolean isTrueFor(P input) {
		return inquiry.isTrueFor(input.get(index));
	}

	@Override
	public String toString(){
		return (this.getClass().toString() + " : " + this.inquiry.getClass().toString() + " [" + this.index +"]");
	}
}
