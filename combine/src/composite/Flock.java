package composite;

import duck.Quackable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Flock implements Quackable {
    private final List<Quackable> ducks = new ArrayList<>();

    public void add(Quackable duck) {
        ducks.add(duck);
    }

    @Override
    public String quack() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ducks.size(); i++) {
            if (i > 0) {
                result.append("\n");
            }
            result.append(ducks.get(i).quack());
        }
        return result.toString();
    }

    @Override
    public void registerObserver(Observer observer) {
        for (Quackable duck : ducks) {
            duck.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
    }

    @Override
    public String toString() {
        return "오리 무리";
    }
}
