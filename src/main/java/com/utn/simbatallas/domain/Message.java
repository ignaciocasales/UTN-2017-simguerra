package main.java.com.utn.simbatallas.domain;

/**
 * Created by Ignacio on 5/6/2017.
 * <p>
 * Clase Mensaje
 */
public abstract class Message {

    protected String type = "template";
    private String simpleMessage;

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
