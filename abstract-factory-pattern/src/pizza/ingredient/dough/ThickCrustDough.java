package pizza.ingredient.dough;

public class ThickCrustDough implements Dough {

    @Override
    public String toString() {
        return DoughType.THICK_CRUST.getDescription();
    }
}
