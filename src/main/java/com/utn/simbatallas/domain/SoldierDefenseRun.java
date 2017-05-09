package main.java.com.utn.simbatallas.domain;

import java.util.Random;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase que define la defensa de un soldado que intenta evadir los impactos
 */
public class SoldierDefenseRun extends SoldierDefenseWrapper {

    public SoldierDefenseRun() {
        super();
        this.setDefensa(super.getDefensa());
    }

    @Override
    public int defend() {

        Random rnd = new Random();
        int eleccion = rnd.nextInt(2);
        int danioReflejado = 0;

        switch (eleccion) {
            case 0:
                danioReflejado = this.getDefensa();
                break;
            case 1:
                danioReflejado = this.getDefensa() * 10;
                break;
        }

        return danioReflejado;
    }

    @Override
    public String toString() {
        return "Correr";
    }
}
