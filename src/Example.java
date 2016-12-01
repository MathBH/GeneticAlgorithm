
public class Example <P,C>{

	private P premise;
	private C conclusion;
	
	public Example(P premise, C conclusion){
		this.premise = premise;
		this.conclusion = conclusion;
	}
	
	public P getPremise(){
		return this.premise;
	}
	
	public C getConclusion(){
		return this.conclusion;
	}
}
