package pizza.ingredient.dough;

public class ThinCrustDough implements Dough {

    @Override
    public String toString() {
        return DoughType.THIN_CRUST.getDescription();
    }
}
