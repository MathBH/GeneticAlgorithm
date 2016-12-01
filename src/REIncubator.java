import java.util.ArrayList;
import java.io.File;

public interface REIncubator<P,C> {

	public ReasoningEngine<P,C> generateReasoningEngine(int populationSize, File learningData);
}
