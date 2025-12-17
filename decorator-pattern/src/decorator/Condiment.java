package decorator;

public enum Condiment {
    MILK("우유", 1000),
    SOY("두유", 1500),
    WHIP("휘핑크림", 1000),
    MOCHA("모카", 2000);

    private final String koreanName;
    private final int cost;

    Condiment(String koreanName, int cost) {
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
