package role;

import behavior.WeaponBehavior;

public abstract class Character {

    // 변경 가능한 동작(전략)을 인터페이스로 -> 전략 패턴
    private WeaponBehavior weaponBehavior;

    public Character(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    // 각 객체에 따라 다른 구현 -> 추상 메서드
    public abstract String getName();

    // 전략 변경
    public void changeWeapon(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    // 전략을 사용하는 메소드, 전략(weaponBehavior)을 사용하는 메서드가 있어야 전략 패턴
    public String fight() {
        return weaponBehavior.useWeapon(); // 전략에 위임
    }
}
