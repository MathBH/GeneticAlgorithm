package GA;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inquiry.AttributeInquiry;
import Inquiry.EqualsInquiry;

public class TestAntTrailTracer {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		EqualsInquiry<String> equals_president = new EqualsInquiry<String>("president");
		EqualsInquiry<String> equals_pieceOfShit = new EqualsInquiry<String>("piece of shit");
		
		AttributeInquiry<String> i_equals_president = new AttributeInquiry<String>(equals_president,0);
		AttributeInquiry<String> i_equals_pieceOfShit = new AttributeInquiry<String>(equals_pieceOfShit,1);
		
		DecisionTree<ArrayList<String>,Boolean> tree_eq_pieceOfShit = new DecisionTree<ArrayList<String>,Boolean>();
		tree_eq_pieceOfShit.setDecisionFactor(i_equals_pieceOfShit);
		
		DecisionTree<ArrayList<String>,Boolean> tree_eq_president = new DecisionTree<ArrayList<String>,Boolean>();
		tree_eq_president.setDecisionFactor(i_equals_president);
		
		DecisionTree<ArrayList<String>,Boolean> tree_c_false = new DecisionTree<ArrayList<String>,Boolean>();
		tree_c_false.setConclusion(false);
		
		DecisionTree<ArrayList<String>,Boolean> tree_c_true = new DecisionTree<ArrayList<String>,Boolean>();
		tree_c_true.setConclusion(true);
		
		tree_eq_president.setNoNode(tree_c_false);
		tree_eq_president.setYesNode(tree_eq_pieceOfShit);
		tree_eq_pieceOfShit.setNoNode(tree_c_true);
		tree_eq_pieceOfShit.setYesNode(tree_c_false);
		
		AntTrailTracer<ArrayList<String>,Boolean> tracer = new AntTrailTracer();
		DecisionTree<ArrayList<String>,Boolean> lastNode = null;
		ArrayList<DecisionTree<ArrayList<String>,Boolean>> path = tracer.randomPath(tree_eq_president);
		
		System.out.println(path);
		
		for (DecisionTree<ArrayList<String>,Boolean> node: path){
			System.out.println(node);
			if (lastNode == null)
				continue; System.out.println("CONTINUED");
			
			assertFalse(lastNode.getYesNode().equals(node) || lastNode.getNoNode().equals(node));
		}
		System.out.println("------------------------------------------");
		printDecisionTree(tree_eq_president);
	}
	
	private void printDecisionTree(DecisionTree dt){
		if (dt == null)
			return;
		System.out.print(dt);
		if (dt.hasChildren())
			System.out.print(": [" + dt.getNoNode() + ", " + dt.getYesNode() + "]");
		System.out.println();
		printDecisionTree(dt.getNoNode());
		printDecisionTree(dt.getYesNode());
	}

}
