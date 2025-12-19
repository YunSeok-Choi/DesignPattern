package pizza.ingredient.cheese;

public enum CheeseType {
    REGGIANO_CHEESE("레지아노 치즈"),
    MOZZARELLA_CHEESE("모짜렐라 치즈"),
    PARMESAN_CHEESE("파마산 치즈");

    private final String description;

    CheeseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
