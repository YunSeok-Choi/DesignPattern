package decorator;

import berverage.Beverage;

public class Whip extends CondimentDecorator {

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", " + Condiment.WHIP.getKoreanName();
    }

    @Override
    public int cost() {
        return beverage.cost() + Condiment.WHIP.getCost();
    }
}
