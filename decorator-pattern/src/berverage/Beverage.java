package berverage;

public abstract class Beverage {

    protected String description = "음료";

    public String getDescription() {
        return description;
    }

    public abstract int cost();
}
