package GA;
import java.io.File;
import java.io.FileNotFoundException;

import MObserve.*;

/**
 * Reasoning Engine Evaluator
 * @author Mathieu
 *
 * Takes a Reasoning engine with premise type P and conclusion type C
 * and a set of examples represented by a DataShell<Example<P,C>>
 * and outputs a float fitness score from 0.0 to 1.0.
 *
 * @param <P>
 * @param <C>
 */

public class REEvaluator<P,C> extends Observable<REEResult<C>>{
	private DataSetReader<Example<P,C>> ldReader;
	
	/**
	 * 
	 * @param agent
	 * @param data
	 * @return fitness score
	 */
	public float evaluate(ReasoningEngine<P,C> agent, DataShell<Example<P,C>> data){
		float numTrials = 0.0f;
		float success = 0.0f;
		ldReader = data.getReader();
		
		while(ldReader.hasNext()){
			numTrials++;
			if (pass(agent,ldReader.next()))
				success++;
		}
		
		if (numTrials == 0.0f)
			return 1.0f;
		
		return success/numTrials;
	}
	
	/**
	 * Helper method that takes an agent and an example and returns a boolean
	 * truth value indicating if it passed or not.
	 * 
	 * @param agent
	 * @param test
	 * @return passed
	 */
	boolean pass(ReasoningEngine<P,C> agent, Example<P,C> test){ //open for testing
		C aConclusion = agent.concludeFrom(test.getPremise());
		this.notifyObservers(new REEResult<C>(aConclusion, test.getConclusion()));
		return aConclusion.equals(test.getConclusion());
	}
}