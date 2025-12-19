package pizza.ingredient.veggie;

public class Eggplant implements Veggies {

    @Override
    public String toString() {
        return VeggieType.EGGPLANT.getDescription();
    }
}
