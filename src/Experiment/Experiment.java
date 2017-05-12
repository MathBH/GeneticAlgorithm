package Experiment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;

import GA.*;
/**
 * Project Experiment.
 * @author Mathieu
 *
 */
public class Experiment {
	private static final String SRC = "src/Experiment/";
	private static String RESULTS_1 = "results1/";
	private static String RESULTS_2 = "results2/";
	private static String RESULTS_3 = "results3/";
	private static String RESULTS_4 = "results4/";
	private static String RESULTS_5 = "results5/";
	private static String RESULTS_6 = "results6/";
	private static String RESULTS_7 = "results7/";
	private static String RESULTS_8 = "results8/";
	private static String RESULTS_9 = "results9/";
	private static String[] RESULTS = {RESULTS_1, RESULTS_2, RESULTS_3,
										RESULTS_4, RESULTS_5, RESULTS_6,
										RESULTS_7, RESULTS_8, RESULTS_9};
	
	private static final String BASIC_INCUBATOR = "basicIncubator/";
	private static final String REROLL_INCUBATOR = "rerollIncubator/";
	private static final String[] INCUBATOR_NAMES = {BASIC_INCUBATOR, REROLL_INCUBATOR};
	
	private static final String FITNESS_FILE = "fitness.txt";
	private static final String CONFUSION_MATRIX = "confusion.txt";
	private static final String TEST_SCORE = "score.txt";
	private static final String[] OUTPUT_FILES = {FITNESS_FILE, CONFUSION_MATRIX, TEST_SCORE};
	
	private static final String TESTING_DATA = "testing-bpformat.txt";
	private static final String TRAINING_DATA = "training-bpformat.txt";
	
	private static final String IRIS = "iris";
	private static final String IRIS_LARGE = "irisLarge";
	private static final String BIMODAL = "bimodal";
	private static final String SPIRAL = "spiral";
	private static final String GLASS = "glass";
	private static final String[] DATA_NAMES = {IRIS, IRIS_LARGE, BIMODAL, SPIRAL, GLASS};
	
	private static ClassifierIncubator basicIncubator;
	private static RerollCI rerollIncubator;
	private static ClassifierEvaluator evaluator;
	private static FloatScoreHandler scoreHandler;

	private static String results;
	
	public static void main(String[] args) throws FileNotFoundException{
		buildAllOutputFiles();
		
		basicIncubator = new ClassifierIncubator();
		rerollIncubator = new RerollCI();
		evaluator = new ClassifierEvaluator();
		scoreHandler = new FloatScoreHandler();
		
		basicIncubator.addObserver(new CIObserver());
		rerollIncubator.addObserver(new CIObserver());
		evaluator.addObserver(new CEObserver());
		scoreHandler.addObserver(new FloatScoreObserver());
		
		results = RESULTS_1;
		
		runExperiment(64, 64, 1.0f, IRIS, 0.95f);
		runExperiment(64, 64, 1.0f, IRIS_LARGE, 0.95f);
		runExperiment(64, 64, 1.0f, BIMODAL, 0.95f);
		runExperiment(64, 64, 1.0f, SPIRAL, 0.95f);
		runExperiment(64, 64, 1.0f, GLASS, 0.95f);
		
		results = RESULTS_2;
		
		runExperiment(64, 64, 2.0f, IRIS, 0.95f);
		runExperiment(64, 64, 2.0f, IRIS_LARGE, 0.95f);
		runExperiment(64, 64, 2.0f, BIMODAL, 0.95f);
		runExperiment(64, 64, 2.0f, SPIRAL, 0.95f);
		runExperiment(64, 64, 2.0f, GLASS, 0.95f);
		
		results = RESULTS_3;
		
		runExperiment(64, 64, 5.0f, IRIS, 0.95f);
		runExperiment(64, 64, 5.0f, IRIS_LARGE, 0.95f);
		runExperiment(64, 64, 5.0f, BIMODAL, 0.95f);
		runExperiment(64, 64, 5.0f, SPIRAL, 0.95f);
		runExperiment(64, 64, 5.0f, GLASS, 0.95f);
		
		results = RESULTS_4;
		
		runExperiment(25, 64, 1.0f, IRIS, 0.95f);
		runExperiment(25, 64, 1.0f, IRIS_LARGE, 0.95f);
		runExperiment(25, 64, 1.0f, BIMODAL, 0.95f);
		runExperiment(25, 64, 1.0f, SPIRAL, 0.95f);
		runExperiment(25, 64, 1.0f, GLASS, 0.95f);
		
		results = RESULTS_5;
		
		runExperiment(128, 64, 1.0f, IRIS, 0.95f);
		runExperiment(128, 64, 1.0f, IRIS_LARGE, 0.95f);
		runExperiment(128, 64, 1.0f, BIMODAL, 0.95f);
		runExperiment(128, 64, 1.0f, SPIRAL, 0.95f);
		runExperiment(128, 64, 1.0f, GLASS, 0.95f);
		
		results = RESULTS_6;
		
		runExperiment(64, 10, 1.0f, IRIS, 0.95f);
		runExperiment(64, 10, 1.0f, IRIS_LARGE, 0.95f);
		runExperiment(64, 10, 1.0f, BIMODAL, 0.95f);
		runExperiment(64, 10, 1.0f, SPIRAL, 0.95f);
		runExperiment(64, 10, 1.0f, GLASS, 0.95f);
		
		results = RESULTS_7;
		
		runExperiment(64, 256, 1.0f, IRIS, 0.95f);
		runExperiment(64, 256, 1.0f, IRIS_LARGE, 0.95f);
		runExperiment(64, 256, 1.0f, BIMODAL, 0.95f);
		runExperiment(64, 256, 1.0f, SPIRAL, 0.95f);
		runExperiment(64, 256, 1.0f, GLASS, 0.95f);
	}
	
