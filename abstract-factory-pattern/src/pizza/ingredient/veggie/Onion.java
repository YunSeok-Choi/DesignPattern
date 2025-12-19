package pizza.ingredient.veggie;

public class Onion implements Veggies {

    @Override
    public String toString() {
        return VeggieType.ONION.getDescription();
    }
}
