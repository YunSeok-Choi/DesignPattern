package coffee;

import berverage.Beverage;

public class Espresso extends Beverage {

    public Espresso() {
        this.description = Coffee.ESPRESSO.getKoreanName();
    }

    @Override
    public int cost() {
        return Coffee.ESPRESSO.getCost();
    }
}
