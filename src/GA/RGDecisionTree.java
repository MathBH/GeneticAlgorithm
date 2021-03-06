package GA;
import java.util.ArrayList;

import Inquiry.Inquiry;

/**
 * Random Generator Decision Tree
 * @author Mathieu
 *
 * responsible for random generation of decision trees of premise type P and
 * conclusion type C.
 *
 * @param <P> Premise type
 * @param <C> Conclusion type
 * 
 * TODO: find better name
 */

public class RGDecisionTree<P,C> extends RandomGenerator<DecisionTree<P,C>>{

	private final int DEFAULT_LEAF_CAP = 64;
	
	private RGInquiry<P> inquiryRG;
	private RandomGenerator<C> conclusionRG;
	private int leafCap;
	
	/**
	 * Constructor
	 * @param inquiryRG
	 * @param conclusionRG
	 */
	
	public RGDecisionTree(RGInquiry<P> inquiryRG, RandomGenerator<C> conclusionRG){
		super();
		this.inquiryRG = inquiryRG;
		this.conclusionRG = conclusionRG;
		this.leafCap = DEFAULT_LEAF_CAP;
	}
	
	/**
	 * Set maximum possible leaves in a randomly generated tree
	 * @param leafCap
	 */
	
	public void setLeafCap(int leafCap){
		this.leafCap = leafCap;
	}
	
	/**
	 * Decision Tree generator
	 */
	
	public DecisionTree<P,C> randomGenerate(int numLeaves) {
		if(numLeaves <= 0)
			numLeaves = 1;
		ArrayList<DecisionTree<P,C>> treeParts= new ArrayList<DecisionTree<P,C>>();
		
		Inquiry<P> inquiryBuffer;
		C conclusionBuffer;
		DecisionTree<P,C> nodeBuffer;
		
		for (int i = 0; i < numLeaves; i++){
			conclusionBuffer = this.conclusionRG.randomGenerate();
			nodeBuffer = new DecisionTree<P,C>();
			nodeBuffer.setConclusion(conclusionBuffer);
			treeParts.add(nodeBuffer);
		}
		
		int i1;
		int i2;
		DecisionTree<P,C> node1;
		DecisionTree<P,C> node2;
		DecisionTree<P,C> tree;
		
		while (treeParts.size() > 1){
			i1 = this.rollForInterval(treeParts.size());
			node1 = treeParts.get(i1);
			treeParts.remove(i1);
			
			i2 = this.rollForInterval(treeParts.size());
			node2 = treeParts.get(i2);
			treeParts.remove(i2);
			
			tree = new DecisionTree<P,C>();
			tree.setNoNode(node1);
			tree.setYesNode(node2);
			
			inquiryBuffer = this.inquiryRG.randomGenerate();
			tree.setDecisionFactor(inquiryBuffer);
			
			treeParts.add(tree);
		}
		
		return treeParts.get(0);
	}
	
	@Override
	public DecisionTree<P,C> randomGenerate() {
		int numLeaves = this.rollForInterval(leafCap) + 1;	//to be used as size value for treeParts collection
		return randomGenerate(numLeaves);
	}
	
}
