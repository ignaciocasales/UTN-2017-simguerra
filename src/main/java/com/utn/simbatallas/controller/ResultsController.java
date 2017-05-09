package main.java.com.utn.simbatallas.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.com.utn.simbatallas.persistence.BattleMySQLPersistence;
import main.java.com.utn.simbatallas.persistence.DataBase;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ignacio on 5/7/2017.
 */
public class ResultsController implements Initializable {

    private static ResultsController instance;
    private final ObservableList<Record> dataList
            = FXCollections.observableArrayList();

    @FXML
    private TableView<Record> tableResults;
    @FXML
    private TableColumn c21;
    @FXML
    private TableColumn c22;
    @FXML
    private TableColumn c23;
    @FXML
    private TableColumn c24;
    @FXML
    private TableColumn c25;
    @FXML
    private TableColumn c26;
    @FXML
    private TableColumn c27;
    @FXML
    private Button reload;
    @FXML
    private Button goBack;

    private ResultsController() {

    }

    public static ResultsController getInstance() {
        if (instance == null) {
            instance = new ResultsController();
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("CONTROLADOR DE VISTA CARGADO");

        c21.setCellValueFactory(
                new PropertyValueFactory<>("f1"));
        c22.setCellValueFactory(
                new PropertyValueFactory<>("f2"));
        c23.setCellValueFactory(
                new PropertyValueFactory<>("f3"));
        c24.setCellValueFactory(
                new PropertyValueFactory<>("f4"));
        c25.setCellValueFactory(
                new PropertyValueFactory<>("f5"));
        c26.setCellValueFactory(
                new PropertyValueFactory<>("f6"));
        c27.setCellValueFactory(
                new PropertyValueFactory<>("f7"));

        tableResults.setItems(dataList);

        handleBtnReload();
    }

    @FXML
    public void handleBtnGoBack() {
        try {
            Stage stage = (Stage) goBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ui/game-view.fxml"));
            SimpleController s = SimpleController.getInstance();
            loader.setController(s);
            Parent primaryScene = loader.load();
            stage.setScene(new Scene(primaryScene));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnReload() {
        tableResults.getItems().clear();

        DataBase db = BattleMySQLPersistence.getInstance();

        List<String[]> dbrslt = db.getAllBattlesResults();

        for (String[] string :
                dbrslt) {
            Record record = new Record(string[0], string[1], string[2],
                    string[3], string[4], string[5], string[6]);

            dataList.add(record);
        }
    }

    @SuppressWarnings("unused")
    public class Record {
        //Assume each record have 6 elements, all String
        private SimpleStringProperty f1, f2, f3, f4, f5, f6, f7;

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
    }
}
