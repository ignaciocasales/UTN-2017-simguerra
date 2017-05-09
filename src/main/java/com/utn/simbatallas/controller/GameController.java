package main.java.com.utn.simbatallas.controller;

import main.java.com.utn.simbatallas.domain.*;
import main.java.com.utn.simbatallas.persistence.BattleMySQLPersistence;
import main.java.com.utn.simbatallas.persistence.DataBase;
import main.java.com.utn.simbatallas.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ignacio on 03/05/17.
 * <p>
 * Clase que inicia el juego
 */
public class GameController {

    private static GameController instance;
    private Thread tGermans;
    private Thread tRussians;

    private GameController() {

    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void run() {
        //creo el campo de batalla
        BattleField stalingrado = new BattleField("Stalingrado");
        //obtengo la base de datos
        DataBase db = BattleMySQLPersistence.getInstance();
        //instancio observadores
        ConsoleView consoleView = ConsoleView.getInstance();
        ViewBattleController vbc = ViewBattleController.getInstance();


        ArmyUnit.setQuantity(0);
        //creo el ejercito germans
        Army germans = createArmy("Alemanes", 5, stalingrado);
        //creo el ejercito russians
        Army russians = createArmy("Rusos", 5, stalingrado);

        //referencia cruzada de enemigos
        germans.setEnemy(russians);
        russians.setEnemy(germans);

        //seteo los observadores
        //germans.addObserver(consoleView);
        //russians.addObserver(consoleView);

        germans.addObserver(vbc);
        russians.addObserver(vbc);
        db.addObserver(consoleView);
        stalingrado.addObserver(db);

        tGermans = new Thread(germans);
        tRussians = new Thread(russians);

        //inicio los threads
        tGermans.start();
        tRussians.start();
    }

    public void stop() {
        if ((tGermans != null) && (tRussians != null)) {
            tGermans.interrupt();
            tRussians.interrupt();
        }
    }

    private Army createArmy(String name, int quantity, BattleField battleField) {

        List<ArmyUnit> units = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            ISoldierAttack soldierAttackBehavior = null;
            ISoldierDefense soldierDefenseBehavior = null;

            Random rnd = new Random();

            int attack = rnd.nextInt(4);

            switch (attack) {
                case 0:
                    soldierAttackBehavior = new SoldierAttackRifle();
                    break;
                case 1:
                    soldierAttackBehavior = new SoldierAttackObus();
                    break;
                case 2:
                    soldierAttackBehavior = new SoldierAttackTank();
                    break;
                case 3:
                    soldierAttackBehavior = new SoldierAttackPlane();
                    break;
            }

            if (soldierAttackBehavior instanceof SoldierAttackRifle) {
                int defense = rnd.nextInt(3);

                switch (defense) {
                    case 0:
                        soldierDefenseBehavior = new SoldierDefenseHelmet();
                        break;
                    case 1:
                        soldierDefenseBehavior = new SoldierDefenseTrench();
                        break;
                    case 2:
                        soldierDefenseBehavior = new SoldierDefenseRun();
                        break;
                }
            } else {
                soldierDefenseBehavior = new SoldierDefenseArmor();
            }

            ArmyUnit soldier = new ArmyUnit(soldierAttackBehavior, soldierDefenseBehavior);

            units.add(soldier);
        }

        return new Army(name, units, battleField);
    }
}
