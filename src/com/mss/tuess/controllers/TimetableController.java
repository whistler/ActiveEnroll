/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.timetable.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class TimetableController implements Initializable {

    @FXML
    Pane sidebar;
    @FXML
    Label termID;
    @FXML
    WebView timeTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TScheduler ts = new TScheduler();
        ts.update();
        WebEngine engine = timeTable.getEngine();
        engine.loadContent(ts.timeTable);

    }
}
