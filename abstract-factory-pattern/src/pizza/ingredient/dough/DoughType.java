package pizza.ingredient.dough;

public enum DoughType {
    THIN_CRUST("씬 크러스트 도우"),
    THICK_CRUST("아주 두꺼운 크러스트 도우");

    private final String description;

    DoughType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
