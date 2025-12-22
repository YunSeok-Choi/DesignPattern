import domain.ChocolateBoiler;
import domain.ChocolateBoilerEnum;

public class Main {
    public static void main(String[] args) {

        ChocolateBoiler boiler = ChocolateBoiler.getInstance(); // 인스턴스 생성
        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();

        ChocolateBoilerEnum boilerEnum = ChocolateBoilerEnum.INSTANCE;
        ChocolateBoilerEnum boilerEnum2 = ChocolateBoilerEnum.INSTANCE;

        boiler.fill();  // 보일러에 우유와 초콜릿 재료 추가
        boiler2.boil(); // 재료를 끓임
        boiler.drain(); // 끓인 재료를 다음 단계로 넘김

        System.out.println("boiler == boiler2: " + (boiler == boiler2));      // true
        System.out.println("boilerEnum == boilerEnum2: " + (boilerEnum == boilerEnum2));
        System.out.println();

        // enum 싱글톤
        boilerEnum.fill();
        boilerEnum2.boil();
        boilerEnum.drain();

        System.out.println("동일한 인스턴스 확인: " + (System.identityHashCode(boiler) == System.identityHashCode(boiler2))); // true
        System.out.println("동일한 인스턴스 확인: " + (System.identityHashCode(boilerEnum) == System.identityHashCode(boilerEnum2))); // true
    }
}
