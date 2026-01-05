package test;

import berverage.Beverage;
import coffee.DarkRoast;
import coffee.Espresso;
import coffee.HouseBlend;
import decorator.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DecoratorTest {

    @Test
    void testDarkRoastWithMochaAndMilk() {
        // Given - DarkRoast 커피 생성
        Beverage beverage = new DarkRoast();

        // When - Mocha와 Milk 데코레이터 추가
        beverage = new Mocha(beverage);
        beverage = new Milk(beverage);

        // Then - 설명과 가격이 올바르게 계산되어야 함
        assertThat(beverage.getDescription()).isEqualTo("다크 로스트, 모카, 우유");
        assertThat(beverage.cost()).isEqualTo(4000); // 1000 + 2000 + 1000
    }

    @Test
    void testEspressoWithoutDecorator() {
        // Given - Espresso 커피 생성
        Beverage beverage = new Espresso();

        // When - 데코레이터 없이 사용

        // Then - 기본 설명과 가격이 반환되어야 함
        assertThat(beverage.getDescription()).isEqualTo("에스프레소");
        assertThat(beverage.cost()).isEqualTo(2000);
    }

    @Test
    void testHouseBlendWithMultipleDecorators() {
        // Given - HouseBlend 커피 생성
        Beverage beverage = new HouseBlend();

        // When - 여러 데코레이터 추가 (중복 가능)
        beverage = new Soy(beverage);
        beverage = new Soy(beverage);
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);

        // Then - 모든 데코레이터가 올바르게 적용되어야 함
        assertThat(beverage.getDescription()).isEqualTo("하우스 블렌드, 두유, 두유, 모카, 휘핑크림");
        assertThat(beverage.cost()).isEqualTo(7000); // 1000 + 1500 + 1500 + 2000 + 1000
    }

    @Test
    void testDecoratorCanBeDuplicated() {
        // Given - Espresso 커피 생성
        Beverage beverage = new Espresso();

        // When - 같은 데코레이터를 여러 번 추가
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);
        beverage = new Mocha(beverage);

        // Then - 중복된 데코레이터가 모두 적용되어야 함
        assertThat(beverage.getDescription()).isEqualTo("에스프레소, 모카, 모카, 모카");
        assertThat(beverage.cost()).isEqualTo(8000); // 2000 + 2000 + 2000 + 2000
    }

    @Test
    void testSingleDecorator() {
        // Given - DarkRoast 커피 생성
        Beverage beverage = new DarkRoast();

        // When - 단일 데코레이터만 추가
        beverage = new Whip(beverage);

        // Then - 데코레이터가 올바르게 적용되어야 함
        assertThat(beverage.getDescription()).isEqualTo("다크 로스트, 휘핑크림");
        assertThat(beverage.cost()).isEqualTo(2000); // 1000 + 1000
    }

    @Test
    void testDifferentDecoratorCombinations() {
        // Given - Espresso 커피 생성
        Beverage beverage1 = new Espresso();
        Beverage beverage2 = new Espresso();

        // When - 서로 다른 데코레이터 조합 적용
        beverage1 = new Milk(beverage1);
        beverage1 = new Whip(beverage1);

        beverage2 = new Soy(beverage2);
        beverage2 = new Mocha(beverage2);

        // Then - 각각 다른 결과가 나와야 함
        assertThat(beverage1.getDescription()).isEqualTo("에스프레소, 우유, 휘핑크림");
        assertThat(beverage1.cost()).isEqualTo(4000); // 2000 + 1000 + 1000

        assertThat(beverage2.getDescription()).isEqualTo("에스프레소, 두유, 모카");
        assertThat(beverage2.cost()).isEqualTo(5500); // 2000 + 1500 + 2000
    }

    @Test
    void testAllDecoratorsOnOneBeverage() {
        // Given - HouseBlend 커피 생성
        Beverage beverage = new HouseBlend();

        // When - 모든 종류의 데코레이터 추가
        beverage = new Milk(beverage);
        beverage = new Soy(beverage);
        beverage = new Whip(beverage);
        beverage = new Mocha(beverage);

        // Then - 모든 데코레이터가 순서대로 적용되어야 함
        assertThat(beverage.getDescription()).isEqualTo("하우스 블렌드, 우유, 두유, 휘핑크림, 모카");
        assertThat(beverage.cost()).isEqualTo(6500); // 1000 + 1000 + 1500 + 1000 + 2000
    }

    @Test
    void testDecoratorOrder() {
        // Given - DarkRoast 커피 생성
        Beverage beverage1 = new DarkRoast();
        Beverage beverage2 = new DarkRoast();

        // When - 같은 데코레이터를 다른 순서로 추가
        beverage1 = new Mocha(beverage1);
        beverage1 = new Milk(beverage1);

        beverage2 = new Milk(beverage2);
        beverage2 = new Mocha(beverage2);

        // Then - 가격은 같지만 설명은 순서가 달라야 함
        assertThat(beverage1.cost()).isEqualTo(beverage2.cost());
        assertThat(beverage1.getDescription()).isEqualTo("다크 로스트, 모카, 우유");
        assertThat(beverage2.getDescription()).isEqualTo("다크 로스트, 우유, 모카");
    }

    @Test
    void testDecoratorIndependence() {
        // Given - 같은 커피 객체
        Beverage baseBeverage = new Espresso();

        // When - 서로 다른 데코레이터로 감싸기
        Beverage beverage1 = new Mocha(baseBeverage);
        Beverage beverage2 = new Milk(baseBeverage);

        // Then - 각 데코레이터는 독립적으로 작동해야 함
        assertThat(beverage1.getDescription()).isEqualTo("에스프레소, 모카");
        assertThat(beverage1.cost()).isEqualTo(4000); // 2000 + 2000

        assertThat(beverage2.getDescription()).isEqualTo("에스프레소, 우유");
        assertThat(beverage2.cost()).isEqualTo(3000); // 2000 + 1000

        // 원본은 변경되지 않아야 함
        assertThat(baseBeverage.getDescription()).isEqualTo("에스프레소");
        assertThat(baseBeverage.cost()).isEqualTo(2000);
    }
}
