package MObserve;
import java.util.ArrayList;

public abstract class Observable<D> {
	ArrayList<Observer> observers;
	
	public Observable(){
		observers = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer o){
		observers.add(o);
	}
	
	public void notifyObservers(D data){
		for (Observer o: observers){
			o.notify(data);
		}
	}
}
