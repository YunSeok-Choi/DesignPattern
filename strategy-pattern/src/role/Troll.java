package role;

import behavior.WeaponBehavior;

public class Troll extends Character {

    public Troll(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public String getName() {
        return Role.TROLL.name();
    }

}
