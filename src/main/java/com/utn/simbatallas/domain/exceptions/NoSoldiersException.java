package main.java.com.utn.simbatallas.domain.exceptions;

import main.java.com.utn.simbatallas.domain.Army;
import main.java.com.utn.simbatallas.domain.MessageError;

/**
 * Created by ignacio on 27/04/17.
 * <p>
 * Exception que es lanzada cuando los ejércitos no tienen soldados
 */
public class NoSoldiersException extends SimguerraException {
    public NoSoldiersException(Army army) {
        this.msg = new MessageError(
                army.getArmyName().toUpperCase() +
                        " ERROR: no hay soldados en el ejército.\n"
        );
    }
}
