package pizza.ny_style;

import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class NYStyleClamPizza extends Pizza {

    public NYStyleClamPizza() {
        this.name = PizzaName.CLAM;
        this.dough = Dough.THIN_CRUST.getDescription();
        this.sauce = Sauce.MARINARA.getDescription();

        this.toppings.add(Topping.FRESH_CLAMS);
        this.toppings.add(Topping.PARMESAN_CHEESE);
    }
}
