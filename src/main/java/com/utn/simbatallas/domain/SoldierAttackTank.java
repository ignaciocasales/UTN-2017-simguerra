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
    public void atacar(ArmyUnit oponente) {
        int danioHecho;

        if ((oponente.getComportamientoAtaque() instanceof SoldierAttackObus) ||
                (oponente.getComportamientoAtaque() instanceof SoldierAttackRifle)) {
            danioHecho = 1000;
        } else {
            danioHecho = this.getDamage() - oponente.defend();
        }
        super.setDamageDone(oponente, danioHecho);
    }

    @Override
    public String toString() {
        return "Tanque";
    }
}
