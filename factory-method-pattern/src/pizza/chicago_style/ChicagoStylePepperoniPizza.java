package pizza.chicago_style;

import pizza.CutStyle;
import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class ChicagoStylePepperoniPizza extends Pizza {

    public ChicagoStylePepperoniPizza() {
        this.name = PizzaName.PEPPERONI;
        this.dough = Dough.THICK_CRUST.getDescription();
        this.sauce = Sauce.PLUM_TOMATO.getDescription();

        this.toppings.add(Topping.PEPPERONI);
        this.toppings.add(Topping.MOZZARELLA_CHEESE);
    }

    @Override
    public String cut() {
        return CutStyle.SQUARE.getDescription();
    }
}
