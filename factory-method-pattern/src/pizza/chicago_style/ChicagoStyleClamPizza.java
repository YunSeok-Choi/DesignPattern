package pizza.chicago_style;

import pizza.CutStyle;
import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class ChicagoStyleClamPizza extends Pizza {

    public ChicagoStyleClamPizza() {
        this.name = PizzaName.CLAM;
        this.dough = Dough.THICK_CRUST.getDescription();
        this.sauce = Sauce.PLUM_TOMATO.getDescription();

        this.toppings.add(Topping.FROZEN_CLAMS);
        this.toppings.add(Topping.PARMESAN_CHEESE);
    }

    @Override
    public String cut() {
        return CutStyle.SQUARE.getDescription();
    }
}
