package pizza;

import pizza.ingredient.cheese.Cheese;
import pizza.ingredient.clam.Clams;
import pizza.ingredient.dough.Dough;
import pizza.ingredient.pepperoni.Pepperoni;
import pizza.ingredient.sauce.Sauce;
import pizza.ingredient.veggie.Veggies;
import pizza.style.PizzaType;

import java.util.ArrayList;
import java.util.List;


public abstract class Pizza {

    protected PizzaType name;
    protected Dough dough;
    protected Sauce sauce;
    protected List<Veggies> veggies = new ArrayList<>();
    protected Cheese cheese;
    protected Pepperoni pepperoni;
    protected Clams clam;

    private static final String explainBake = "350도에서 25분간 굽기";
    private static final String explainBox = "피자 가게 박스에 피자 포장하기";

    // 해당 메소드를 호출해야 실질적으로 피자 생성
    abstract public void prepare();

    public void bake() {
        System.out.println(explainBake);
    }

    public void cut() {
        System.out.println(CutType.REGULAR.getDescription());
    }

    public void box() {
        System.out.println(explainBox);
    }

    public String toString() {
        StringBuffer display = new StringBuffer();
        if (dough != null) {
            display.append(dough);
            display.append("\n");
        }
        if (sauce != null) {
            display.append(sauce);
            display.append("\n");
        }
        if (cheese != null) {
            display.append(cheese);
            display.append("\n");
        }
        if (veggies != null) {
            for (Veggies veggie : veggies) {
                display.append(veggie);
                if (veggie != veggies.get(veggies.size() - 1)) {
                    display.append(", ");
                }
            }
            display.append("\n");
        }
        if (clam != null) {
            display.append(clam);
            display.append("\n");
        }
        if (pepperoni != null) {
            display.append(pepperoni);
            display.append("\n");
        }
        return display.toString();
    }

}
