package pizza;

public enum CutType {
    REGULAR("알맞은 크기의 피자 조각으로 자르기"),
    SQUARE("네모난 모양으로 피자 자르기");

    private final String description;

    CutType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
