package duck;

import observer.Observable;
import observer.Observer;

public class RubberDuck implements Quackable {
    private final Observable observable;

    public RubberDuck() {
        observable = new Observable(this);
    }

    @Override
    public String quack() {
        notifyObservers();
        return "삑삑";
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
        return "고무오리";
    }
}
