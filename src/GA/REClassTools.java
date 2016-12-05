package GA;
import java.util.List;

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
