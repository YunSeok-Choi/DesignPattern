package pizza.ny_style;

import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        this.name = PizzaName.CHEESE;
        this.dough = Dough.THIN_CRUST.getDescription();
        this.sauce = Sauce.MARINARA.getDescription();

        this.toppings.add(Topping.REGGIANO_CHEESE);
    }
}
