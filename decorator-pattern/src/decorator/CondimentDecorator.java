package decorator;

import berverage.Beverage;

public abstract class CondimentDecorator extends Beverage {

    protected Beverage beverage;

    // 이미 Beverage에 있기 떄문에 중복 구현이지만 강제 구현을 위해 추상 메서드 선언.
    // 구현 클래스는 최상위 추상 클래스인 Beverage의 cost()와 현재 CondimentDecorator의 getDescription()을 강제 구현 해야 함.
    public abstract String getDescription();
}
