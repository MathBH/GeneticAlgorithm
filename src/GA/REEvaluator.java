package GA;
import java.io.File;
import java.io.FileNotFoundException;

import MObserve.*;

public class REEvaluator<P,C> extends Observable<REEResult<C>>{
	private DataSetReader<Example<P,C>> ldReader;
	
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
	
	boolean pass(ReasoningEngine<P,C> agent, Example<P,C> test){ //open for testing
		C aConclusion = agent.concludeFrom(test.getPremise());
		this.notifyObservers(new REEResult<C>(aConclusion, test.getConclusion()));
		return aConclusion.equals(test.getConclusion());
	}
}