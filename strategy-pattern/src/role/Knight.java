package role;

import behavior.WeaponBehavior;

public class Knight extends Character {

    public Knight(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public String getName() {
        return Role.KNIGHT.name();
    }

}
