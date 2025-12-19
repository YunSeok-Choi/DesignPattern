package pizza_store;

import pizza.Pizza;
import pizza.ingredient.NYPizzaIngredientFactory;
import pizza.ingredient.PizzaIngredientFactory;
import pizza.style.*;

// Concrete Creator (구체적 생성자)
public class NYPizzaStore extends PizzaStore {

    // 팩토리 메소드 구현
    @Override
    public Pizza createPizza(PizzaType pizzaType) {

        // 뉴욕 피자 팩토리에서 피자 생성
        PizzaIngredientFactory pizzaIngredientFactory = new NYPizzaIngredientFactory();

        return switch (pizzaType) {
            case PEPPERONI -> new PepperoniPizza(pizzaIngredientFactory);
            case CLAM -> new ClamPizza(pizzaIngredientFactory);
            case VEGGIE -> new VeggiePizza(pizzaIngredientFactory);
            default -> new CheesePizza(pizzaIngredientFactory);
        };
    }
}
