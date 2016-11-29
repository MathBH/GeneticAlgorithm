import java.util.ArrayList;

import Inquiry.EqualsInquiry;
import Inquiry.GreaterThanInquiry;
import Inquiry.IndexInquiry;
import Inquiry.Inquiry;
import Inquiry.SmallerThanInquiry;

public class RGIFloatAttributeSet extends RGInquiry<ArrayList<Float>>{	//fix the mess from ArrayList being newly added
	
	private final int NUM_INQUIRY_TYPES = 3;
	private final float DEFAULT_CRITERIA_MAX = 5.0f;
	
	private int length;
	
	public RGIFloatAttributeSet(int length){
		this.length = length;
	}
	
	@Override
	public Inquiry<ArrayList<Float>> randomGenerate() {
		
		Inquiry<Float> inquiryBuffer = null;
		float criteriaBuffer;
		
		int index = rollForInterval(this.length);
		
		switch(this.rollForInterval(NUM_INQUIRY_TYPES)){
		case 0:
			criteriaBuffer = this.rollForInterval(DEFAULT_CRITERIA_MAX);
			inquiryBuffer = new EqualsInquiry(criteriaBuffer);
			break;
		case 1:
			criteriaBuffer = this.rollForInterval(DEFAULT_CRITERIA_MAX);
			inquiryBuffer = new GreaterThanInquiry(criteriaBuffer);
			break;
		case 2:
			criteriaBuffer = this.rollForInterval(DEFAULT_CRITERIA_MAX);
			inquiryBuffer = new SmallerThanInquiry(criteriaBuffer);
			break;
		}
		
		return new IndexInquiry(inquiryBuffer,index);
	}
	
}
