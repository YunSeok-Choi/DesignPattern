package pizza_store;

import pizza.Pizza;
import pizza.style.PizzaType;

// Creator (추상 생성자)
public abstract class PizzaStore {

    // 팩토리 메소드
    public abstract Pizza createPizza(PizzaType pizzaType);

    public Pizza orderPizza(PizzaType pizzaType) {
        Pizza pizza = createPizza(pizzaType);

        // 해당 메소드를 호출해야 특정 지역 팩토리의 피자 생성
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

}
