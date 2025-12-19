package pizza.ingredient.clam;

public enum ClamType {
    FRESH_CLAMS("신선한 조개"),
    FROZEN_CLAMS("냉동 조개");

    private final String description;

    ClamType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
