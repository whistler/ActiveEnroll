
package com.mss.tuess.controllers;

import com.mss.tuess.util.*;
import com.mss.tuess.entity.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegisteredCourseController implements Initializable 
{
    @FXML  TableView<RegisteredCourse> registeredCoursesTable;
    @FXML  TableColumn<RegisteredCourse, String> courseDept;
    @FXML  TableColumn<RegisteredCourse, String> courseNum;    
    @FXML  TableColumn<RegisteredCourse, String> sectionID;
    @FXML  TableColumn<RegisteredCourse, String> courseName;
    @FXML  TableColumn<RegisteredCourse, String> termID;
    @FXML  TableColumn<RegisteredCourse, Integer> credit;
    
    @FXML Label currentTerm;
    @FXML Label totalCredits;
    
    private ObservableList<RegisteredCourse> tableContent = FXCollections.observableArrayList();
 
     /**
     * Constructor of RegisteredCourseController
     *
     * @throws SQLException
     */
    public RegisteredCourseController() throws SQLException {
        tableContent.clear();
        RegisteredCourse.fetch();
        int registeredCoursesSize = RegisteredCourse.getAll().size();
        
        if(registeredCoursesSize > 0){
            tableContent.addAll(RegisteredCourse.getAll());
        }
    }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Term ct=State.getCurrentTerm();
        currentTerm.setText(ct.getTermID());
        
        courseDept.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("courseDept"));
        courseNum.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("courseNum"));
        sectionID.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("sectionID"));
        courseName.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("courseName"));
        termID.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("termID"));
        credit.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, Integer>("credit"));

        totalCredits.setText(Integer.toString(RegisteredCourse.getTotalCredits()));   
        registeredCoursesTable.setItems(tableContent);
        
        
        /*
         * Event Handler to capture the selected course
         */
        registeredCoursesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegisteredCourse>() {
            @Override
            public void changed(ObservableValue<? extends RegisteredCourse> ov, RegisteredCourse t, RegisteredCourse t1) {
                int selectedIndex = registeredCoursesTable.getSelectionModel().getSelectedIndex();
                

                System.out.println("Index : " + selectedIndex);
                try {
                    Section currentSection = new Section();
                    currentSection.fetch(tableContent.get(selectedIndex).getSectionID(),
                                         tableContent.get(selectedIndex).getCourseDept(),
                                         tableContent.get(selectedIndex).getCourseNum(),
                                         tableContent.get(selectedIndex).getTermID());
                    State.setCurrentSection(currentSection);
                    ViewManager.changeView("Section");
                } catch (Exception ex) {
                    Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    

   
    
    
}
