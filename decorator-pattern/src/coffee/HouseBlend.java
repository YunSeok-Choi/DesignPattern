package coffee;

import berverage.Beverage;

public class HouseBlend extends Beverage {

    public HouseBlend() {
        this.description = Coffee.HOUSE_BLEND.getKoreanName();
    }

    @Override
    public int cost() {
        return Coffee.HOUSE_BLEND.getCost();
    }
}
