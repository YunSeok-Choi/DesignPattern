package pizza;

public enum Topping {
    // 치즈류
    REGGIANO_CHEESE("잘게 썬 레지아노 치즈"),
    MOZZARELLA_CHEESE("모짜렐라 치즈"),
    SHREDDED_MOZZARELLA_CHEESE("잘게 조각낸 모짜렐라 치즈"),
    PARMESAN_CHEESE("파마산 치즈"),

    // 해산물
    FRESH_CLAMS("신선한 조개"),
    FROZEN_CLAMS("냉동 조개"),

    // 고기
    PEPPERONI("페퍼로니"),

    // 야채
    ONION("양파"),
    BELL_PEPPER("피망"),
    MUSHROOM("버섯"),
    OLIVE("올리브"),
    BLACK_OLIVE("블랙 올리브"),
    EGGPLANT("가지"),
    SPINACH("시금치");

    private final String description;

    Topping(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

