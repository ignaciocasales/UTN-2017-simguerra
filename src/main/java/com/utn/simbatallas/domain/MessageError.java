package main.java.com.utn.simbatallas.domain;

/**
 * Created by Ignacio on 5/6/2017.
 * <p>
 * Clase Mensaje de error
 */
public class MessageError extends Message {
    public MessageError(String simpleMessage) {
        super(simpleMessage);
        this.setType("ERROR: ");
    }
}
