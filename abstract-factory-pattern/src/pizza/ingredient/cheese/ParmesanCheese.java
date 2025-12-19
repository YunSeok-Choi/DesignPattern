package pizza.ingredient.cheese;

public class ParmesanCheese implements Cheese {

    @Override
    public String toString() {
        return CheeseType.PARMESAN_CHEESE.getDescription();
    }
}
