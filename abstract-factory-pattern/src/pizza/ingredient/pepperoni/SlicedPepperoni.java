package pizza.ingredient.pepperoni;

public class SlicedPepperoni implements Pepperoni {

    @Override
    public String toString() {
        return PepperoniType.SLICED_PEPPERONI.getDescription();
    }
}
