package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase de ataque con obus
 */
public class SoldierAttackObus extends SoldierAttackWrapper {
    public SoldierAttackObus() {
        super();
        this.damage = super.getDamage() + 50;
    }

    @Override
    public void attack(ArmyUnit oponent) {
        int danioHecho;

        if ((oponent.getAttackBehavior() instanceof SoldierAttackPlane) ||
                (oponent.getAttackBehavior() instanceof SoldierAttackRifle)) {
            danioHecho = oponent.getHealth();
        } else {
            danioHecho = this.getDamage();
        }

        oponent.setHealth(oponent.getHealth() - danioHecho);

        setLastAttackDamage(danioHecho);
    }

    @Override
    public String toString() {
        return "Obus";
    }
}
