package duck;

import observer.Observable;
import observer.Observer;

public class MallardDuck implements Quackable {
    private final Observable observable;

    public MallardDuck() {
        observable = new Observable(this);
    }

    @Override
    public String quack() {
        notifyObservers();
        return "꽥꽥";
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
        return "청둥오리";
    }
}
