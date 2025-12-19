package pizza.ingredient;

import pizza.ingredient.cheese.Cheese;
import pizza.ingredient.clam.Clams;
import pizza.ingredient.dough.Dough;
import pizza.ingredient.pepperoni.Pepperoni;
import pizza.ingredient.sauce.Sauce;
import pizza.ingredient.veggie.Veggies;

import java.util.List;

// 추상 팩토리 - 제품 생성 메소드를 정의
public interface PizzaIngredientFactory {

    public Dough createDough();     // ← 제품 1

    public Sauce createSauce();     // ← 제품 2

    public Cheese createCheese();   // ← 제품 3

    public List<Veggies> createVeggies();  // ← 제품 4

    public Pepperoni createPepperoni();    // ← 제품 5

    public Clams createClams();            // ← 제품 6
}
