package decorator;

import berverage.Beverage;

public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", " + Condiment.MOCHA.getKoreanName();
    }

    @Override
    public int cost() {
        return beverage.cost() + Condiment.MOCHA.getCost();
    }
}
