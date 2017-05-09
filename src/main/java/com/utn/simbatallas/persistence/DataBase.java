package main.java.com.utn.simbatallas.persistence;

import main.java.com.utn.simbatallas.domain.BattleField;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by ignacio on 04/05/17.
 * <p>
 * Interface para la persistencia
 */
public abstract class DataBase extends Observable implements Observer {

    public abstract void addBattleResult(BattleField b);

    public abstract List<String[]> getAllBattlesResults();
}