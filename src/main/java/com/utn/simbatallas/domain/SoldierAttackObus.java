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
    public void atacar(ArmyUnit oponente) {
        int danioHecho = 0;

        if ((oponente.getComportamientoAtaque() instanceof SoldierAttackPlane) ||
                (oponente.getComportamientoAtaque() instanceof SoldierAttackRifle)) {
            danioHecho = 1000;
        } else {
            danioHecho = this.getDamage();
        }

        oponente.setSalud(oponente.getSalud() - danioHecho);

        setDanioHechoUltimoAtaque(danioHecho);
    }

    @Override
    public String toString() {
        return "Obus";
    }
}
