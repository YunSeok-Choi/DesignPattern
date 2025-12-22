package domain;


public enum ChocolateBoilerEnum {
    INSTANCE;  // 싱글톤 인스턴스

    private boolean empty;
    private boolean boiled;

    ChocolateBoilerEnum() {
        empty = true;
        boiled = false;
        System.out.println("ChocolateBoilerEnum 인스턴스 생성");
    }

    public void fill() {
        if (empty) {
            System.out.println("보일러에 우유와 초콜릿 재료 추가");
            empty = false;
            boiled = false;
        }
    }

    public void drain() {
        if (!empty && boiled) {
            System.out.println("끓인 재료를 다음 단계로 넘김");
            empty = true;
        }
    }

    public void boil() {
        if (!empty && !boiled) {
            System.out.println("재료를 끓임");
            boiled = true;
        }
    }

}
