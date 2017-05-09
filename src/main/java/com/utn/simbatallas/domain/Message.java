package main.java.com.utn.simbatallas.domain;

/**
 * Created by Ignacio on 5/6/2017.
 * <p>
 * Clase Mensaje
 */
public class Message {
    String simpleMessage;
    String type;

    public Message() {
        type = "template";
    }

    public Message(String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
