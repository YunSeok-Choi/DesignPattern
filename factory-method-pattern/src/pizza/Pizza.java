package pizza;

import java.util.ArrayList;
import java.util.List;


// Product (추상 제품)
public abstract class Pizza {

    protected PizzaName name;
    protected String dough;
    protected String sauce;
    protected List<Topping> toppings = new ArrayList<>();

    private static final String explainReady = " 준비 중...";
    private static final String explainMakeDaugh = "반죽 만드는 중...";
    private static final String explainMakeSauce = "소스 추가중...";
    private static final String explainAddTopping = "토핑 추가중...";

    private static final String explainBake = "350도에서 25분간 굽기";
    private static final String explainBox = "피자 가게 박스에 피자 포장하기";

    public void prepare() {
        System.out.println(name + explainReady);
        System.out.println(explainMakeDaugh);
        System.out.println(explainMakeSauce);
        System.out.println(explainAddTopping);
        for (Topping topping : toppings) {
            System.out.println(" " + topping.getDescription());
        }
    }

    public void bake() {
        System.out.println(explainBake);
    }

    public void cut() {
        System.out.println(CutStyle.REGULAR.getDescription());
    }

    public void box() {
        System.out.println(explainBox);
    }

    public String getName() {
        return name.toString();
    }

    public String toString() {
        StringBuffer display = new StringBuffer();
        display.append("---- " + name + " ----\n");
        display.append(dough + "\n");
        display.append(sauce + "\n");
        for (Topping topping : toppings) {
            display.append(topping.getDescription() + "\n");
        }
        return display.toString();
    }

}
