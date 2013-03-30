/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.views;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wwh
 */
public class Tuess extends Application {

    private Stage stage;
    private static Tuess instance;
    public static com.mss.tuess.util.DatabaseConnector db_con;
    static AnchorPane Login;
    public static String g="000000000000000000";

    public Tuess() {
        instance = this;
    }

    public static Tuess getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            instance = this;
            this.stage = primaryStage;
            db_con = new com.mss.tuess.util.DatabaseConnector();
            Login = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage.setTitle("Enroll");
            stage.setMinHeight(700);
            stage.setMinWidth(550);
            stage.setScene(new Scene(Login));
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(Tuess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}