import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class ClassifierIncubator implements REIncubator<ArrayList<Float>,ArrayList<Boolean>>{
	private final float DEFAULT_ENTROPY_INCR = 1.0f;
	private final int DEFAULT_POPULATION_SIZE = 64;
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
			counter ++;
		}

		System.out.println(populationData.getFirst());
		return populationData.getFirst().getAgent();
	}
	
	public Classifier generateReasoningEngine(File ldFile, float treshold){
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
			counter ++;
		}

		System.out.println(populationData.getFirst());
		return populationData.getFirst().getAgent();
	}
	
	public void setPopulationSize(int populationSize){
		this.populationSize = populationSize;
	}
	
	private SortedList<EvaluationEntry<Classifier,Float>> nextGeneration(SortedList<EvaluationEntry<Classifier,Float>> lastGen, CLD ld){
		SortedList<EvaluationEntry<Classifier,Float>> nextGen = new SortedList<EvaluationEntry<Classifier,Float>>();

		GeneticChildPair<ArrayList<Float>,ArrayList<Boolean>> childrenGhosts;
		Classifier child1;
		Classifier child2;
		Classifier parent1;
		Classifier parent2;
		
		float fitnessBuffer;
		
		while (nextGen.size() < populationSize){
			System.out.println(nextGen.size()+" : " + populationSize);//DEBUG
			parent1 = lastGen.remove(0).getAgent();
			parent2 = lastGen.remove(0).getAgent();
			System.out.println("PARENTS : " + parent1 + " , " + parent2);//DEBUG
			System.out.println("shuffle");
			childrenGhosts = gShuffler.shuffle(parent1.getDecisionTree(),parent2.getDecisionTree());
			System.out.println("done");
			
			child1 = new Classifier(childrenGhosts.getFirstChild());
			fitnessBuffer = evaluator.evaluate(child1, ld);
			
			nextGen.add(new EvaluationEntry<Classifier,Float>(child1,fitnessBuffer));
			
			if(nextGen.size() >= populationSize)
				break;
			
			child2 = new Classifier(childrenGhosts.getSecondChild());
			fitnessBuffer = evaluator.evaluate(child2, ld);
			
			nextGen.add(new EvaluationEntry<Classifier,Float>(child2,fitnessBuffer));
			System.out.println("CHILDREN : " + child1 + " , " + child2);//DEBUG
		}
		
		return nextGen;
	}
	
	private boolean pass(float score){
		return score >= this.treshold;
	}
	
	public void setEntropyIncrement(float entropyIncr){
		this.entropyIncr = entropyIncr;
	}
}
