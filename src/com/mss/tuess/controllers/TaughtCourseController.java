
package com.mss.tuess.controllers;

import com.mss.tuess.util.*;
import com.mss.tuess.entity.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TaughtCourseController implements Initializable 
{
    @FXML  TableView<Section> taughtCoursesTable;
    @FXML  TableColumn<Section, String> courseDept;
    @FXML  TableColumn<Section, String> courseNum;    
    @FXML  TableColumn<Section, String> sectionID;
    @FXML  TableColumn<Section, String> courseName;
    @FXML  TableColumn<Section, String> termID;
    @FXML  TableColumn<Section, Integer> credit;
    
    @FXML Label currentTerm;
    @FXML Label totalCredits;
    
    private ObservableList<Section> tableContent = FXCollections.observableArrayList();
 
   
     /**
     * Constructor of RegisteredCourseController
     *
     * @throws SQLException
     */
    public TaughtCourseController() throws SQLException
    {
        tableContent.clear();
        
        Section.fetchByInstructor(CurrentUser.getUser().getID(), State.getCurrentTerm().getTermID());
        int taughtCoursesSize = Section.getAll().size();
        tableContent.clear();
        
        if(taughtCoursesSize > 0){
        tableContent.addAll(Section.getAll());
        }
    
    }
    
     /**
     * Handle the order for each column
     */
    private void tableOrderAct() {
        ArrayList<TableColumn<Section, ?>> sortOrder = new ArrayList(taughtCoursesTable.getSortOrder());
        taughtCoursesTable.getSortOrder().clear();
        taughtCoursesTable.getSortOrder().addAll(sortOrder);
    }

  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Term ct=State.getCurrentTerm();
        currentTerm.setText(ct.getTermID());
        
        courseDept.setCellValueFactory(new PropertyValueFactory<Section, String>("courseDept"));
        courseNum.setCellValueFactory(new PropertyValueFactory<Section, String>("courseNum"));
        sectionID.setCellValueFactory(new PropertyValueFactory<Section, String>("sectionID"));
        courseName.setCellValueFactory(new PropertyValueFactory<Section, String>("courseName"));
        termID.setCellValueFactory(new PropertyValueFactory<Section, String>("termID"));
        credit.setCellValueFactory(new PropertyValueFactory<Section, Integer>("credit"));

       
        totalCredits.setText(Integer.toString(RegisteredCourse.getTotalCredits()));

        
        taughtCoursesTable.setItems(tableContent);
  
    }
   
    
    
}
