package main.java.com.utn.simbatallas.domain.exceptions;

import main.java.com.utn.simbatallas.domain.Army;
import main.java.com.utn.simbatallas.domain.MessageError;

/**
 * Created by ignacio on 28/04/17.
 * <p>
 * Exception que es lanzada cuando la referencia cruzada de oponentes no esta inicializada adecuadamente
 */
public class CrossReferenceException extends SimguerraException {
    public CrossReferenceException(Army army) {
        this.msg = new MessageError(
                army.getArmyName().toUpperCase() +
                        " ERROR: referencia cruzada errónea.\n"
        );
    }
}
