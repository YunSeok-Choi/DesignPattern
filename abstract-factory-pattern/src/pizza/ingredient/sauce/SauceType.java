package pizza.ingredient.sauce;

public enum SauceType {
    MARINARA("마리나라 소스"),
    PLUM_TOMATO("플럼 토마토 소스");

    private final String description;

    SauceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
