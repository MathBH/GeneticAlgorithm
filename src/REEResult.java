
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
