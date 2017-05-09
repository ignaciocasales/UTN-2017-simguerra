package main.java.com.utn.simbatallas.domain;

import java.util.Random;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase que define la defensa de un soldado con Casco
 */
public class SoldierDefenseHelmet extends SoldierDefenseWrapper {

    public SoldierDefenseHelmet() {
        super();
        this.setDefensa(super.getDefensa() + 15);
    }

    @Override
    public int defend() {

        Random rnd = new Random();
        int pseudorand = rnd.nextInt(100);
        int eleccion = 1;
        int danioReflejado = 0;

        if (pseudorand > 70) {
            eleccion = 2;
        } else if (pseudorand > 60 && pseudorand < 70) {
            eleccion = 3;
        }

        switch (eleccion) {
            case 1:
                danioReflejado = this.getDefensa();
                break;
            case 2:
                danioReflejado = 0;
                break;
            case 3:
                danioReflejado = this.getDefensa() * 2;
                break;
        }

        return danioReflejado;
    }

    @Override
    public String toString() {
        return "Casco";
    }
}
