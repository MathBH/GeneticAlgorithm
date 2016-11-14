import java.io.File;

public abstract class AlgorithmEvaluator<P,C,V> {
	protected TrainingDataParser tdParser;
	
	public abstract V evaluate(ReasoningEngine<P,C> agent, TrainingData<P,C> trainingData);
}