package adapter;

import duck.Quackable;
import observer.Observable;
import observer.Observer;

public class GooseAdapter implements Quackable {
    private final Goose goose;
    private final Observable observable;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
        observable = new Observable(this);
    }

    @Override
    public String quack() {
        notifyObservers();
        return goose.honk();
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
        return "오리인 척 하는 거위";
    }
}
