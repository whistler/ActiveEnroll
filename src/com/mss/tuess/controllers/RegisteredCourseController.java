
package com.mss.tuess.controllers;

import com.mss.tuess.util.*;
import com.mss.tuess.entity.*;
import com.mss.tuess.entitylist.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
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
    public RegisteredCourseController() throws SQLException
    {
        tableContent.clear();
        RegisteredCoursesList.fetch();
        int registeredCoursesSize = RegisteredCoursesList.getAll().size();
        tableContent.clear();
        
        if(registeredCoursesSize > 0){
        tableContent.addAll(RegisteredCoursesList.getAll());
        }
    
    }
    
     /**
     * Handle the order for each column
     */
    private void tableOrderAct() {
        ArrayList<TableColumn<RegisteredCourse, ?>> sortOrder = new ArrayList(registeredCoursesTable.getSortOrder());
        registeredCoursesTable.getSortOrder().clear();
        registeredCoursesTable.getSortOrder().addAll(sortOrder);
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

       
        totalCredits.setText(Integer.toString(RegisteredCoursesList.getTotalCredits()));

        
        registeredCoursesTable.setItems(tableContent);
  
    }
   
    
    
}
