import berverage.Beverage;
import coffee.Espresso;
import coffee.HouseBlend;
import decorator.*;
import coffee.DarkRoast;

public class Main {
    public static void main(String[] args) {

        Beverage beverage = new DarkRoast();
        beverage = new Mocha(beverage); // 데코레이터 추가
        beverage = new Milk(beverage); // 데코레이터 추가2
        System.out.println(beverage.getDescription() + " 총 " + beverage.cost() + "원");
        // 다크 로스트, 모카, 우유 총 4000원

        Beverage beverage2 = new Espresso(); // 데코레이터 없이
        System.out.println(beverage2.getDescription() + " 총 " + beverage2.cost() + "원");
        // 에스프레소 총 2000원

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3); // 데코레이터 추가
        beverage3 = new Soy(beverage3); // 데코레이터 추가2 (중복 가능)
        beverage3 = new Mocha(beverage3); // 데코레이터 추가3
        beverage3 = new Whip(beverage3); // 데코레이터 추가4
        System.out.println(beverage3.getDescription() + " 총 " + beverage3.cost() + "원");
        // 하우스 블렌드, 두유, 두유, 모카, 휘핑크림 총 7000원

    }
}
