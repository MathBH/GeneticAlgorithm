package GA;

/**
 * Number Value Exception
 * @author Mathieu
 * 
 * Exception indicated a given number value is not appropriate.
 *
 */

public class NumberValueException extends Exception{
	public NumberValueException(){
	}
	
	public NumberValueException(String message){
		super(message);
	}

}
