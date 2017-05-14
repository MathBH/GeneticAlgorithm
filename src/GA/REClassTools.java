package GA;
import java.util.List;

/**
 * Reasoning Engine Class Tools
 * @author Mathieu
 *
 * Static helper class for dealing with Reasoning Engines.
 * 
 * TODO: kinda looks like a code smell
 */

public class REClassTools {
	/**
	 * returns integer representing class
	 * WARNING: presented as integers starting from 1 and up not 0 and up
	 * coz most learning data is written this way
	 * 
	 * @param classificationSet
	 * @return
	 */
	public static int toInt(List<Boolean> classificationSet){
		return classificationSet.indexOf(true) +1;
	}

}
