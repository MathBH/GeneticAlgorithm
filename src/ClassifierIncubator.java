import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class ClassifierIncubator implements REIncubator<ArrayList<Float>,ArrayList<Boolean>>{
	private final float DEFAULT_ENTROPY_INCR = 1.0f;
	private ClassifierGenerator generator;
	private REEvaluator evaluator;
	private float entropyIncr;
	
	public ClassifierIncubator(){
		setEntropyIncrement(DEFAULT_ENTROPY_INCR);
		evaluator = new REEvaluator();
	}
	
	public ClassifierIncubator(float entropyIncr){
		setEntropyIncrement(entropyIncr);
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
			population.add(generator.generateRandom());
		}
		
		return null;
	}
	
	public void setEntropyIncrement(float entropyIncr){
		this.entropyIncr = entropyIncr;
	}
}
