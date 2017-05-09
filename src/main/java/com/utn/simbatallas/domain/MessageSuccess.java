package main.java.com.utn.simbatallas.domain;

/**
 * Created by Ignacio on 5/6/2017.
 * <p>
 * Clase Mensaje de exito
 */
public class MessageSuccess extends Message {
    public MessageSuccess(String simpleMessage) {
        super(simpleMessage);
        this.setType("SUCCESS: ");
    }
}
