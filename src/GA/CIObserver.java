package GA;
import MObserve.*;
import java.util.List;

/**
 * Classifier Incubator Observer
 * @author Mathieu
 * 
 * Object that can be assigned to a Classifier Incubator and will in response
 * output information describing what the Incubator is doing.
 *
 */

public class CIObserver implements Observer<CIInfo>{
	
	public void notify(CIInfo info){
		
		FloatDataSet scoreData = compile(info.getPopulationData());
		System.out.println(info.generationNumber + "," + scoreData.getMin() + "," + scoreData.getAverage() + "," + scoreData.getMax());
	}
	
	private FloatDataSet compile(List<EvaluationEntry<Classifier,Float>> data){
		FloatDataSet scoreData = new FloatDataSet();
		
		for(EvaluationEntry<Classifier,Float> entry: data){
			scoreData.add(entry.getScore());
		}
		
		return scoreData;
	}

}
