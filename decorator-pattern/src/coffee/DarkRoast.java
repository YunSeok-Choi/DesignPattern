package coffee;

import berverage.Beverage;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        this.description = Coffee.DARK_ROAST.getKoreanName();
    }

    @Override
    public int cost() {
        return Coffee.DARK_ROAST.getCost();
    }
}
