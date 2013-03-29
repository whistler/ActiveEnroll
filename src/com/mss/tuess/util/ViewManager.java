/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import com.mss.tuess.start.TUESS;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
}
