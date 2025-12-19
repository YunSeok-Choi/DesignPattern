package pizza_store;

import pizza.Pizza;
import pizza.PizzaName;
import pizza.ny_style.NYStyleCheesePizza;
import pizza.ny_style.NYStyleClamPizza;
import pizza.ny_style.NYStylePepperoniPizza;
import pizza.ny_style.NYStyleVeggiePizza;

// Concrete Creator (구체적 생성자)
public class NYPizzaStore extends PizzaStore {

    // 팩토리 메소드 구현
    @Override
    public Pizza createPizza(PizzaName pizzaName) {
        // 피지 하나를 직접 생성, 구체 클래스를 직접 new
        switch (pizzaName) {
            case PEPPERONI:
                return new NYStylePepperoniPizza();
            case CLAM:
                return new NYStyleClamPizza();
            case VEGGIE:
                return new NYStyleVeggiePizza();
            case CHEESE:
            default:
                return new NYStyleCheesePizza();
        }
    }
}
