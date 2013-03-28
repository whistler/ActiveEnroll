/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.start;

import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author wwh
 */
public class TUESS extends Application {

    private static ViewManager viewManager;
    @Override
    public void start(Stage stage) throws Exception {
        DatabaseConnector db = new DatabaseConnector();
        Parent root = FXMLLoader.load(getClass().getResource("/com/mss/tuess/views/Login.fxml"));

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        launch(args);
        
    }
}