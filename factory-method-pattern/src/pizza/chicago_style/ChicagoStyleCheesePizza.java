package pizza.chicago_style;

import pizza.CutStyle;
import pizza.Dough;
import pizza.Pizza;
import pizza.PizzaName;
import pizza.Sauce;
import pizza.Topping;

public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        this.name = PizzaName.CHEESE;
        this.dough = Dough.THICK_CRUST.getDescription();
        this.sauce = Sauce.PLUM_TOMATO.getDescription();

        this.toppings.add(Topping.SHREDDED_MOZZARELLA_CHEESE);
    }

    @Override
    public void cut() {
        System.out.println(CutStyle.SQUARE.getDescription());
    }
}
