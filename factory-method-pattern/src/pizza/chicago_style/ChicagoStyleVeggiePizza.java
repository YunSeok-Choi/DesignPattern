package pizza.chicago_style;

import pizza.CutStyle;
import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class ChicagoStyleVeggiePizza extends Pizza {

    public ChicagoStyleVeggiePizza() {
        this.name = PizzaName.VEGGIE;
        this.dough = Dough.THICK_CRUST.getDescription();
        this.sauce = Sauce.PLUM_TOMATO.getDescription();

        this.toppings.add(Topping.EGGPLANT);
        this.toppings.add(Topping.SPINACH);
        this.toppings.add(Topping.BLACK_OLIVE);
        this.toppings.add(Topping.MUSHROOM);
    }

    @Override
    public String cut() {
        return CutStyle.SQUARE.getDescription();
    }
}
