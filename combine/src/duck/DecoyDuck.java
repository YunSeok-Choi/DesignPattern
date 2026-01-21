package duck;

import observer.Observable;
import observer.Observer;

public class DecoyDuck implements Quackable {
    private final Observable observable;

    public DecoyDuck() {
        observable = new Observable(this);
    }

    @Override
    public String quack() {
        notifyObservers();
        return "<< 조용 >>";
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
        return "가짜오리";
    }
}
