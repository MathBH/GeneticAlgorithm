package ExceptionHandler;

import java.util.Observable;

public abstract class ExceptionHandler extends Observable{
	public abstract void alert(Exception e);
}