	/**
	 * general experiment method
	 * @param fileFolder: name of the data to be experimented on
	 */
	private static void runExperiment(int leafCap, int populationSize, float mutationRate, String dataName, float treshold){
		System.err.println("EXPERIMENT: " + dataName);
		/**
		 * pipe to basic incubator fitness over time file.
		 */
		System.err.println("FITNESS: BI");
		pipeOutput(SRC + results + dataName + "/" + BASIC_INCUBATOR + FITNESS_FILE);
		
		File trainingData = new File(SRC + dataName + "/" + TRAINING_DATA);
		Classifier bclassifier = basicIncubator.generateReasoningEngine(leafCap, populationSize, mutationRate, trainingData, treshold);

		/**
		 * pipe to reroll incubator fitness over time file.
		 */
		System.err.println("FITNESS: RI");
		pipeOutput(SRC + results + dataName + "/" + REROLL_INCUBATOR + FITNESS_FILE);
		Classifier rrclassifier = rerollIncubator.generateReasoningEngine(leafCap, populationSize, mutationRate, trainingData, treshold);
		
		/**
		 * pipe to confusion matrix file for basic
		 */
		System.err.println("CONFUSION: BI");
		pipeOutput(SRC + results + dataName + "/" + BASIC_INCUBATOR + CONFUSION_MATRIX);
		
		File testingData = new File(SRC + dataName + "/" + TESTING_DATA);
		float bscore = evaluator.evaluate(bclassifier, testingData);
		
		/**
		 * pipe to confusion matrix file for reroll
		 */
		System.err.println("CONFUSION: RI");
		pipeOutput(SRC + results + dataName + "/" + REROLL_INCUBATOR + CONFUSION_MATRIX);
		float rscore = evaluator.evaluate(rrclassifier, testingData);
		

		System.err.println("Scoring");
		/**
		 * pipe to score for basic
		 */
		pipeOutput(SRC + results + dataName + "/" + BASIC_INCUBATOR + TEST_SCORE);
		scoreHandler.score(bscore);

		/**
		 * pipe to score for reroll
		 */
		pipeOutput(SRC + results + dataName + "/" + REROLL_INCUBATOR + TEST_SCORE);
		scoreHandler.score(rscore);
	}
	
	private static void pipeOutput(String filePath){
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream(filePath));
			System.setOut(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Builds all output files necessary to log experiment results.
	 * 
	 * NOTE: If output files already exist, they WILL BE DELETED.
	 * TODO change "result" and "roundName" for nomenclature that lines up
	 */	
	private static void buildAllOutputFiles(){
		for (String result : RESULTS){
			buildRoundOutputFiles(result);
		}
	} 
	
	/**
	 * Builds all output files necessary to log the specified experiment round's results.
	 * 
	 * NOTE: If output files already exist, they WILL BE DELETED.
	 */	
	private static void buildRoundOutputFiles(String roundName){
		for (String dataName : DATA_NAMES){
			buildDataSetOutputFiles(roundName, dataName);
		}
	}
	
	/**
	 * Builds all output files necessary to log the specified experiment round for
	 * a specified data type's results.
	 * 
	 * NOTE: If output files already exist, they WILL BE DELETED.
	 */	
	private static void buildDataSetOutputFiles(String roundName, String dataName) {
		for (String incubatorName : INCUBATOR_NAMES){
			buildIncubatorOutputFiles(roundName, dataName, incubatorName);	
		}
	}
	
	/**
	 * Builds all output files necessary to log the specified experiment round for
	 * a specified data type and Incubator's results.
	 * 
	 * NOTE: If output files already exist, they WILL BE DELETED.
	 */	
	private static void buildIncubatorOutputFiles(String roundName, String dataName, 
																String incubatorName) {
		String dirPath = SRC + roundName + dataName + "/" + incubatorName;
		File dir = new File(dirPath);
		if (!dir.exists()){
			dir.mkdirs();
		}
		for (String outputFile : OUTPUT_FILES){
			File file = new File(dirPath + outputFile);
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
