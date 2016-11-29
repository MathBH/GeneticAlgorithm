package ExceptionHandler;

import java.util.Observer;

public class BasicExceptionHandler extends ExceptionHandler{

	public BasicExceptionHandler(/*Observer view*/){
		//this.addObserver(view);
	}
	
	@Override
	public void alert(Exception e) {
		e.printStackTrace();
		//this.setChanged();
		//this.notifyObservers(e);
	}
	
}
