package main.java.com.utn.simbatallas.domain;

import main.java.com.utn.simbatallas.domain.exceptions.BattleWinnerException;

import java.util.Observable;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Este es el recurso compartido donde acceden los Hilos
 */
public class BattleField extends Observable {

    private Army winner;
    private String name;
    private Boolean available;

    public BattleField(String name) {
        this.setName(name);
        this.setAvailable(true);
        winner = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Army getWinner() {
        return this.winner;
    }

    public void setWinner(Army winner) {
        this.winner = winner;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * Método al que acceden los hilos. El primero que llega es al que le toca attack.
     *
     * @param attacker Army
     * @param defender Army
     */
    void confrontation(Army attacker, Army defender) throws BattleWinnerException {
        if (this.getWinner() != null) {
            this.setChanged();

            notifyObservers(this);

            throw new BattleWinnerException(this.getWinner());
        } else {
            if (!(attacker.isDefeated()) && (defender.isDefeated())) {
                this.setWinner(attacker);
            } else if ((attacker.isDefeated()) && !(defender.isDefeated())) {
                this.setWinner(defender);
            } else {
                attacker.attack();
            }
        }
    }
}
