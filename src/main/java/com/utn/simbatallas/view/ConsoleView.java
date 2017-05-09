package main.java.com.utn.simbatallas.view;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ignacio on 26/04/17.
 * <p>
 * Clase Observer
 */
public class ConsoleView implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String) arg);
    }
}
