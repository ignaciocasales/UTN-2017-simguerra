package main.java.com.utn.simbatallas.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Ignacio on 5/5/2017.
 * <p>
 * Clase que inicia la apilicacion
 */
public class AppStart extends Application {

    static Alert alert;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //seteo alert como un dialog
        alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setResizable(false);
        //alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("../../assets/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("../../assets/soldier-icon.png").toString()));
        //cargador de la vista principal
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ui/game-view.fxml"));
        ViewBattleController s = ViewBattleController.getInstance();
        loader.setController(s);
        Parent root = loader.load();
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Simulador de batallas - by Ignacio Casales - v0.1");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(this.getClass().getResource("../../assets/soldier-icon.png").toString()));
        primaryStage.show();
    }
}