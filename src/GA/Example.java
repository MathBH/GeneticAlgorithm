package GA;

/**
 * 
 * @author Mathieu
 *
 * Examples are used by Reasoning Engines to learn something.
 * They are examples of a situation where a conclusion "C" follows
 * from a premise "P".
 *
 * @param <P> Premise
 * @param <C> Conclusion
 */

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
