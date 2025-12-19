package pizza.ingredient.veggie;

public class RedPepper implements Veggies {

    @Override
    public String toString() {
        return VeggieType.BELL_PEPPER.getDescription();
    }
}
