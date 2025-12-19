import pizza.Pizza;
import pizza.style.PizzaType;
import pizza_store.ChicagoPizzaStore;
import pizza_store.NYPizzaStore;
import pizza_store.PizzaStore;

public class Main {
    public static void main(String[] args) {
        /*
        구조:
        - 제품: 6가지 관련 재료들 (Dough, Sauce, Cheese, Veggies, Pepperoni, Clams)
        - 생성 방식: 인터페이스로 정의, 구체 팩토리가 서로 어울리는 제품군을 생성
        - 목적: 관련 객체들을 일관성 있게 생성 (NY 재료끼리, Chicago 재료끼리)
        */
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        Pizza nyCheesePizza = nyPizzaStore.orderPizza(PizzaType.CHEESE);
        System.out.println(nyCheesePizza);
        /*
        치즈 피자준비 중...
        350도에서 25분간 굽기
        알맞은 크기의 피자 조각으로 자르기
        피자 가게 박스에 피자 포장하기
        씬 크러스트 도우
        마리나라 소스
        레지아노 치즈
        */

        Pizza chicagoPepperoniPizza = chicagoPizzaStore.orderPizza(PizzaType.PEPPERONI);
        System.out.println(chicagoPepperoniPizza);
        /*
        페퍼로니 피자준비 중...
        350도에서 25분간 굽기
        알맞은 크기의 피자 조각으로 자르기
        피자 가게 박스에 피자 포장하기
        아주 두꺼운 크러스트 도우
        플럼 토마토 소스
        모짜렐라 치즈
        블랙 올리브, 가지, 시금치
        슬라이스 페퍼로니
        */

    }
}
