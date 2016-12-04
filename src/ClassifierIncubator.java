import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.io.FileNotFoundException;

public class ClassifierIncubator extends Observable implements REIncubator<ArrayList<Float>,ArrayList<Boolean>>{
	private final float DEFAULT_ENTROPY_INCR = 1.0f;
	private final int DEFAULT_POPULATION_SIZE = 64;
	private final int DEFAULT_LEAF_CAP= 64;	//TODO: fix this ugly hack fix
	private final int MAX_GENERATIONS = 90000;
	private ClassifierGenerator generator;
	private REEvaluator evaluator;
	private float entropyIncr;
	
	private float treshold;
	private int populationSize;
	
	private GeneticMutator<ArrayList<Float>,ArrayList<Boolean>> mutator;
	private GeneticShuffler<ArrayList<Float>,ArrayList<Boolean>> gShuffler;
	
	public ClassifierIncubator(){
		setEntropyIncrement(DEFAULT_ENTROPY_INCR);
		setPopulationSize(DEFAULT_POPULATION_SIZE);
		evaluator = new REEvaluator();
	}
	
	public ClassifierIncubator(float entropyIncr){
		setEntropyIncrement(entropyIncr);
		setPopulationSize(DEFAULT_POPULATION_SIZE);
		evaluator = new REEvaluator();
	}
	
	public ClassifierIncubator(int populationSize, float entropyIncr){
		setEntropyIncrement(entropyIncr);
		setPopulationSize(populationSize);
		evaluator = new REEvaluator();
	}
	
	public Classifier generateReasoningEngine(int leafCap, int populationSize, File ldFile, float treshold){
		setPopulationSize(populationSize);
		this.treshold = treshold;
		CLD ld;
		SortedList<EvaluationEntry<Classifier,Float>> populationData;
		
		try{
			ld = new CLD(ldFile);
		}catch(FileNotFoundException e){
			System.err.println("FILE NOT FOUND: "+ldFile);
			return null;
		}
		
		populationData = new SortedList<EvaluationEntry<Classifier,Float>>();
		generator = new ClassifierGenerator(ld.getNumAttrs(),ld.getNumClass());
		generator.setLeafCap(leafCap);
		
		Classifier agentBuffer;
		float fitnessScoreBuffer;
		for (int i = 0; i < populationSize; i++){
			agentBuffer = generator.generateRandom();
			fitnessScoreBuffer = evaluator.evaluate(agentBuffer, ld);
			populationData.add(new EvaluationEntry<Classifier,Float>(agentBuffer,fitnessScoreBuffer));
		}
		
		mutator = new ClassifierMutator(ld.getNumAttrs());
		gShuffler = new AntTrailGS();
		
		int counter = 0;
		while(!pass(populationData.getFirst().getScore())){
			if (counter >= MAX_GENERATIONS){
				System.out.println("BREAK");//DEBUG
				break;
			}
			System.out.println(populationData.getFirst());
			populationData = nextGeneration(populationData, ld);
			System.out.println("NEW GEN: " + populationData);//DEBUG
			counter ++;
		}

		System.out.println("GENERATIONS NEEDED: " + counter);
		System.out.println(populationData.getFirst());
		return populationData.getFirst().getAgent();
	}
	
	public Classifier generateReasoningEngine(File ldFile, float treshold){ 	//TODO: fix this ugly hack fix
		return generateReasoningEngine(DEFAULT_LEAF_CAP, populationSize,ldFile, treshold);
	}
	
	public void setPopulationSize(int populationSize){
		this.populationSize = populationSize;
	}
	
	private SortedList<EvaluationEntry<Classifier,Float>> nextGeneration(SortedList<EvaluationEntry<Classifier,Float>> lastGen, CLD ld){
		SortedList<EvaluationEntry<Classifier,Float>> nextGen = new SortedList<EvaluationEntry<Classifier,Float>>();

		GeneticChildPair<ArrayList<Float>,ArrayList<Boolean>> childrenGhosts;
		Classifier child1;
		Classifier child2;
		EvaluationEntry<Classifier,Float> parent1Data;
		EvaluationEntry<Classifier,Float> parent2Data;
		Classifier parent1;
		Classifier parent2;
		
		float fitnessBuffer;
		
		while (nextGen.size() < populationSize){							//WARNING: only works for even number populations coz needs 2 parents
			System.out.println(nextGen.size()+" : " + populationSize);//DEBUG
			parent1Data = lastGen.remove(0);
			parent2Data = lastGen.remove(0);
			parent1 = parent1Data.getAgent();
			parent2 = parent2Data.getAgent();
			
			System.out.println("PARENTS : " + parent1 + " , " + parent2);//DEBUG
			System.out.println("shuffle");
			childrenGhosts = gShuffler.shuffle(parent1.getDecisionTree(),parent2.getDecisionTree());
			System.out.println("done");
			
			child1 = new Classifier(childrenGhosts.getFirstChild());
			fitnessBuffer = evaluator.evaluate(child1, ld);
			child1.setDecisionTree(mutator.mutate(childrenGhosts.getFirstChild(),1.0f -fitnessBuffer));
			System.out.println("FITNESS: " + fitnessBuffer);//DEBUG
			
			nextGen.add(new EvaluationEntry<Classifier,Float>(child1,fitnessBuffer));
			
			if(nextGen.size() >= populationSize)
				break;
			
			child2 = new Classifier(childrenGhosts.getSecondChild());
			fitnessBuffer = evaluator.evaluate(child2, ld);
			child2.setDecisionTree(mutator.mutate(childrenGhosts.getSecondChild(),1.0f -fitnessBuffer));
			System.out.println("FITNESS: " + fitnessBuffer);//DEBUG
			
			nextGen.add(new EvaluationEntry<Classifier,Float>(child2,fitnessBuffer));
			System.out.println("CHILDREN : " + child1 + " , " + child2);//DEBUG
		}
		
		Classifier newCommer = generator.generateRandom();
		nextGen.add(new EvaluationEntry<Classifier,Float>(newCommer, evaluator.evaluate(newCommer,ld)));

		newCommer = generator.generateRandom();
		nextGen.add(new EvaluationEntry<Classifier,Float>(newCommer, evaluator.evaluate(newCommer,ld)));
		
		return nextGen;
	}
	
	private boolean pass(float score){
		return score >= this.treshold;
	}
	
	public void setEntropyIncrement(float entropyIncr){
		this.entropyIncr = entropyIncr;
	}
}
