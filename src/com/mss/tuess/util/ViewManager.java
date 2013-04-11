/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import com.mss.tuess.controllers.LayoutController;
import com.mss.tuess.entity.User;
import com.mss.tuess.start.TUESS;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author ibrahim
 */
public class ViewManager {

    private static Stage stage;

    /**
     * Sets the window(Stage) that should be used to display Views
     *
     * @param newStage stage to use
     */
    public static void setStage(Stage newStage) {
        stage = newStage;
        stage.setTitle("TUESS");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Changes the Scene (whole screen) to the View defined in the given FXML
     * path
     *
     * @param fxml the path of the view to load
     * @throws Exception
     */
    public static void changeScene(String fxml) throws Exception {
        Parent page = FXMLLoader.load(TUESS.class.getResource(fxml));
        Scene scene = new Scene(page);
        stage.setScene(scene);
    }

    /**
     * Changes the main content of the screen to the specified View
     *
     * @param fxml path of the view to load
     */
    public static void changeView(String fxml) {
        setStatus("Loading...");
        Pane pane = (Pane) stage.getScene().lookup("#content");
        Object load = "";
        try {
            load = FXMLLoader.load(TUESS.class.getResource(fxml));
        } catch (IOException ex) {
            Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane.getChildren().clear();
        pane.getChildren().add((Node) load);
        setStatus("");
    }

    public static void setStatus(String status) {
        Label label = (Label) stage.getScene().lookup("#statusLabel");
        label.setText(status);
    }
    
    public static void setTitle(String title) {
        Label label = (Label) stage.getScene().lookup("#programTitle");
        label.setText(title);
    }

    /**
     * Loads the side bar in to the given Pane depending on the type of user
     * that is logged in
     *
     * @param sidebar Pane that should hold the side bar
     */
    public static void loadSidebar(Pane sidebar) {
        System.out.println(stage.getScene());
        String path = CurrentUser.getSidebarPath();
        Object load = "";
        try {
            load = FXMLLoader.load(TUESS.class.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sidebar.getChildren().clear();
        sidebar.getChildren().add((Node) load);
    }

    public static void setUser(User user) {
        Label label = (Label) stage.getScene().lookup("#loggedInUser");
        if (user == null) {
            label.setText("");
        } else {
            label.setText("Welcome " + user.getFirstName()
                    + user.getLastName() + "\nID : " + user.getID());
        }
    }
}
