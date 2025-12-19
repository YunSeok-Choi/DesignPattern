import pizza.Pizza;
import pizza.PizzaName;
import pizza_store.ChicagoPizzaStore;
import pizza_store.NYPizzaStore;
import pizza_store.PizzaStore;

public class Main {
    public static void main(String[] args) {

        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        Pizza nyCheesePizza = nyPizzaStore.orderPizza(PizzaName.CHEESE);
        System.out.println(nyCheesePizza);

        System.out.println();

        Pizza chicagoCheesePizza = chicagoPizzaStore.orderPizza(PizzaName.CHEESE);
        System.out.println(chicagoCheesePizza);

    }
}
