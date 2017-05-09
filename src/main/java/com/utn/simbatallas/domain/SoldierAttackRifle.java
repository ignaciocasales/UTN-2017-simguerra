package main.java.com.utn.simbatallas.domain;

import java.util.Random;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase que define el ataque de un soldado con rifle
 */
public class SoldierAttackRifle extends SoldierAttackWrapper {

    public SoldierAttackRifle() {
        super();
        this.damage = super.getDamage() + 25;
    }

    @Override
    public void attack(ArmyUnit oponent) {
        int damage = 0;

        Random rnd = new Random();
        int pseudorandom = rnd.nextInt(100);
        int eleccion = 1;

        if (pseudorandom > 65) {
            eleccion = 2;
        } else if (pseudorandom > 45 && pseudorandom < 65) {
            eleccion = 3;
        }

        switch (eleccion) {
            case 1:
                damage = this.getDamage() - oponent.defend();
                break;
            case 2:
                damage = (this.getDamage() * 2) - oponent.defend();
                break;
            case 3:
                damage = (this.getDamage() * 5) - oponent.defend();
                break;
        }

        super.setDamageDone(oponent, damage);
    }

    @Override
    public String toString() {
        return "Fusil";
    }
}
