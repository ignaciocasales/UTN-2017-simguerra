package main.java.com.utn.simbatallas.domain;

import main.java.com.utn.simbatallas.domain.exceptions.PartidaTerminadaException;

import java.util.Observable;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Este es el recurso compartido donde acceden los Hilos
 */
public class BattleField extends Observable {

    private String name;
    private Army winner;
    private Boolean available;
    private Boolean random;

    public BattleField(String name, Boolean random) {
        this.setName(name);
        this.setAvailable(true);
        winner = null;
        this.setRandom(random);
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

    public Boolean isRandom() {
        return random;
    }

    public void setRandom(Boolean random) {
        this.random = random;
    }

    /**
     * Método al que acceden los hilos. El primero que llega es al que le toca attack.
     *
     * @param atacar   Army
     * @param defender Army
     */
    void enfrentamiento(Army atacar, Army defender) throws PartidaTerminadaException {
        if (this.getWinner() != null) {
            throw new PartidaTerminadaException();
        } else {
            if (!(atacar.isDefeated()) && (defender.isDefeated())) {
                this.setWinner(atacar);

                this.setChanged();

                notifyObservers(this);
            } else if ((atacar.isDefeated()) && !(defender.isDefeated())) {
                this.setWinner(defender);

                this.setChanged();

                notifyObservers(this);
            } else {
                if (isRandom()) {
                    atacar.attack();
                } else {
                    //TODO
                }
            }
        }
    }
}
