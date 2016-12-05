import java.lang.reflect.Method;
import MObserve.*;

public interface REEObserver<T> extends Observer<REEResult<T>>{

	public void notify(REEResult<T> r);
}
