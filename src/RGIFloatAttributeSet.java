import java.util.ArrayList;

import Inquiry.AttributeInquiry;
import Inquiry.EqualsInquiry;
import Inquiry.GreaterThanInquiry;
import Inquiry.Inquiry;
import Inquiry.SmallerThanInquiry;
import Inquiry.ValInquiry;

public class RGIFloatAttributeSet extends RGInquiry<ArrayList<Float>>{	//fix the mess from ArrayList being newly added
	
	private final int NUM_INQUIRY_TYPES = 3;
	private final float DEFAULT_CRITERIA_MIN = -5.0f;
	private final float DEFAULT_CRITERIA_MAX = 5.0f;
	
	private int length;
	private float criteriaMin;
	private float criteriaMax;
	
	public RGIFloatAttributeSet(int length, float criteriaMax){
		this.length = length;
		this.criteriaMin = DEFAULT_CRITERIA_MIN;
		this.criteriaMax = criteriaMax;
	}
	
	public RGIFloatAttributeSet(int length, float criteriaMin, float criteriaMax){
		this.length = length;
		this.criteriaMin = criteriaMin;
		this.criteriaMax = criteriaMax;
	}
	
	public RGIFloatAttributeSet(int length){
		this.length = length;
		this.criteriaMin = DEFAULT_CRITERIA_MIN;
		this.criteriaMax = DEFAULT_CRITERIA_MAX;
	}
	
	@Override
	public AttributeInquiry<Float> randomGenerate() {
		
		ValInquiry<Float> inquiryBuffer = null;
		float criteriaBuffer;
		
		int index = rollForInterval(this.length);
		
		switch(this.rollForInterval(NUM_INQUIRY_TYPES)){
		case 0:
			criteriaBuffer = this.rollForInterval(criteriaMin, criteriaMax);
			inquiryBuffer = new EqualsInquiry(criteriaBuffer);
			break;
		case 1:
			criteriaBuffer = this.rollForInterval(criteriaMin, criteriaMax);
			inquiryBuffer = new GreaterThanInquiry(criteriaBuffer);
			break;
		case 2:
			criteriaBuffer = this.rollForInterval(criteriaMin, criteriaMax);
			inquiryBuffer = new SmallerThanInquiry(criteriaBuffer);
			break;
		}
		
		return new AttributeInquiry(inquiryBuffer,index);
	}
	
	public AttributeInquiry<Float> randomGenerate(float hardCriteria) {
		
		ValInquiry<Float> inquiryBuffer = null;
		
		int index = rollForInterval(this.length);
		
		switch(this.rollForInterval(NUM_INQUIRY_TYPES)){
		case 0:
			inquiryBuffer = new EqualsInquiry(hardCriteria);
			break;
		case 1:
			inquiryBuffer = new GreaterThanInquiry(hardCriteria);
			break;
		case 2:
			inquiryBuffer = new SmallerThanInquiry(hardCriteria);
			break;
		}
		
		return new AttributeInquiry(inquiryBuffer,index);
	}
	
	public AttributeInquiry<Float> randomGenerate(float criteriaMin, float criteriaMax) {
		
		ValInquiry<Float> inquiryBuffer = null;
		float criteriaBuffer;
		
		int index = rollForInterval(this.length);
		
		switch(this.rollForInterval(NUM_INQUIRY_TYPES)){
		case 0:
			criteriaBuffer = this.rollForInterval(criteriaMin, criteriaMax);
			inquiryBuffer = new EqualsInquiry(criteriaBuffer);
			break;
		case 1:
			criteriaBuffer = this.rollForInterval(criteriaMin, criteriaMax);
			inquiryBuffer = new GreaterThanInquiry(criteriaBuffer);
			break;
		case 2:
			criteriaBuffer = this.rollForInterval(criteriaMin, criteriaMax);
			inquiryBuffer = new SmallerThanInquiry(criteriaBuffer);
			break;
		}
		
		return new AttributeInquiry(inquiryBuffer,index);
	}
	
	private float rollForInterval(float min, float max){
		float length = max - min;
		if (length < 0)
			length = 0;
		return min + rollForInterval(length);
	}
	
}
