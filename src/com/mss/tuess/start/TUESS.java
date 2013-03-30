/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.start;

import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.entity.*;
import com.mss.tuess.util.ViewManager;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author wwh
 */
public class TUESS extends Application {
    
    private Stage stage;
    private static TUESS instance;
    static AnchorPane Login;
    public static String g="000000000000000000";
    public TUESS() {
        instance = this;
    }

    public static TUESS getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        DatabaseConnector.Connect();
        
        instance = this;
        this.stage = stage;
        ViewManager.setStage(stage);
        ViewManager.changeView("/com/mss/tuess/views/Login.fxml");
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