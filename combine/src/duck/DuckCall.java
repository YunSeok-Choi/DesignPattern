package duck;

import observer.Observable;
import observer.Observer;

public class DuckCall implements Quackable {
    private final Observable observable;

    public DuckCall() {
        observable = new Observable(this);
    }

    @Override
    public String quack() {
        notifyObservers();
        return "꽉";
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public String toString() {
        return "오리호출기";
    }
}
