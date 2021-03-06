package GA;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import MObserve.*;
/**
 * Standard Classifier Incubator
 * @author Mathieu
 * 
 * This is where the Genetic Algorithm is.
 * Incubators are what make the AI "learn", though "grow" might be a better
 * metaphor.
 * 
 * The Incubator can take in a learning data file and output a Reasoning Engine.
 *
 */
public class ClassifierIncubator extends Observable<CIInfo> implements REIncubator<ArrayList<Float>,ArrayList<Boolean>>{
	private final float DEFAULT_MUTATION_RATE = 1.0f;
	private final int DEFAULT_POPULATION_SIZE = 64;
	private final int DEFAULT_LEAF_CAP= 64;	//TODO: fix this ugly hack fix
	private final int MAX_GENERATIONS = 200;
	private ClassifierGenerator generator;
	private REEvaluator<ArrayList<Float>,ArrayList<Boolean>> evaluator;
	private float mutationRate;
	
	private float treshold;
	private int populationSize;
	
	private GeneticMutator<ArrayList<Float>,ArrayList<Boolean>> mutator;
	private GeneticShuffler<ArrayList<Float>,ArrayList<Boolean>> gShuffler;
	
	public ClassifierIncubator(){
		setMutationRate(DEFAULT_MUTATION_RATE);
		setPopulationSize(DEFAULT_POPULATION_SIZE);
		evaluator = new REEvaluator<ArrayList<Float>,ArrayList<Boolean>>();
	}
	
	public ClassifierIncubator(float mutationRate){
		setMutationRate(mutationRate);
		setPopulationSize(DEFAULT_POPULATION_SIZE);
		evaluator = new REEvaluator<ArrayList<Float>,ArrayList<Boolean>>();
	}
	
	public ClassifierIncubator(int populationSize, float mutationRate){
		setMutationRate(mutationRate);
		setPopulationSize(populationSize);
		evaluator = new REEvaluator<ArrayList<Float>,ArrayList<Boolean>>();
	}
	
	/**
	 * 
	 * Takes an ldFile (learning data) and attempts to grow a Classifier.
	 * The threshold is used to determine the minimum success rate
	 * required of the AI. The incubator will continue to try to
	 * grow the AI to acheive this success rate unless MAX_GENERATIONS
	 * is reached at which point it will stop and return its best
	 * performing AI instead.
	 * 
	 * @param leafCap max leaves in Classifiers' decision trees.
	 * @param populationSize population size per generation
	 * @param mutationRate degree to which children will mutate
	 * @param ldFile (learning data)
	 * @param treshold fitness demanded of the final Classifier
	 * @return a Classifier
	 */
	public Classifier generateReasoningEngine(int leafCap, int populationSize, float mutationRate, File ldFile, float treshold){
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
		
		this.notifyObservers(new CIInfo(populationData,counter));
		
		while(!pass(populationData.getFirst().getScore())){
			if (counter >= MAX_GENERATIONS){
				break;
			}
			populationData = nextGeneration(populationData, ld);
			counter ++;
			this.notifyObservers(new CIInfo(populationData,counter));
		}
		return populationData.getFirst().getAgent();
	}
	
	public Classifier generateReasoningEngine(File ldFile, float treshold){ 	//TODO: fix this ugly hack fix
		return generateReasoningEngine(DEFAULT_LEAF_CAP, populationSize, DEFAULT_MUTATION_RATE, ldFile, treshold);
	}
	
	public void setPopulationSize(int populationSize){
		this.populationSize = populationSize;
	}

	public void setMutationRate(float mutationRate){
		this.mutationRate = mutationRate;
	}
	
	/**
	 * Takes in a population and returns the population for the next generation.
	 * 
	 * @param lastGen last generation AI list
	 * @param ld learning data
	 * @return
	 */
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
			parent1Data = lastGen.remove(0);
			parent2Data = lastGen.remove(0);
			parent1 = parent1Data.getAgent();
			parent2 = parent2Data.getAgent();
			
			childrenGhosts = gShuffler.shuffle(parent1.getDecisionTree(),parent2.getDecisionTree());
			
			child1 = new Classifier(childrenGhosts.getFirstChild());
			fitnessBuffer = evaluator.evaluate(child1, ld);
			child1.setDecisionTree(mutator.mutate(childrenGhosts.getFirstChild(),(1.0f -fitnessBuffer)*mutationRate));
			
			nextGen.add(new EvaluationEntry<Classifier,Float>(child1,fitnessBuffer));
			
			if(nextGen.size() >= populationSize)
				break;
			
			child2 = new Classifier(childrenGhosts.getSecondChild());
			fitnessBuffer = evaluator.evaluate(child2, ld);
			child2.setDecisionTree(mutator.mutate(childrenGhosts.getSecondChild(),(1.0f -fitnessBuffer)*mutationRate));
			
			nextGen.add(new EvaluationEntry<Classifier,Float>(child2,fitnessBuffer));
		}
		return nextGen;
	}
	
	/**
	 * Helper method tells if a given score meets the requested treshold or not.
	 * @param score
	 * @return true if score meets threshold
	 */
	private boolean pass(float score){
		return score >= this.treshold;
	}
}
