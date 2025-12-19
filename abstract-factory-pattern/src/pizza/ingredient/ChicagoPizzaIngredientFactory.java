package pizza.ingredient;

import pizza.ingredient.cheese.Cheese;
import pizza.ingredient.cheese.MozzarellaCheese;
import pizza.ingredient.clam.Clams;
import pizza.ingredient.clam.FrozenClams;
import pizza.ingredient.dough.Dough;
import pizza.ingredient.dough.ThickCrustDough;
import pizza.ingredient.pepperoni.Pepperoni;
import pizza.ingredient.pepperoni.SlicedPepperoni;
import pizza.ingredient.sauce.PlumTomatoSauce;
import pizza.ingredient.sauce.Sauce;
import pizza.ingredient.veggie.*;

import java.util.List;

// Concrete Factory - Chicago 스타일 재료 조합
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory{
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public List<Veggies> createVeggies() {
        return List.of(new BlackOlives(), new Eggplant(), new Spinach());
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FrozenClams();
    }
}
