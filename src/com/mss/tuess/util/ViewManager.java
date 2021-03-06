package com.mss.tuess.util;

import com.mss.tuess.controllers.LayoutController;
import com.mss.tuess.entity.User;
import com.mss.tuess.start.TUESS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Manages all screens in the program including switching windows, displaying 
 * status messages, displaying the right side bar and header
 * @author ibrahim
 */
public class ViewManager {

    private static Stage stage;
    private static Stage help = new Stage();
    private static ArrayList previousNodes = new ArrayList();

    /**
     * Initializes a new stage to use as the help window
     */
    public static void loadHelp(){
        Parent page;
        try {
            page = FXMLLoader.load(TUESS.class.getResource("/com/mss/tuess/views/Help.fxml"));
            Scene scene = new Scene(page);
            help.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Sets the window(Stage) that should be used to display Views
     *
     * @param newStage stage to use
     */
    public static void setStage(Stage newStage) {
        stage = newStage;
        stage.setTitle("ActiveEnroll");
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
    public static void changeView(String view) {
        String fxml = "/com/mss/tuess/views/" + view + ".fxml";
        ViewManager.setStatus("");
        Pane pane = (Pane) stage.getScene().lookup("#content");
        previousNodes.clear();
        previousNodes.addAll(pane.getChildren());
        Object load = "";
        try {
            load = FXMLLoader.load(TUESS.class.getResource(fxml));
        } catch (IOException ex) {
            Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane.getChildren().clear();
        pane.getChildren().add((Node) load);
    }

    /**
     * Update the status text of the program
     * @param status 
     */
    public static void setStatus(String status) {
        Label label = (Label) stage.getScene().lookup("#statusLabel");
        label.setText(status);
    }
    
    /**
     * Sets title for the program
     * @param title of the current view
     */
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

    /**
     * Displays user information in the header
     * @param user that is logged in
     */
    public static void setUser(User user) {
        Label label = (Label) stage.getScene().lookup("#loggedInUser");
        if (user == null) {
            label.setText("");
        } else {
            label.setText("Welcome " + user.getFirstName() + " " + user.getLastName() + "! ");
        }
    }
    
    /**
     * Displays the screen that was displayed before the current screen
     */
    public static void showPreviousView()
    {
        Pane pane = (Pane) stage.getScene().lookup("#content");
        pane.getChildren().clear();
        pane.getChildren().addAll(previousNodes);
        setStatus("");
    }
    
    /**
     * Show help file in a new window
     * @param fileName html file to load in to the help window (store in help package)
     */
    public static void showHelp()
    {
        Scene scene = help.getScene();
        WebView webview = (WebView) scene.lookup("#htmlViewer");
        try{
            String url = TUESS.class.getResource("/com/mss/tuess/help/index.html").toExternalForm();
            webview.getEngine().load(url);
            help.show();
        }
        catch (Exception e){
            System.out.println("Help file: not found!");
        }
    }
    
    /**
     * Shows an error dialog
     * @param error title of the error
     * @param description description of the error
     */
    public static void showError(String error, String description)
    {
        Dialogs.showErrorDialog(stage, description, error, "Error");
    }
}
