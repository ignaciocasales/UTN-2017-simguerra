package main.java.com.utn.simbatallas.view;

import main.java.com.utn.simbatallas.domain.Message;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ignacio on 26/04/17.
 * <p>
 * Clase Observer
 */
public class ConsoleView implements Observer {
    private static ConsoleView instance;

    private ConsoleView() {
    }

    public static ConsoleView getInstance() {
        if (instance == null) {
            instance = new ConsoleView();
        }
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Message) {
            System.out.println(((Message) arg).getSimpleMessage());
        }
    }
}