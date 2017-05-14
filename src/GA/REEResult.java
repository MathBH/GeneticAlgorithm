package GA;

/**
 * Reasoning Engine Evaluator Result
 * @author Mathieu
 * 
 * Data pair holding the expected conclusion and the given AI conlusion,
 * both of type C.
 *
 * @param <C> Conclusion type
 */

public class REEResult<C> {
	private C expected;
	private C result;
	
	public REEResult(C result, C expected){
		this.expected = expected;
		this.result = result;
	}
	
	public C getExpected(){
		return this.expected;
	}
	
	public C getResult(){
		return this.result;
	}

}
