import java.util.ArrayList;

public class ClassifierTD extends TrainingData<ArrayList<Float>,ArrayList<Boolean>>{

	public ClassifierTD(String filePath) {
		super(filePath);
	}

	@Override
	public FileParser<TrainingExample<ArrayList<Float>,ArrayList<Boolean>>> getParser() {
		return new ClassifierTDParser(this);
	}

}
