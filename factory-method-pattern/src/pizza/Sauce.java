package pizza;

public enum Sauce {
    MARINARA("마리나라 소스"),
    PLUM_TOMATO("플럼토마토 소스");

    private final String description;

    Sauce(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
