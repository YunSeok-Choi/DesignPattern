package role;

import behavior.WeaponBehavior;

public class King extends Character {

    public King(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public String getName() {
        return Role.KING.name();
    }

}
