/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class SidebarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void showMyProfile()
    {
        
    }
    
    public void showSearchCourses()
    {
    
    }
    
    public void showRegisteredCourses()
    {
        
    }
    
    public void showTranscript()
    {
        
    }
    
    public void showMyProgram()
    {
        
    }
    
    public void logout() throws Exception 
    {
        ViewManager.replaceSceneContent("/com/mss/tuess/views/Login.fxml");
    }
    
}
