package pizza.ingredient.veggie;

public class Mushroom implements Veggies {

    @Override
    public String toString() {
        return VeggieType.MUSHROOM.getDescription();
    }
}
