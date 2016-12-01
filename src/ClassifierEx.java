import java.util.ArrayList;

public class ClassifierEx extends Example<ArrayList<Float>,ArrayList<Boolean>>{
	
	public ClassifierEx(ArrayList<Float> attr, ArrayList<Boolean> classifier) {
		super(attr,classifier);
	}
	
	@Override
	public String toString(){
		return this.getPremise().toString() + " : " + this.getConclusion().toString();
	}
}
