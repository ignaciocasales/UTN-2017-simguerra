package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase Wrapper para el definir el comportamiento comun de la Defensa de los Soldados
 */
public abstract class SoldierDefenseWrapper implements ISoldierDefense {

    protected int defensa;

    SoldierDefenseWrapper() {
        this.defensa = 10;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}
