package pizza.ingredient.sauce;

public class MarinaraSauce implements Sauce {

    @Override
    public String toString() {
        return SauceType.MARINARA.getDescription();
    }
}
