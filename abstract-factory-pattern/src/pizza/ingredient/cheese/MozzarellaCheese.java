package pizza.ingredient.cheese;

public class MozzarellaCheese implements Cheese {

    @Override
    public String toString() {
        return CheeseType.MOZZARELLA_CHEESE.getDescription();
    }
}
