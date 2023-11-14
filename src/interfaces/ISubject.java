package interfaces;

import java.util.List;

public interface ISubject {
    public void registerObserver(List<? extends IObserver> observers);

    public void notifyObservers();
}
