package pizza.ingredient.clam;

public class FrozenClams implements Clams {

    @Override
    public String toString() {
        return ClamType.FROZEN_CLAMS.getDescription();
    }
}
