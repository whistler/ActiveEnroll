
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

public class RegisteredCoursesController implements Initializable 
{

    @FXML Pane sidebar;
   
    @FXML
    private TextField filterText;
    
    @FXML private TableView<RegisteredCourses> registeredCoursesTable;
    
    @FXML 
    private TableColumn<RegisteredCourses, String> courseDept;
    @FXML 
    private TableColumn<RegisteredCourses, String> courseNum;    
    @FXML 
    private TableColumn<RegisteredCourses, String> sectionID;
    @FXML 
    private TableColumn<RegisteredCourses, String> courseName;
    @FXML 
    private TableColumn<RegisteredCourses, String> termID;
    @FXML 
    private TableColumn<RegisteredCourses, String> day;
    @FXML 
    private TableColumn<RegisteredCourses, String> type;
    @FXML 
    private TableColumn<RegisteredCourses, Integer> credit;
    
    @FXML 
    private TextField totalCredit;

    
    private ObservableList<RegisteredCourses> tableContent = FXCollections.observableArrayList();
    private ObservableList<RegisteredCourses> filterContent = FXCollections.observableArrayList();
 
   
     /**
     * Constructor of RegisteredCoursesController
     *
     * @throws SQLException
     */
    public RegisteredCoursesController() throws SQLException
    {
        tableContent.clear();
        RegisteredCoursesList.fetch();
        int registeredCoursesSize = RegisteredCoursesList.getRegisteredCoursesList().size();
        int courseCounter=0;
        while(registeredCoursesSize-1!=courseCounter)
        {
            tableContent.add(RegisteredCoursesList.getRegisteredCourse(courseCounter));
            courseCounter++;
        }
        filterContent.addAll(tableContent);

        tableContent.addListener(new ListChangeListener<RegisteredCourses>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends RegisteredCourses> change) {
                filterRefresh();
            }
        });
    }
    
        /**
     * Handle the order for each column
     */
    private void tableOrderAct() {
        ArrayList<TableColumn<RegisteredCourses, ?>> sortOrder = new ArrayList(registeredCoursesTable.getSortOrder());
        registeredCoursesTable.getSortOrder().clear();
        registeredCoursesTable.getSortOrder().addAll(sortOrder);
    }

    /**
     * Refresh the content of the filter
     */
    private void filterRefresh() {
        filterContent.clear();
        for (RegisteredCourses registeredCourses : tableContent) {
            if (filterChecker(registeredCourses)) {
                filterContent.add(registeredCourses);
            }
        }
        tableOrderAct();
    }

    private boolean filterChecker(RegisteredCourses registeredCourses) {
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
        
        courseDept.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("courseDept"));
        courseNum.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("courseNum"));
        sectionID.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("sectionID"));
        courseName.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("courseName"));
        termID.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("termID"));
        day.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("day"));
        type.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("type"));
        courseNum.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, String>("courseNum"));
        credit.setCellValueFactory(new PropertyValueFactory<RegisteredCourses, Integer>("credit"));

        
       totalCredit.setText(Integer.toString(TranscriptList.getAddCredit()));
       
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
