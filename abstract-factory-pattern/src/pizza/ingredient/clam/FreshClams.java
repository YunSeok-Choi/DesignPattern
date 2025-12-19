package pizza.ingredient.clam;

public class FreshClams implements Clams {

    @Override
    public String toString() {
        return ClamType.FRESH_CLAMS.getDescription();
    }
}
