package main.java.com.utn.simbatallas.domain.exceptions;

import main.java.com.utn.simbatallas.domain.Army;
import main.java.com.utn.simbatallas.domain.MessageError;

/**
 * Created by ignacio on 27/04/17.
 * <p>
 * Exception que es lanzada cuando el oponente de un ejército no esta inicializado
 */
public class NullOponentException extends SimguerraException {
    public NullOponentException(Army army) {
        this.msg = new MessageError(
                army.getArmyName().toUpperCase() +
                        " ERROR: oponente no inicializado.\n"
        );
    }
}
