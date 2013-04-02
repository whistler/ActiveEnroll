/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import com.mss.tuess.util.State;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SectionController implements Initializable {

    @FXML Pane sidebar;
    
    @FXML TextField courseNum;
    @FXML TextField courseName;
    @FXML TextField courseDept;
    @FXML TextArea courseInfo;
    @FXML TextField courseCredits;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewManager.loadSidebar(sidebar);
        if(State.selectedCourse != null && !State.selectedCourse.getCourseNum().isEmpty()){
            //set course fields
            courseNum.setText(State.selectedCourse.getCourseNum());
            courseName.setText(State.selectedCourse.getCourseName());
            courseInfo.setText(State.selectedCourse.getInfo());
            courseDept.setText(State.selectedCourse.getCourseDept());
            courseCredits.setText(Integer.toString(State.selectedCourse.getCredit()));
        }
    }    
}
