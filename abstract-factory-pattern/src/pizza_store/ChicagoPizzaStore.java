package pizza_store;

import pizza.Pizza;
import pizza.ingredient.ChicagoPizzaIngredientFactory;
import pizza.ingredient.PizzaIngredientFactory;
import pizza.style.*;

// Concrete Creator (구체적 생성자)
public class ChicagoPizzaStore extends PizzaStore {

    // 팩토리 메소드 구현
    @Override
    public Pizza createPizza(PizzaType pizzaType) {

        // 시카고 피자 팩토리에서 피자 생성
        PizzaIngredientFactory pizzaIngredientFactory = new ChicagoPizzaIngredientFactory();

        return switch (pizzaType) {
            case VEGGIE -> new VeggiePizza(pizzaIngredientFactory);
            case PEPPERONI -> new PepperoniPizza(pizzaIngredientFactory);
            case CLAM -> new ClamPizza(pizzaIngredientFactory);
            default -> new CheesePizza(pizzaIngredientFactory);
        };
    }
}
