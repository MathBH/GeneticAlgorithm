package GA;

public class EvaluationEntry<R extends ReasoningEngine, F extends Comparable> implements Comparable<EvaluationEntry<R, F>>{

	private R agent;
	private F score;
	
	public EvaluationEntry(R agent, F score){
		this.agent = agent;
		this.score = score;
	}
	
	public R getAgent(){
		return this.agent;
	}
	
	public F getScore(){
		return this.score;
	}

	@Override
	public int compareTo(EvaluationEntry<R, F> otherEntry) {
		return otherEntry.getScore().compareTo(this.score);
	}
	
	@Override
	public String toString(){
		return "AGENT SCORE: "+ agent.toString() + " : " + score.toString();
	}
}
