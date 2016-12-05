import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;

public class ClassifierEvaluator extends REEvaluator<ArrayList<Float>,ArrayList<Boolean>>{
	public float evaluate(Classifier agent, File dataFile){
		CLD ld = null;
		try{
			ld = new CLD(dataFile);
		}catch(FileNotFoundException e){
			System.err.println("FILE NOT FOUND: "+dataFile);
			System.exit(313);
		}
		return evaluate(agent,ld);
	}
}
