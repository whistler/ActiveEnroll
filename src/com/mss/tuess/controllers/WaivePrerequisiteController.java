package com.mss.tuess.controllers;

import com.mss.tuess.entitylist.WaivePrerequisiteList;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class WaivePrerequisiteController implements Initializable {
    
 @FXML ComboBox coursesToWaive;
   
     private ObservableList<String> courseToWaiveList = FXCollections.observableArrayList();

 
     /**
     * Constructor of RegisteredCourseController
     *
     * @throws SQLException
     */
    public WaivePrerequisiteController() throws SQLException
    {
        WaivePrerequisiteList.fetch();
        int courseToTeachSize = WaivePrerequisiteList.getAll().size();
        for(int i=0;i<courseToTeachSize;i++)
        {
            courseToWaiveList.addAll(WaivePrerequisiteList.getCoursesToTeach(i).getCourseDept()
                    +WaivePrerequisiteList.getCoursesToTeach(i).getCourseNum()+" "
                    +WaivePrerequisiteList.getCoursesToTeach(i).getSectionID()+": "
                    +WaivePrerequisiteList.getCoursesToTeach(i).getCourseName());
        }


    }
    
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        coursesToWaive.setItems(courseToWaiveList);
    } 
}
