package pizza.ingredient;

import pizza.ingredient.cheese.Cheese;
import pizza.ingredient.cheese.ReggianoCheese;
import pizza.ingredient.clam.Clams;
import pizza.ingredient.clam.FreshClams;
import pizza.ingredient.dough.Dough;
import pizza.ingredient.dough.ThinCrustDough;
import pizza.ingredient.pepperoni.Pepperoni;
import pizza.ingredient.pepperoni.SlicedPepperoni;
import pizza.ingredient.sauce.MarinaraSauce;
import pizza.ingredient.sauce.Sauce;
import pizza.ingredient.veggie.*;

import java.util.List;

// Concrete Factory - NY 스타일 재료 조합
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public List<Veggies> createVeggies() {
        return List.of(new Onion(), new Mushroom(), new Garlic(), new RedPepper());
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
