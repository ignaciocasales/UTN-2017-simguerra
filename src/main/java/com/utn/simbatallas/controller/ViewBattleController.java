package main.java.com.utn.simbatallas.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.com.utn.simbatallas.domain.*;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Ignacio on 5/6/2017.
 * <p>
 * Controlador de la vista principal
 */
public class ViewBattleController implements Initializable, Observer {

    private static ViewBattleController instance;
    private final ObservableList<Record> dataList
            = FXCollections.observableArrayList();
    @FXML
    private TableView<Record> table;
    @FXML
    private TableColumn<Record, String> c1;
    @FXML
    private TableColumn<Record, String> c2;
    @FXML
    private TableColumn<Record, String> c3;
    @FXML
    private TableColumn<Record, String> c4;
    @FXML
    private TableColumn<Record, String> c5;
    @FXML
    private TableColumn<Record, String> c6;
    @FXML
    private TableColumn<Record, String> c7;
    @FXML
    private TableColumn<Record, String> c8;
    @FXML
    private Button start;
    @FXML
    private Button exit;
    @FXML
    private Button results;
    @FXML
    private Label ruscount;
    @FXML
    private Label gercount;

    private ViewBattleController() {

    }

    static ViewBattleController getInstance() {
        if (instance == null) {
            instance = new ViewBattleController();
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("CONTROLADOR DE VISTA(" + this.getClass().getSimpleName().toUpperCase() + ")CARGADO");

        c1.setCellValueFactory(
                new PropertyValueFactory<>("f1"));
        c2.setCellValueFactory(
                new PropertyValueFactory<>("f2"));
        c3.setCellValueFactory(
                new PropertyValueFactory<>("f3"));
        c4.setCellValueFactory(
                new PropertyValueFactory<>("f4"));
        c5.setCellValueFactory(
                new PropertyValueFactory<>("f5"));
        c6.setCellValueFactory(
                new PropertyValueFactory<>("f6"));
        c7.setCellValueFactory(
                new PropertyValueFactory<>("f7"));
        c8.setCellValueFactory(
                new PropertyValueFactory<>("f8"));

        table.setItems(dataList);
    }

    @FXML
    public void handleBtnStartGame() {
        results.setDisable(true);
        table.getItems().clear();
        GameController.getInstance().run();
    }

    @FXML
    public void handleBtnExit() {
        GameController.getInstance().stop();
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleBtnResults() {
        try {
            Stage stage = (Stage) results.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ui/results.fxml"));

            ViewResultsController rs = ViewResultsController.getInstance();

            loader.setController(rs);

            Parent secondaryScene = loader.load();

            stage.setScene(new Scene(secondaryScene));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println("UPDATE: vista.");
        if (o instanceof Army) {
            Army a = (Army) o;
            if (a.getArmyName().equals("Alemanes")) {
                Platform.runLater(() -> gercount.setText(String.valueOf(a.getAliveUnits())));
                Platform.runLater(() -> ruscount.setText(String.valueOf(a.getEnemy().getAliveUnits())));
            } else if (a.getArmyName().equals("Rusos")) {
                Platform.runLater(() -> ruscount.setText(String.valueOf(a.getAliveUnits())));
                Platform.runLater(() -> gercount.setText(String.valueOf(a.getEnemy().getAliveUnits())));
            }
            if (arg instanceof Message) {
                if (arg instanceof MessageBattleLog) {
                    MessageBattleLog mbl = (MessageBattleLog) arg;

                    String[] strings = new String[8];

                    strings[0] = mbl.getS1();
                    strings[1] = mbl.getS2();
                    strings[2] = mbl.getS3();
                    strings[3] = mbl.getS4();
                    strings[4] = mbl.getS5();
                    strings[5] = mbl.getS6();
                    strings[6] = mbl.getS7();
                    strings[7] = mbl.getS8();

                    Record record = new Record(strings[0], strings[1], strings[2],
                            strings[3], strings[4], strings[5], strings[6], strings[7]);
                    dataList.add(record);
                } else if (arg instanceof MessageSuccess) {
                    MessageSuccess m = (MessageSuccess) arg;

                    results.setDisable(false);

                    Platform.runLater(() -> {
                        AppStart.alert.setTitle(m.getType());
                        AppStart.alert.setHeaderText(null);
                        AppStart.alert.setContentText(m.getSimpleMessage());
                        AppStart.alert.setAlertType(Alert.AlertType.INFORMATION);
                        AppStart.alert.showAndWait();
                    });
                } else if (arg instanceof MessageError) {
                    MessageError m = (MessageError) arg;

                    Platform.runLater(() -> {
                        AppStart.alert.setTitle(m.getType());
                        AppStart.alert.setHeaderText(null);
                        AppStart.alert.setContentText(m.getSimpleMessage());
                        AppStart.alert.setAlertType(Alert.AlertType.ERROR);
                        AppStart.alert.showAndWait();
                    });
                }
            }
        }
    }

    @SuppressWarnings("unused")
    public class Record {
        //Assume each record have 6 elements, all String
        private SimpleStringProperty f1, f2, f3, f4, f5, f6, f7, f8;

        Record(String f1, String f2, String f3, String f4,
               String f5, String f6, String f7, String f8) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
            this.f3 = new SimpleStringProperty(f3);
            this.f4 = new SimpleStringProperty(f4);
            this.f5 = new SimpleStringProperty(f5);
            this.f6 = new SimpleStringProperty(f6);
            this.f7 = new SimpleStringProperty(f7);
            this.f8 = new SimpleStringProperty(f8);
        }

        Record(String f1, String f2, String f3, String f4,
               String f5, String f6, String f7) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
            this.f3 = new SimpleStringProperty(f3);
            this.f4 = new SimpleStringProperty(f4);
            this.f5 = new SimpleStringProperty(f5);
            this.f6 = new SimpleStringProperty(f6);
            this.f7 = new SimpleStringProperty(f7);
        }

        public String getF1() {
            return f1.get();
        }

        public String getF2() {
            return f2.get();
        }

        public String getF3() {
            return f3.get();
        }

        public String getF4() {
            return f4.get();
        }

        public String getF5() {
            return f5.get();
        }

        public String getF6() {
            return f6.get();
        }

        public String getF7() {
            return f7.get();
        }

        public String getF8() {
            return f8.get();
        }
    }
}
