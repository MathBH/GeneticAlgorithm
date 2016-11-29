package observers;

import java.util.Observable;
import java.util.Observer;

public class TextView implements Observer{

	public void update(Observable arg0, Object arg1) {
		System.out.println(arg0+""+arg1);
	}
}
