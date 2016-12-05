import java.util.ArrayList;

public class AntTrailGS<P,C> implements GeneticShuffler<P,C>{
	private ArrayList<DecisionTree<P,C>> trail1;
	private ArrayList<DecisionTree<P,C>> trail2;
	
	private DTPathTracer tracer;
	private Die die;
	
	public AntTrailGS(){
		tracer = new AntTrailTracer();
		die = new Die();
	}

	@Override
	public GeneticChildPair<P, C> shuffle(DecisionTree<P, C> parent1, DecisionTree<P, C> parent2) {	//TODO: figure if this should take in a ReasoningEngine instead (this is why their called ghosts instead of children atm coz wa supposed to be put in children originally)
		DecisionTree<P, C> ghost1;
		DecisionTree<P, C> ghost2;
		
		trail1 = tracer.randomPath(parent1);
		trail2 = tracer.randomPath(parent2);
		
		int idx1 = die.roll(trail1.size());
		int idx2 = die.roll(trail2.size());
		
		if (idx1 >= 1){
			if(trail1.get(idx1-1).getYesNode().equals(trail1.get(idx1)))
				trail1.get(idx1-1).setYesNode(trail2.get(idx2));
			else if (trail1.get(idx1-1).getNoNode().equals(trail1.get(idx1)))
				trail1.get(idx1-1).setNoNode(trail2.get(idx2));
			else{
				System.err.println("Uhm, it wasn't the no node or the yes node???");
				return null;
			}
			ghost1 = trail1.get(0);
		} else {
			ghost1 = trail2.get(idx2);
		}
		
		if (idx2 >= 1){
			if(trail2.get(idx2-1).getYesNode().equals(trail2.get(idx2)))
				trail2.get(idx2-1).setYesNode(trail1.get(idx1));
			else if (trail2.get(idx2-1).getNoNode().equals(trail2.get(idx2)))
				trail2.get(idx2-1).setNoNode(trail1.get(idx1));
			else{
				System.err.println("Uhm, it wasn't the no node or the yes node???");
				return null;
			}
			ghost2 = trail2.get(0);
		} else {
			ghost2 = trail1.get(idx1);
		}
			
		return new GeneticChildPair<P,C>(ghost1,ghost2);
	}

}
