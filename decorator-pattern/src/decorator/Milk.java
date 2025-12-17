package decorator;

import berverage.Beverage;

public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", " + Condiment.MILK.getKoreanName();
    }

    @Override
    public int cost() {
        return beverage.cost() + Condiment.MILK.getCost();
    }
}
