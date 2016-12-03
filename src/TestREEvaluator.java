import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Inquiry.AttributeInquiry;
import Inquiry.EqualsInquiry;

public class TestREEvaluator {

	static ReasoningEngine<ArrayList<String>,Boolean> agent;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		agent = new ReasoningEngine<ArrayList<String>,Boolean>();
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
	public void testPass() {
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
		
		agent.setDecisionTree(tree_eq_president);
		
		ArrayList<String> statement1 = new ArrayList<String>();
		statement1.add("not president");
		statement1.add("stuff");
		
		ArrayList<String> statement2 = new ArrayList<String>();
		statement2.add("president");
		statement2.add("ok");
		
		ArrayList<String> statement3 = new ArrayList<String>();
		statement3.add("president");
		statement3.add("piece of shit");
		
		Example<ArrayList<String>,Boolean> test1 = new Example(statement1,true);
		Example<ArrayList<String>,Boolean> test2 = new Example(statement2,true);
		Example<ArrayList<String>,Boolean> test3 = new Example(statement3,false);
		
		REEvaluator evaluator = new REEvaluator();
		
		float score = 0.0f;
		float trials = 0.0f;
		
		System.out.println("Agent: "+agent.concludeFrom(statement1));
		System.out.println("Test: "+test1.getConclusion());
		System.out.println(evaluator.pass(agent,test1));
		System.out.println();
		trials++;
		
		System.out.println("Agent: "+agent.concludeFrom(statement2));
		System.out.println("Test: "+test2.getConclusion());
		System.out.println(evaluator.pass(agent,test2));
		System.out.println();
		trials++;
		score++;

		System.out.println("Agent: "+agent.concludeFrom(statement3));
		System.out.println("Test: "+test3.getConclusion());
		System.out.println(evaluator.pass(agent,test3));
		System.out.println();
		trials++;
		score++;

		System.out.println(score/trials);
	}

}
