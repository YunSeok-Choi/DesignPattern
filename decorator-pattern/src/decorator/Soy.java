package decorator;

import berverage.Beverage;

public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", " + Condiment.SOY.getKoreanName();
    }

    @Override
    public int cost() {
        return beverage.cost() + Condiment.SOY.getCost();
    }
}
