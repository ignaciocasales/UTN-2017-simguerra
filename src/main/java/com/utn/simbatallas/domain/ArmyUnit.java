package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase Soldado Base
 */
public class ArmyUnit implements ISoldierAttack, ISoldierDefense {

    private static int quantity = 0;
    private int id;
    private int health;
    private boolean alive;
    private ISoldierAttack attackBehavior;
    private ISoldierDefense defenseBehavior;

    public ArmyUnit(ISoldierAttack attackBehavior, ISoldierDefense defenseBehavior) {
        this.setId(quantity + 1);
        this.health = 250;
        this.alive = true;
        this.setAttackBehavior(attackBehavior);
        this.setDefenseBehavior(defenseBehavior);
        quantity = quantity + 1;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        ArmyUnit.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public ISoldierAttack getAttackBehavior() {
        return attackBehavior;
    }

    public void setAttackBehavior(ISoldierAttack attackBehavior) {
        this.attackBehavior = attackBehavior;
    }

    public ISoldierDefense getDefenseBehavior() {
        return defenseBehavior;
    }

    public void setDefenseBehavior(ISoldierDefense defenseBehavior) {
        this.defenseBehavior = defenseBehavior;
    }

    @Override
    public void attack(ArmyUnit oponent) {
        this.attackBehavior.attack(oponent);
    }

    @Override
    public int getLastAttackDamage() {
        return this.attackBehavior.getLastAttackDamage();
    }

    @Override
    public void setLastAttackDamage(int damage) {
        this.attackBehavior.setLastAttackDamage(damage);
    }

    @Override
    public int defend() {
        return this.defenseBehavior.defend();
    }

    @Override
    public String toString() {
        return "ArmyUnit";
    }
}