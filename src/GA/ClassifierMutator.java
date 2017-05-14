package GA;
import java.util.ArrayList;

import Inquiry.AttributeInquiry;
import Inquiry.Inquiry;

/**
 * Classifier Mutator
 * @author Mathieu
 *
 * Object that takes in an AI's decision tree and outputs a mutated version.
 * 
 * TODO: fix it to take in an AI and output a mutated AI
 *
 */

public class ClassifierMutator implements GeneticMutator<ArrayList<Float>,ArrayList<Boolean>>{
	private DTPathTracer tracer;
	private RGIFloatAttributeSet attrGen;
	private Die die;
	private Coin coin;
	private Deviator dev;

	
	public ClassifierMutator(int numAttr){ //TODO: this is very ugly pls fix at some point so that don't have to pass in numAttr
		tracer = new AntTrailTracer();
		die = new Die();
		attrGen = new RGIFloatAttributeSet(numAttr);
		coin = new Coin();
		dev = new Deviator();
	}

	@Override
	public DecisionTree<ArrayList<Float>, ArrayList<Boolean>> mutate(
			DecisionTree<ArrayList<Float>, ArrayList<Boolean>> decisionTree, float entropy) {
		
		ArrayList<DecisionTree<ArrayList<Float>, ArrayList<Boolean>>> trail = tracer.randomPath(decisionTree);
		DecisionTree<ArrayList<Float>, ArrayList<Boolean>> targetNode =trail.get(die.roll(trail.size()));
		Inquiry<ArrayList<Float>> inquiry = targetNode.getDecisionFactor();
		
		if(inquiry == null)
			return decisionTree;
		if (!inquiry.getClass().isAssignableFrom(AttributeInquiry.class))
			return decisionTree;
		
		AttributeInquiry<Float> attrIq = (AttributeInquiry<Float>) inquiry;
		float origCriteria = attrIq.getCriteria();
		
		if(coin.flip(entropy))
			attrIq = attrGen.randomGenerate(origCriteria);
		
		float newCriteria = dev.deviate(origCriteria, origCriteria*entropy);
		attrIq.setCriteria(newCriteria);
		
		targetNode.setDecisionFactor(attrIq);
		
		return trail.get(0);
	}

}
