import java.io.File;

public abstract class TrainingData<P,C> extends File{

	public TrainingData(String filePath) {
		super(filePath);
	}
	
	public abstract FileParser<TrainingExample<P,C>> getParser();

}
