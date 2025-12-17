package coffee;

import berverage.Beverage;

public class Decaf extends Beverage {

    public Decaf() {
        this.description = Coffee.DECAF.getKoreanName();
    }

    @Override
    public int cost() {
        return Coffee.DECAF.getCost();
    }
}
