public class FloatDataSet{
	private SortedList<Float> data;
	
	public FloatDataSet(){
		data = new SortedList<Float>();
	}
	
	public void add(float val){
		data.add(val);
	}
	
	public float getMin(){
		return data.getFirst();
	}
	
	public float getAverage(){
		float avg = 0.0f;
		
		for (float val: data){
			avg += val;
		}
		
		avg /= data.size();
		
		return avg;
	}
	
	public float getMax(){
		return data.getLast();
	}
	
	

}
