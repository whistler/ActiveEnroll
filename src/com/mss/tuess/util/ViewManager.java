/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import com.mss.tuess.controllers.DashboardController;
import com.mss.tuess.start.TUESS;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author ibrahim
 */
public class ViewManager {
    
    private static Stage stage;
    
    public static void setStage(Stage newStage)
    {
        stage = newStage;
    }
    
    public static Parent changeView(String fxml) throws Exception {
        
        Parent page = FXMLLoader.load(TUESS.class.getResource(fxml));
        Scene scene = new Scene(page);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
   
        return page;
    }
    
    public static void loadSidebar(Pane sidebar)
    {
        String path = CurrentUser.getSidebarPath();
        Object load = "";
        try {
            load = FXMLLoader.load(TUESS.class.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sidebar.getChildren().clear();
        sidebar.getChildren().add((Node) load);
    }
}
