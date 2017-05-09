package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase de ataque con avion
 */
public class SoldierAttackPlane extends SoldierAttackWrapper {
    public SoldierAttackPlane() {
        super();
        this.damage = super.getDamage() + 75;
    }

    @Override
    public void attack(ArmyUnit oponent) {
        int danioHecho = 0;

        if ((oponent.getAttackBehavior() instanceof SoldierAttackTank) ||
                (oponent.getAttackBehavior() instanceof SoldierAttackRifle)) {
            danioHecho = 1000;
        } else {
            danioHecho = this.getDamage();
        }

        oponent.setHealth(oponent.getHealth() - danioHecho);

        setLastAttackDamage(danioHecho);
    }

    @Override
    public String toString() {
        return "Avion";
    }
}
