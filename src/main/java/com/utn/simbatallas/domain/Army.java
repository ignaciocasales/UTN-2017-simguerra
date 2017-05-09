package main.java.com.utn.simbatallas.domain;

import main.java.com.utn.simbatallas.domain.exceptions.NoSoldadosException;
import main.java.com.utn.simbatallas.domain.exceptions.OponenteNoSetException;
import main.java.com.utn.simbatallas.domain.exceptions.PartidaTerminadaException;
import main.java.com.utn.simbatallas.domain.exceptions.ReferenciaCruzadaNoSetThisException;

import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Cada ejército es un hilo de ejecución
 */
public class Army extends Observable implements Runnable {

    private final Object criticalSection;
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
        this.criticalSection = new Object();
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

    private Object getCriticalSection() {
        return criticalSection;
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

    public int getAliveUnits() {
        int contador = 0;
        if (!this.getUnitList().isEmpty()) {
            for (ArmyUnit u :
                    this.getUnitList()) {
                if (u.isEstado()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public boolean isDefeated() {
        return (this.getAliveUnits() == 0) && (!this.isEmpty());
    }

    public boolean isEmpty() {
        return (this.getUnitList().isEmpty());
    }

    private int randomNumber() {
        Random ran = new Random();

        int val = ran.nextInt() % 1500;

        if (val < 0) {
            val = val * (-1);
        }
        return val;
    }

    public ArmyUnit getRandomUnit() {

        List<ArmyUnit> list = this.getUnitList();

        Random rnd = new Random();

        int i = rnd.nextInt(list.size());

        return (list.get(i).isEstado()) ? list.get(i) : getRandomUnit();
    }


    public void attack() {
        ArmyUnit attacker = getRandomUnit();
        ArmyUnit defender = enemy.getRandomUnit();

        attacker.atacar(defender);

        if (defender.getSalud() <= 0) {
            defender.setEstado(false);
        }

        this.setChanged();

        MessageBattle m = new MessageBattle(
                this.getArmyName().toUpperCase(),
                String.valueOf(attacker.getId()),
                attacker.getComportamientoAtaque().toString(),
                String.valueOf(attacker.getDanioHechoUltimoAtaque()),
                this.getEnemy().getArmyName().toUpperCase(),
                String.valueOf(defender.getId()),
                defender.getComportamientoAtaque().toString() + "(" + defender.getComportamientoDefensa().toString() + ")",
                ((defender.getSalud() <= 0) ? "MUERTO" : String.valueOf(defender.getSalud())));

        notifyObservers(m);
        /*old notify
        notifyObservers(
                "ATAQUE: SoldadoID:" + attacker.getId() + " (" +
                        this.getArmyName().toUpperCase() + ")" +
                        " hizo Daño " + attacker.getDanioHechoUltimoAtaque() +
                        " con " + attacker.getComportamientoAtaque().getTipoArma() +
                        " -> a SoldadoID:" + defend.getId() + " (" +
                        this.enemy.getArmyName().toUpperCase() + ")" +
                        " defensa: " + defend.getComportamientoDefensa().getTipoDefensa() +
                        " y dejadolo " + ((defend.getSalud() <= 0) ? "MUERTO" :
                        "con " + defend.getSalud() + " de vida."));
        */
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
                throw new OponenteNoSetException();
            } else {
                if (this.getEnemy().getEnemy() == null) {
                    // Si el oponende de mi enemy es NULL
                    throw new ReferenciaCruzadaNoSetThisException();
                } else if (!this.getEnemy().getEnemy().equals(this)) {
                    // Si el enemy de mi enemy no es THIS
                    throw new ReferenciaCruzadaNoSetThisException();
                } else {
                    if (this.isEmpty() || this.getEnemy().isEmpty()) {
                        // Si no tengo unidades o mi enemy no tiene unidades
                        throw new NoSoldadosException();
                    } else {
                        // Mientras que no haya un ganador en el campo de batalla
                        while (this.getBattleField().getWinner() == null) {
                            // Duermo el hilo aleatoriamente creando margen
                            Thread.sleep((long) randomNumber());
                            // Entro en la criticalSection
                            synchronized (criticalSection) {
                                while (!this.getBattleField().isAvailable()) {
                                    // Si el recurso compartido (campo de batalla) no esta disponible WAIT
                                    this.getCriticalSection().wait();
                                }
                                // THIS se apodera de el campo de batalla
                                this.getBattleField().setAvailable(false);

                                this.getBattleField().enfrentamiento(this, this.getEnemy());
                                // Libero el recurso compartido
                                this.getBattleField().setAvailable(true);
                                // Notifico a todos
                                this.getCriticalSection().notifyAll();
                            }
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.printf(this.getArmyName().toUpperCase() + " Thread Interrumpido.\n");
        } catch (OponenteNoSetException e) {
            System.out.printf(this.getArmyName().toUpperCase() + " ERROR: enemy no set.\n");
        } catch (PartidaTerminadaException e) {
            this.setChanged();

            MessageSuccess m = new MessageSuccess(
                    "Partida terminada, ganador: " +
                    this.getBattleField().getWinner().getArmyName().toUpperCase() +
                    "\n(los resultados se guardarán en la base de datos)"
            );

            notifyObservers(m);
        } catch (NoSoldadosException e) {
            System.out.printf(this.getArmyName().toUpperCase() + " ERROR: no hay soldadosList.\n");
        } catch (ReferenciaCruzadaNoSetThisException e) {
            System.out.printf(this.getArmyName().toUpperCase() + " ERROR: referencia cruzada no set.\n");
        } catch (Exception e) {
            System.out.println(this.getArmyName().toUpperCase() + " ERROR: desconocido.\n");
            e.printStackTrace();
        } finally {
            this.getBattleField().setAvailable(true);
        }
    }
}
