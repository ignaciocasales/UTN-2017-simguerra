package main.java.com.utn.simbatallas.domain.exceptions;

import main.java.com.utn.simbatallas.domain.Message;

/**
 * Created by Ignacio on 5/9/2017.
 * <p>
 * Super exception para simguerra
 */
public class SimguerraException extends Exception {
    protected Message msg;

    public Message getMsg() {
        return msg;
    }
}
