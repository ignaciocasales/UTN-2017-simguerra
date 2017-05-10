package main.java.com.utn.simbatallas.domain.exceptions;

import main.java.com.utn.simbatallas.domain.Army;
import main.java.com.utn.simbatallas.domain.MessageSuccess;

/**
 * Created by ignacio on 27/04/17.
 * <p>
 * Exception que es lanzada cuando termina una partida en condiciones
 */
public class BattleWinnerException extends SimguerraException {
    public BattleWinnerException(Army winner) {
        this.msg = new MessageSuccess(
                "Partida terminada !\n\t Ganador: " +
                        winner.getArmyName().toUpperCase()
        );
    }
}
