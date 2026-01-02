package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizza.Pizza;
import pizza.PizzaName;
import pizza_store.ChicagoPizzaStore;
import pizza_store.NYPizzaStore;
import pizza_store.PizzaStore;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryMethodTest {

    private PizzaStore nyPizzaStore;
    private PizzaStore chicagoPizzaStore;

    @BeforeEach
    void setUp() {
        // Given - NY와 Chicago 피자 가게 생성
        nyPizzaStore = new NYPizzaStore();
        chicagoPizzaStore = new ChicagoPizzaStore();
    }

    @Test
    void testNYStyleCheesePizza() {
        // Given - NY 피자 가게가 준비됨

        // When - NY 스타일 치즈 피자 주문
        Pizza pizza = nyPizzaStore.orderPizza(PizzaName.CHEESE);

        // Then - NY 스타일 치즈 피자가 생성되어야 함
        assertThat(pizza.getName()).contains("치즈");
        assertThat(pizza.toString()).contains("치즈");
        assertThat(pizza.toString()).contains("씬 크러스트 도우");
    }

    @Test
    void testChicagoStyleCheesePizza() {
        // Given - Chicago 피자 가게가 준비됨

        // When - Chicago 스타일 치즈 피자 주문
        Pizza pizza = chicagoPizzaStore.orderPizza(PizzaName.CHEESE);

        // Then - Chicago 스타일 치즈 피자가 생성되어야 함
        assertThat(pizza.getName()).contains("치즈");
        assertThat(pizza.toString()).contains("치즈");
        assertThat(pizza.toString()).contains("아주 두꺼운 크러스트 도우");
    }

    @Test
    void testFactoryMethodPattern() {
        // Given - 서로 다른 구체적인 PizzaStore들

        // When - 같은 타입의 피자를 주문
        Pizza nyPizza = nyPizzaStore.createPizza(PizzaName.CHEESE);
        Pizza chicagoPizza = chicagoPizzaStore.createPizza(PizzaName.CHEESE);

        // Then - 서로 다른 스타일의 피자가 생성되어야 함 (팩토리 메서드 패턴의 핵심)
        assertThat(nyPizza.toString()).isNotEqualTo(chicagoPizza.toString());
        assertThat(nyPizza.toString()).contains("씬 크러스트");
        assertThat(chicagoPizza.toString()).contains("아주 두꺼운 크러스트");
    }

    @Test
    void testPizzaPreparation() {
        // Given - NY 피자 가게가 준비됨
        Pizza pizza = nyPizzaStore.createPizza(PizzaName.CHEESE);

        // When - 피자 준비
        String prepareResult = pizza.prepare();

        // Then - 준비 과정이 올바르게 반환되어야 함
        assertThat(prepareResult).contains("준비 중");
        assertThat(prepareResult).contains("반죽 만드는 중");
        assertThat(prepareResult).contains("소스 추가중");
        assertThat(prepareResult).contains("토핑 추가중");
    }

    @Test
    void testPizzaBaking() {
        // Given - 피자가 생성됨
        Pizza pizza = nyPizzaStore.createPizza(PizzaName.CHEESE);

        // When - 피자 굽기
        String bakeResult = pizza.bake();

        // Then - 굽기 설명이 올바르게 반환되어야 함
        assertThat(bakeResult).contains("350도");
        assertThat(bakeResult).contains("25분");
    }

    @Test
    void testPizzaCutting() {
        // Given - 피자가 생성됨
        Pizza pizza = nyPizzaStore.createPizza(PizzaName.CHEESE);

        // When - 피자 자르기
        String cutResult = pizza.cut();

        // Then - 자르기 설명이 올바르게 반환되어야 함
        assertThat(cutResult).contains("알맞은 크기의 피자 조각");
    }

    @Test
    void testPizzaBoxing() {
        // Given - 피자가 생성됨
        Pizza pizza = nyPizzaStore.createPizza(PizzaName.CHEESE);

        // When - 피자 포장
        String boxResult = pizza.box();

        // Then - 포장 설명이 올바르게 반환되어야 함
        assertThat(boxResult).contains("박스");
        assertThat(boxResult).contains("포장");
    }

    @Test
    void testDifferentPizzaTypes() {
        // Given - NY 피자 가게가 준비됨

        // When - 서로 다른 종류의 피자 주문
        Pizza cheesePizza = nyPizzaStore.createPizza(PizzaName.CHEESE);
        Pizza veggiePizza = nyPizzaStore.createPizza(PizzaName.VEGGIE);
        Pizza pepperoniPizza = nyPizzaStore.createPizza(PizzaName.PEPPERONI);
        Pizza clamPizza = nyPizzaStore.createPizza(PizzaName.CLAM);

        // Then - 각각 다른 피자가 생성되어야 함
        assertThat(cheesePizza.getName()).isEqualTo("치즈 피자");
        assertThat(veggiePizza.getName()).isEqualTo("야채 피자");
        assertThat(pepperoniPizza.getName()).isEqualTo("페퍼로니 피자");
        assertThat(clamPizza.getName()).isEqualTo("조개 피자");
    }

    @Test
    void testPizzaToString() {
        // Given - 피자가 생성됨
        Pizza pizza = nyPizzaStore.createPizza(PizzaName.CHEESE);

        // When - toString 호출
        String pizzaDescription = pizza.toString();

        // Then - 피자 정보가 올바르게 출력되어야 함
        assertThat(pizzaDescription).contains("치즈");
        assertThat(pizzaDescription).contains("도우");
        assertThat(pizzaDescription).contains("소스");
    }

    @Test
    void testOrderPizzaProcess() {
        // Given - NY 피자 가게가 준비됨

        // When - 피자 주문 (전체 프로세스 실행)
        Pizza pizza = nyPizzaStore.orderPizza(PizzaName.CHEESE);

        // Then - 피자가 정상적으로 생성되고 반환되어야 함
        assertThat(pizza).isNotNull();
        assertThat(pizza.getName()).isEqualTo("치즈 피자");

        // Then - 준비, 굽기, 자르기, 포장이 모두 완료되어야 함
        String prepareResult = pizza.prepare();
        String bakeResult = pizza.bake();
        String cutResult = pizza.cut();
        String boxResult = pizza.box();

        assertThat(prepareResult).isNotEmpty();
        assertThat(bakeResult).isNotEmpty();
        assertThat(cutResult).isNotEmpty();
        assertThat(boxResult).isNotEmpty();
    }

    @Test
    void testChicagoDifferentPizzas() {
        // Given - Chicago 피자 가게가 준비됨

        // When - 여러 종류의 피자 주문
        Pizza cheesePizza = chicagoPizzaStore.createPizza(PizzaName.CHEESE);
        Pizza veggiePizza = chicagoPizzaStore.createPizza(PizzaName.VEGGIE);

        // Then - 모두 Chicago 스타일이어야 함
        assertThat(cheesePizza.toString()).contains("아주 두꺼운 크러스트");
        assertThat(veggiePizza.toString()).contains("아주 두꺼운 크러스트");

        // Then - 하지만 서로 다른 피자 타입이어야 함
        assertThat(cheesePizza.getName()).isNotEqualTo(veggiePizza.getName());
    }
}
