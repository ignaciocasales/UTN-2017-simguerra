package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase Wrapper para el definir el comportamiento comun de los ataques de los soldados
 */
public abstract class SoldierAttackWrapper implements ISoldierAttack {

    protected int damage;
    protected int lastAttackDamage;

    SoldierAttackWrapper() {
        this.damage = 10;
        this.lastAttackDamage = 0;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int danioBase) {
        this.damage = danioBase;
    }

    public void setDamageDone(ArmyUnit oponent, int damage) {
        if (damage > 0) {
            oponent.setHealth(oponent.getHealth() - damage);
            setLastAttackDamage(damage);
        } else {
            oponent.setHealth(oponent.getHealth() - this.getDamage());
            setLastAttackDamage(this.getDamage());
        }
    }

    @Override
    public int getLastAttackDamage() {
        return this.lastAttackDamage;
    }

    @Override
    public void setLastAttackDamage(int damage) {
        this.lastAttackDamage = damage;
    }
}
