package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Interfaz de comportamiento de ataque de los Soldados
 */
public interface ISoldierAttack {
    void attack(ArmyUnit oponent);

    int getLastAttackDamage();

    void setLastAttackDamage(int damage);
}
