package pizza.ingredient.veggie;

public enum VeggieType {
    ONION("양파"),
    BELL_PEPPER("피망"),
    MUSHROOM("버섯"),
    OLIVE("올리브"),
    BLACK_OLIVE("블랙 올리브"),
    EGGPLANT("가지"),
    SPINACH("시금치");

    private final String description;

    VeggieType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
