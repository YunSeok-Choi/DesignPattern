package role;

import behavior.WeaponBehavior;

public class Queen extends Character {

    public Queen(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public String getName() {
        return Role.QUEEN.name();
    }

}
