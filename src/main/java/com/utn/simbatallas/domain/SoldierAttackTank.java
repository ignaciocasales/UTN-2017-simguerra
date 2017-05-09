package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase de ataque con tanque
 */
public class SoldierAttackTank extends SoldierAttackWrapper {

    public SoldierAttackTank() {
        super();
        this.setDamage(super.getDamage() + 100);
    }

    @Override
    public void attack(ArmyUnit oponent) {
        int danioHecho;

        if ((oponent.getAttackBehavior() instanceof SoldierAttackObus) ||
                (oponent.getAttackBehavior() instanceof SoldierAttackRifle)) {
            danioHecho = 1000;
        } else {
            danioHecho = this.getDamage() - oponent.defend();
        }
        super.setDamageDone(oponent, danioHecho);
    }

    @Override
    public String toString() {
        return "Tanque";
    }
}
