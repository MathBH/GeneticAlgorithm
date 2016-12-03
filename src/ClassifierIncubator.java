import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class ClassifierIncubator implements REIncubator<ArrayList<Float>,ArrayList<Boolean>>{
	private ClassifierGenerator generator;
	private REEvaluator evaluator;
	
	public ClassifierIncubator(){
		evaluator = new REEvaluator();
	}
	
	public Classifier generateReasoningEngine(int populationSize, File ldFile){
		CLD ld;
		ArrayList<Classifier> population;
		
		try{
			ld = new CLD(ldFile);
		}catch(FileNotFoundException e){
			System.err.println("FILE NOT FOUND: "+ldFile);
			return null;
		}
		
		population = new ArrayList<Classifier>();
		generator = new ClassifierGenerator(ld.getNumAttrs(),ld.getNumClass());
		
		for (int i = 0; i < populationSize; i++){
			population.add(generator.generateRa;
		}
		
		return null;
	}
}
