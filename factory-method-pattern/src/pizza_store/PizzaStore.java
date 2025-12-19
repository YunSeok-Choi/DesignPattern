package pizza_store;

import pizza.Pizza;
import pizza.PizzaName;

// Creator (추상 생성자)
public abstract class PizzaStore {

    // 팩토리 메서드
    public abstract Pizza createPizza(PizzaName pizzaName);

    // 템플릿 메소드
    public Pizza orderPizza(PizzaName pizzaName) {
        Pizza pizza = createPizza(pizzaName);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

}
