package GA;

public class CIInfo {
	SortedList<EvaluationEntry<Classifier,Float>> populationData;
	int generationNumber;
	
	public CIInfo(SortedList<EvaluationEntry<Classifier,Float>> populationData, int generationNumber){
		this.populationData = populationData;
		this.generationNumber = generationNumber;
	}
	
	public SortedList<EvaluationEntry<Classifier,Float>> getPopulationData(){
		return this.populationData;
	}
	
	public int getGenerationNumber(){
		return this.generationNumber;
	}
}
