package GA;
import java.util.ArrayList;
import java.io.File;

/**
 * Reasoning Engine Incubator
 * @author Mathieu
 *
 * Interface for a Reasoning Engine Incubator
 *
 * @param <P>
 * @param <C>
 */

public interface REIncubator<P,C> {

	public ReasoningEngine<P,C> generateReasoningEngine(File learningData, float treshold);
}
