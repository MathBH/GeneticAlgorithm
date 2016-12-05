package GA;
import java.util.ArrayList;
import java.io.File;

public interface REIncubator<P,C> {

	public ReasoningEngine<P,C> generateReasoningEngine(File learningData, float treshold);
}
