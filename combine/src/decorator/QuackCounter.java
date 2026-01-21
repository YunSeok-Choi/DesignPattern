package decorator;

import duck.Quackable;
import observer.Observer;

public class QuackCounter implements Quackable {
    private final Quackable duck;
    private static int numberOfQuacks;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public String quack() {
        numberOfQuacks++;
        return duck.quack();
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }

    public static void resetQuacks() {
        numberOfQuacks = 0;
    }

    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        duck.notifyObservers();
    }

    @Override
    public String toString() {
        return duck.toString();
    }
}
