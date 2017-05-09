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
    public void atacar(ArmyUnit oponente) {
        int danioHecho = 0;

        if ((oponente.getComportamientoAtaque() instanceof SoldierAttackTank) ||
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
        return "Avion";
    }
}
