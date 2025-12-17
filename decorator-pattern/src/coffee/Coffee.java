package coffee;

public enum Coffee {
    ESPRESSO("에스프레소", 2000),
    DARK_ROAST("다크 로스트", 1000),
    DECAF("디카페인", 1500),
    HOUSE_BLEND("하우스 블렌드", 1000);

    private final String koreanName;
    private final int cost;

    Coffee(String koreanName, int cost) {
        this.koreanName = koreanName;
        this.cost = cost;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public int getCost() {
        return cost;
    }
}
