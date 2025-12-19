package pizza.ingredient.pepperoni;

public enum PepperoniType {
    SLICED_PEPPERONI("슬라이스 페퍼로니");

    private final String description;

    PepperoniType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
