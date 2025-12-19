package pizza.ny_style;

import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class NYStyleVeggiePizza extends Pizza {

    public NYStyleVeggiePizza() {
        this.name = PizzaName.VEGGIE;
        this.dough = Dough.THIN_CRUST.getDescription();
        this.sauce = Sauce.MARINARA.getDescription();

        this.toppings.add(Topping.ONION);
        this.toppings.add(Topping.BELL_PEPPER);
        this.toppings.add(Topping.MUSHROOM);
        this.toppings.add(Topping.OLIVE);
    }
}
