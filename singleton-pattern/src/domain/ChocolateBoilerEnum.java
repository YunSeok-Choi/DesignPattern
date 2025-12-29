package domain;


public enum ChocolateBoilerEnum {
    INSTANCE;  // 싱글톤 인스턴스

    private boolean empty;
    private boolean boiled;

    ChocolateBoilerEnum() {
        empty = true;
        boiled = false;
    }

    public String fill() {
        if (empty) {
            empty = false;
            boiled = false;
            return "보일러에 우유와 초콜릿 재료 추가";
        }
        return "보일러가 이미 가득 차 있습니다";
    }

    public String drain() {
        if (!empty && boiled) {
            empty = true;
            return "끓인 재료를 다음 단계로 넘김";
        }
        return "보일러가 비어있거나 재료가 끓지 않았습니다";
    }

    public String boil() {
        if (!empty && !boiled) {
            boiled = true;
            return "재료를 끓임";
        }
        return "보일러가 비어있거나 이미 끓었습니다";
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }

}
