package pizza.ingredient.cheese;

public class ReggianoCheese implements Cheese {

    @Override
    public String toString() {
        return CheeseType.REGGIANO_CHEESE.getDescription();
    }
}
