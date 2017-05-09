package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 03/05/17.
 */
public class SoldierDefenseArmor extends SoldierDefenseWrapper {
    public SoldierDefenseArmor() {
        super();
        this.setDefensa(super.getDefensa() + 500);
    }

    @Override
    public int defend() {
        return 500;
    }

    @Override
    public String toString() {
        return "Blindado";
    }
}
