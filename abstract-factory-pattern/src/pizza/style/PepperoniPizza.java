package pizza.style;

import pizza.Pizza;
import pizza.ingredient.PizzaIngredientFactory;

public class PepperoniPizza extends Pizza {

    private final PizzaIngredientFactory pizzaIngredientFactory;

    public PepperoniPizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.name = PizzaType.PEPPERONI;
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    // 해당 메소드를 호출해야 실질적으로 피자 생성
    @Override
    public void prepare() {
        System.out.println(name + "준비 중...");
        this.dough = pizzaIngredientFactory.createDough();
        this.sauce = pizzaIngredientFactory.createSauce();
        this.cheese = pizzaIngredientFactory.createCheese();
        this.veggies = pizzaIngredientFactory.createVeggies();
        this.pepperoni = pizzaIngredientFactory.createPepperoni();
    }
}

