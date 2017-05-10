package main.java.com.utn.simbatallas.view;

import main.java.com.utn.simbatallas.domain.Army;
import main.java.com.utn.simbatallas.domain.Message;
import main.java.com.utn.simbatallas.persistence.BattleMySQLPersistence;

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

        if (o instanceof Army) {
            Army a = (Army) o;
            if (arg instanceof Message) {
                System.out.println(a.getArmyName().toUpperCase() + ((Message) arg).getSimpleMessage());
            }
        }

        if (o instanceof BattleMySQLPersistence) {
            if (arg instanceof Message) {
                Message msgs = (Message) arg;
                System.out.println("DATABASE " + msgs.getType() + msgs.getSimpleMessage());
            }
        }
    }
}