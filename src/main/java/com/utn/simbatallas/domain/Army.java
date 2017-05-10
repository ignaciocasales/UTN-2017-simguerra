package main.java.com.utn.simbatallas.domain;

import main.java.com.utn.simbatallas.domain.exceptions.CrossReferenceException;
import main.java.com.utn.simbatallas.domain.exceptions.NoSoldiersException;
import main.java.com.utn.simbatallas.domain.exceptions.NullOponentException;
import main.java.com.utn.simbatallas.domain.exceptions.SimguerraException;

import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Cada ejército es un hilo de ejecución
 */
public class Army extends Observable implements Runnable {

    private static final Object critsec = new Object();
    private String armyName;
    private List<ArmyUnit> unitList;
    private BattleField battleField;
    private Army enemy;

    /**
     * Constructor
     *
     * @param armyName    String
     * @param unitList    List
     * @param battleField BattleField
     */
    public Army(String armyName, List<ArmyUnit> unitList, BattleField battleField) {
        this.setArmyName(armyName);
        this.setUnitList(unitList);
        this.setBattleField(battleField);
    }

    public String getArmyName() {
        return armyName;
    }

    public void setArmyName(String armyName) {
        this.armyName = armyName;
    }

    public List<ArmyUnit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<ArmyUnit> unitList) {
        this.unitList = unitList;
    }

    private Object getCritsec() {
        return critsec;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }

    public Army getEnemy() {
        return enemy;
    }

    public void setEnemy(Army enemy) {
        this.enemy = enemy;
    }

    public boolean isDefeated() {
        return (this.getAliveUnits() == 0) && (!this.isEmpty());
    }

    public boolean isEmpty() {
        return (this.getUnitList().isEmpty());
    }

    public int getAliveUnits() {
        int contador = 0;

        if (!this.getUnitList().isEmpty()) {
            for (ArmyUnit u :
                    this.getUnitList()) {
                if (u.isAlive()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    private int randomNumber() {
        Random rand = new Random();

        int val = rand.nextInt() % 1500;

        if (val < 0) {
            val = val * (-1);
        }
        return val;
    }

    public ArmyUnit getRandomUnit() {

        List<ArmyUnit> list = this.getUnitList();

        Random rnd = new Random();

        int i = rnd.nextInt(list.size());

        return (list.get(i).isAlive()) ? list.get(i) : getRandomUnit();
    }


    public void attack() {
        ArmyUnit attacker = getRandomUnit();
        ArmyUnit defender = enemy.getRandomUnit();

        attacker.attack(defender);

        if (defender.getHealth() <= 0) {
            defender.setAlive(false);
        }

        this.setChanged();

        MessageBattleLog m = new MessageBattleLog(
                this.getArmyName().toUpperCase(),
                String.valueOf(attacker.getId()),
                attacker.getAttackBehavior().toString(),
                String.valueOf(attacker.getLastAttackDamage()),
                this.getEnemy().getArmyName().toUpperCase(),
                String.valueOf(defender.getId()),
                defender.getAttackBehavior().toString() + "(" + defender.getDefenseBehavior().toString() + ")",
                ((defender.getHealth() <= 0) ? "MUERTO" : String.valueOf(defender.getHealth())));

        notifyObservers(m);
    }

    @Override
    public String toString() {
        return "\n============Army " + this.getArmyName().toUpperCase() + "============" +
                "\nN° de Unidades => " + this.getUnitList().size() +
                "\nOponente => " +
                ((this.getEnemy() == null) ? "NO SETEADO" : this.getEnemy().getArmyName().toUpperCase());
    }

    /**
     * Método run...
     */
    @Override
    public void run() {
        try {
            if (this.getEnemy() == null) {
                // Si enemy es nulo
                throw new NullOponentException(this);
            } else {
                if (this.getEnemy().getEnemy() == null) {
                    // Si el oponende de mi enemy es NULL
                    throw new CrossReferenceException(this);
                } else if (!this.getEnemy().getEnemy().equals(this)) {
                    // Si el enemy de mi enemy no es THIS
                    throw new CrossReferenceException(this.getEnemy());
                } else {
                    if (this.isEmpty() || this.getEnemy().isEmpty()) {
                        // Si no tengo unidades o mi enemy no tiene unidades
                        if (this.isEmpty()) {
                            throw new NoSoldiersException(this);
                        } else if (this.getEnemy().isEmpty()) {
                            throw new NoSoldiersException(this.getEnemy());
                        }
                    } else {
                        // Mientras que no haya un ganador en el campo de batalla
                        while (this.getBattleField().getWinner() == null) {
                            // Duermo el hilo aleatoriamente creando margen
                            Thread.sleep((long) randomNumber());
                            // Entro en la critsec
                            synchronized (critsec) {
                                while (!this.getBattleField().isAvailable()) {
                                    // Si el recurso compartido (campo de batalla) no esta disponible WAIT
                                    critsec.wait();
                                }
                                // THIS se apodera de el campo de batalla
                                this.getBattleField().setAvailable(false);

                                this.getBattleField().confrontation(this, this.getEnemy());

                                // Libero el recurso compartido
                                this.getBattleField().setAvailable(true);

                                // Notifico a todos
                                critsec.notifyAll();
                            }
                        }
                    }
                }
            }
        } catch (SimguerraException e) {
            this.setChanged();

            notifyObservers(e.getMsg());
        } catch (InterruptedException e) {
            notifyObservers(
                    this.getArmyName().toUpperCase() +
                            " Thread Interrumpido.\n"
            );
        } catch (Exception e) {
            e.printStackTrace();
            MessageError msge = new MessageError(
                    this.getArmyName().toUpperCase() +
                            " Error desconocido.\n" +
                            e.getMessage()
            );
            this.setChanged();

            notifyObservers(msge);
            //e.printStackTrace();
        } finally {
            this.getBattleField().setAvailable(true);
        }
        System.out.println("EL THREAD " + this.getArmyName() + " HA TERMINADO !");
    }
}
