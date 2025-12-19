package pizza_store;

import pizza.Pizza;
import pizza.PizzaName;
import pizza.chicago_style.ChicagoStyleCheesePizza;
import pizza.chicago_style.ChicagoStyleClamPizza;
import pizza.chicago_style.ChicagoStylePepperoniPizza;
import pizza.chicago_style.ChicagoStyleVeggiePizza;

// ConcreteCreator (구체적 생성자)
public class ChicagoPizzaStore extends PizzaStore {

    // 팩토리 메소드 구현
    @Override
    public Pizza createPizza(PizzaName pizzaName) {
        switch (pizzaName) {
            case VEGGIE:
                return new ChicagoStyleVeggiePizza();
            case PEPPERONI:
                return new ChicagoStylePepperoniPizza();
            case CLAM:
                return new ChicagoStyleClamPizza();
            case CHEESE:
            default:
                return new ChicagoStyleCheesePizza();
        }
    }
}
