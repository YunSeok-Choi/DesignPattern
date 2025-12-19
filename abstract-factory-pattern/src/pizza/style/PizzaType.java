package pizza.style;

public enum PizzaType {
    CHEESE("치즈 피자"),
    PEPPERONI("페퍼로니 피자"),
    CLAM("조개 피자"),
    VEGGIE("야채 피자");

    private final String koreanName;

    PizzaType(String koreanName) {
        this.koreanName = koreanName;
    }

    @Override
    public String toString() {
        return koreanName;
    }
}
