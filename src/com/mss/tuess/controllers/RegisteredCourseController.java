
package com.mss.tuess.controllers;

import com.mss.tuess.util.*;
import com.mss.tuess.entity.*;
import com.mss.tuess.entitylist.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class RegisteredCourseController implements Initializable 
{

    @FXML Pane sidebar;
   
    @FXML TextField filterText;
    
    @FXML TableView<RegisteredCourse> registeredCoursesTable;
    @FXML  TableColumn<RegisteredCourse, String> courseDept;
    @FXML  TableColumn<RegisteredCourse, String> courseNum;    
    @FXML  TableColumn<RegisteredCourse, String> sectionID;
    @FXML  TableColumn<RegisteredCourse, String> courseName;
    @FXML  TableColumn<RegisteredCourse, String> termID;
    @FXML  TableColumn<RegisteredCourse, String> day;
    @FXML  TableColumn<RegisteredCourse, String> type;
    @FXML  TableColumn<RegisteredCourse, Integer> credit;
    
    @FXML Label currentTerm;
    @FXML Label totalCredits;
    
    private ObservableList<RegisteredCourse> tableContent = FXCollections.observableArrayList();
    private ObservableList<RegisteredCourse> filterContent = FXCollections.observableArrayList();
 
   
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
        filterContent.addAll(tableContent);

        tableContent.addListener(new ListChangeListener<RegisteredCourse>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends RegisteredCourse> change) {
                filterRefresh();
            }
        });
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
     * Refresh the content of the filter
     */
    private void filterRefresh() {
        filterContent.clear();
        for (RegisteredCourse registeredCourses : tableContent) {
            if (filterChecker(registeredCourses)) {
                filterContent.add(registeredCourses);
            }
        }
        tableOrderAct();
    }

    private boolean filterChecker(RegisteredCourse registeredCourse) {
        String filterString = filterText.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }
        return false;
    }
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewManager.loadSidebar(sidebar);
        
        Term ct=State.getCurrentTerm();
        currentTerm.setText(ct.getTermID());
        
        courseDept.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("courseDept"));
        courseNum.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("courseNum"));
        sectionID.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("sectionID"));
        courseName.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("courseName"));
        termID.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("termID"));
        day.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("day"));
        type.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("type"));
        courseNum.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, String>("courseNum"));
        credit.setCellValueFactory(new PropertyValueFactory<RegisteredCourse, Integer>("credit"));

       
        totalCredits.setText(Integer.toString(RegisteredCoursesList.getTotalCredits()));

        
        registeredCoursesTable.setItems(filterContent);
       
        filterText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                filterRefresh();
            }
        });
    }
   
    
    
}
