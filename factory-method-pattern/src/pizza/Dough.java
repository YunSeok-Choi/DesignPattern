package pizza;

public enum Dough {
    THIN_CRUST("씬 크러스트 도우"),
    THICK_CRUST("아주 두꺼운 크러스트 도우");

    private final String description;

    Dough(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
