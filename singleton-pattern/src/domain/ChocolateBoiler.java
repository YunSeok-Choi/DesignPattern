package domain;

public class ChocolateBoiler {

    private boolean empty;
    private boolean boiled;

    private volatile static ChocolateBoiler chocolateBoiler;

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    // 싱글톤 인스턴스 생성
    public static ChocolateBoiler getInstance() {
        if (chocolateBoiler == null) {
            synchronized (ChocolateBoiler.class) {
                if (chocolateBoiler == null) {
                    System.out.println("인스턴스 생성");
                    chocolateBoiler = new ChocolateBoiler();
                }
            }
        }
        return chocolateBoiler;
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
