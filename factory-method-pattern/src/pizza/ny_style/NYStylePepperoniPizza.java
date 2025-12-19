package pizza.ny_style;

import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class NYStylePepperoniPizza extends Pizza {

    public NYStylePepperoniPizza() {
        this.name = PizzaName.PEPPERONI;
        this.dough = Dough.THIN_CRUST.getDescription();
        this.sauce = Sauce.MARINARA.getDescription();

        this.toppings.add(Topping.PEPPERONI);
        this.toppings.add(Topping.MOZZARELLA_CHEESE);
    }
}
