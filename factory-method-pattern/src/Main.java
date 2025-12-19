import pizza.Pizza;
import pizza.PizzaName;
import pizza_store.ChicagoPizzaStore;
import pizza_store.NYPizzaStore;
import pizza_store.PizzaStore;

public class Main {
    public static void main(String[] args) {
        /*
        구조:
        - 제품: Pizza 1개
        - 생성 방식: 서브클래스가 어떤 구체 Pizza를 만들지 결정
        - 목적: Pizza 객체 생성을 서브클래스에 위임
        */
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        Pizza nyCheesePizza = nyPizzaStore.orderPizza(PizzaName.CHEESE);
        System.out.println(nyCheesePizza);

        System.out.println();

        Pizza chicagoCheesePizza = chicagoPizzaStore.orderPizza(PizzaName.CHEESE);
        System.out.println(chicagoCheesePizza);

    }
}
