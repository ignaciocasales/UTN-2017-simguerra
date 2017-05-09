package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Interfaz de comportamiento de ataque de los Soldados
 */
public interface ISoldierAttack {
    void atacar(ArmyUnit oponente);

    int getDanioHechoUltimoAtaque();

    void setDanioHechoUltimoAtaque(int danio);
}
