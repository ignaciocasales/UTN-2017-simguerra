package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase que define la defensa de un soldado con Casco
 */
public class SoldierDefenseTrench extends SoldierDefenseWrapper {

    public SoldierDefenseTrench() {
        super();
        this.setDefensa(super.getDefensa() + 100);
    }

    @Override
    public int defend() {
        return getDefensa();
    }

    @Override
    public String toString() {
        return "Trinchera";
    }
}
