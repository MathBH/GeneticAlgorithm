import java.io.File;

public abstract class TrainingDataParser<P,C> extends FileParser<TrainingExample<P,C>>{

	public TrainingDataParser(File sourceFile) {
		super(sourceFile);
	}
}
