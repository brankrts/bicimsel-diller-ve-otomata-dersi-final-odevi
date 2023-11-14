package observer;

import java.util.ArrayList;
import java.util.List;

import interfaces.ISubject;
import interfaces.IObserver;

public class ObserverPool implements ISubject {
    private List<IObserver> observers;
    private String input = "";

    public ObserverPool() {
        this.observers = new ArrayList<IObserver>();
    }

    public void notifyObservers() {
        for (IObserver observer : this.observers) {
            observer.update(input);
        }
    }

    public void setValue(String input) {
        this.input = input;
        this.notifyObservers();
    }

    @Override
    public void registerObserver(List<? extends IObserver> observers) {
        observers.forEach(t -> {
            this.observers.add(t);
        });

    }

}