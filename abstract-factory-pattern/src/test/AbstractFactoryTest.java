package test;

import org.junit.jupiter.api.Test;
import pizza.Pizza;
import pizza.style.PizzaType;
import pizza_store.ChicagoPizzaStore;
import pizza_store.NYPizzaStore;
import pizza_store.PizzaStore;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFactoryTest {

    @Test
    void testNYCheesePizza() {
        // Given - NY 피자 스토어 생성
        PizzaStore nyPizzaStore = new NYPizzaStore();

        // When - 치즈 피자 주문
        Pizza pizza = nyPizzaStore.orderPizza(PizzaType.CHEESE);

        // Then - NY 스타일 재료들이 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("씬 크러스트 도우");
        assertThat(pizzaInfo).contains("마리나라 소스");
        assertThat(pizzaInfo).contains("레지아노 치즈");
    }

    @Test
    void testChicagoPepperoniPizza() {
        // Given - Chicago 피자 스토어 생성
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        // When - 페퍼로니 피자 주문
        Pizza pizza = chicagoPizzaStore.orderPizza(PizzaType.PEPPERONI);

        // Then - Chicago 스타일 재료들이 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("아주 두꺼운 크러스트 도우");
        assertThat(pizzaInfo).contains("플럼 토마토 소스");
        assertThat(pizzaInfo).contains("모짜렐라 치즈");
        assertThat(pizzaInfo).contains("블랙 올리브");
        assertThat(pizzaInfo).contains("가지");
        assertThat(pizzaInfo).contains("시금치");
        assertThat(pizzaInfo).contains("슬라이스 페퍼로니");
    }

    @Test
    void testNYPepperoniPizza() {
        // Given - NY 피자 스토어 생성
        PizzaStore nyPizzaStore = new NYPizzaStore();

        // When - 페퍼로니 피자 주문
        Pizza pizza = nyPizzaStore.orderPizza(PizzaType.PEPPERONI);

        // Then - NY 스타일 재료들이 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("씬 크러스트 도우");
        assertThat(pizzaInfo).contains("마리나라 소스");
        assertThat(pizzaInfo).contains("레지아노 치즈");
        assertThat(pizzaInfo).contains("슬라이스 페퍼로니");
    }

    @Test
    void testChicagoCheesePizza() {
        // Given - Chicago 피자 스토어 생성
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        // When - 치즈 피자 주문
        Pizza pizza = chicagoPizzaStore.orderPizza(PizzaType.CHEESE);

        // Then - Chicago 스타일 재료들이 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("아주 두꺼운 크러스트 도우");
        assertThat(pizzaInfo).contains("플럼 토마토 소스");
        assertThat(pizzaInfo).contains("모짜렐라 치즈");
    }

    @Test
    void testNYClamPizza() {
        // Given - NY 피자 스토어 생성
        PizzaStore nyPizzaStore = new NYPizzaStore();

        // When - 조개 피자 주문
        Pizza pizza = nyPizzaStore.orderPizza(PizzaType.CLAM);

        // Then - NY 스타일 재료들과 신선한 조개가 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("씬 크러스트 도우");
        assertThat(pizzaInfo).contains("마리나라 소스");
        assertThat(pizzaInfo).contains("레지아노 치즈");
    }

    @Test
    void testChicagoClamPizza() {
        // Given - Chicago 피자 스토어 생성
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        // When - 조개 피자 주문
        Pizza pizza = chicagoPizzaStore.orderPizza(PizzaType.CLAM);

        // Then - Chicago 스타일 재료들이 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("아주 두꺼운 크러스트 도우");
        assertThat(pizzaInfo).contains("플럼 토마토 소스");
        assertThat(pizzaInfo).contains("모짜렐라 치즈");
    }

    @Test
    void testNYVeggiePizza() {
        // Given - NY 피자 스토어 생성
        PizzaStore nyPizzaStore = new NYPizzaStore();

        // When - 야채 피자 주문
        Pizza pizza = nyPizzaStore.orderPizza(PizzaType.VEGGIE);

        // Then - NY 스타일 재료들이 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("씬 크러스트 도우");
        assertThat(pizzaInfo).contains("마리나라 소스");
        assertThat(pizzaInfo).contains("레지아노 치즈");
    }

    @Test
    void testChicagoVeggiePizza() {
        // Given - Chicago 피자 스토어 생성
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        // When - 야채 피자 주문
        Pizza pizza = chicagoPizzaStore.orderPizza(PizzaType.VEGGIE);

        // Then - Chicago 스타일 재료들이 사용되어야 함
        String pizzaInfo = pizza.toString();
        assertThat(pizzaInfo).contains("아주 두꺼운 크러스트 도우");
        assertThat(pizzaInfo).contains("플럼 토마토 소스");
        assertThat(pizzaInfo).contains("모짜렐라 치즈");
    }

    @Test
    void testDifferentStoresUseDifferentIngredients() {
        // Given - NY와 Chicago 피자 스토어 생성
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        // When - 같은 타입의 피자를 각 스토어에서 주문
        Pizza nyPizza = nyPizzaStore.orderPizza(PizzaType.CHEESE);
        Pizza chicagoPizza = chicagoPizzaStore.orderPizza(PizzaType.CHEESE);

        // Then - 서로 다른 재료가 사용되어야 함
        String nyPizzaInfo = nyPizza.toString();
        String chicagoPizzaInfo = chicagoPizza.toString();

        // NY는 씬 크러스트와 레지아노 치즈 사용
        assertThat(nyPizzaInfo).contains("씬 크러스트 도우");
        assertThat(nyPizzaInfo).contains("레지아노 치즈");

        // Chicago는 두꺼운 크러스트와 모짜렐라 치즈 사용
        assertThat(chicagoPizzaInfo).contains("아주 두꺼운 크러스트 도우");
        assertThat(chicagoPizzaInfo).contains("모짜렐라 치즈");

        // 서로 다른 재료 확인
        assertThat(nyPizzaInfo).doesNotContain("아주 두꺼운 크러스트 도우");
        assertThat(chicagoPizzaInfo).doesNotContain("씬 크러스트 도우");
    }

    @Test
    void testAllPizzaTypes() {
        // Given - NY 피자 스토어 생성
        PizzaStore nyPizzaStore = new NYPizzaStore();

        // When - 모든 피자 타입 주문
        Pizza cheesePizza = nyPizzaStore.orderPizza(PizzaType.CHEESE);
        Pizza pepperoniPizza = nyPizzaStore.orderPizza(PizzaType.PEPPERONI);
        Pizza clamPizza = nyPizzaStore.orderPizza(PizzaType.CLAM);
        Pizza veggiePizza = nyPizzaStore.orderPizza(PizzaType.VEGGIE);

        // Then - 모든 피자가 정상적으로 생성되어야 함
        assertThat(cheesePizza).isNotNull();
        assertThat(pepperoniPizza).isNotNull();
        assertThat(clamPizza).isNotNull();
        assertThat(veggiePizza).isNotNull();

        // 모든 피자가 NY 스타일 도우를 사용해야 함
        assertThat(cheesePizza.toString()).contains("씬 크러스트 도우");
        assertThat(pepperoniPizza.toString()).contains("씬 크러스트 도우");
        assertThat(clamPizza.toString()).contains("씬 크러스트 도우");
        assertThat(veggiePizza.toString()).contains("씬 크러스트 도우");
    }

    @Test
    void testAbstractFactoryPattern() {
        // Given - 각 지역의 피자 스토어 생성
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        // When - 같은 타입의 피자를 주문
        Pizza nyPepperoni = nyPizzaStore.orderPizza(PizzaType.PEPPERONI);
        Pizza chicagoPepperoni = chicagoPizzaStore.orderPizza(PizzaType.PEPPERONI);

        // Then - 각 팩토리가 일관된 재료군을 사용해야 함
        // NY 팩토리: 씬 크러스트 + 마리나라 소스 + 레지아노 치즈
        String nyInfo = nyPepperoni.toString();
        assertThat(nyInfo).contains("씬 크러스트 도우");
        assertThat(nyInfo).contains("마리나라 소스");
        assertThat(nyInfo).contains("레지아노 치즈");

        // Chicago 팩토리: 두꺼운 크러스트 + 플럼 토마토 소스 + 모짜렐라 치즈
        String chicagoInfo = chicagoPepperoni.toString();
        assertThat(chicagoInfo).contains("아주 두꺼운 크러스트 도우");
        assertThat(chicagoInfo).contains("플럼 토마토 소스");
        assertThat(chicagoInfo).contains("모짜렐라 치즈");
    }

}
