/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import javafx.scene.layout.Pane;
import com.mss.tuess.entity.Course;
import com.mss.tuess.entity.Section;
import com.mss.tuess.util.State;
import com.mss.tuess.util.ViewManager;
import com.mss.tuess.entitylist.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewAllCoursesController implements Initializable {

    @FXML
    Pane sidebar;
    
    @FXML
    private TextField courseFilter;
    @FXML
    private TextField sectionFilter;
    
    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, String> courseNum;
    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, String> courseDept;
    @FXML
    private TableColumn<Course, String> info;
    @FXML
    private TableColumn<Course, Integer> credit;
    
    @FXML
    private TableView<Section> sectionTable;
    @FXML
    private TableColumn<Section, String> sectionID;
    @FXML
    private TableColumn<Section, String> type;
    @FXML
    private TableColumn<Section, String> days;
    @FXML
    private TableColumn<Section, String> startTime;
    @FXML
    private TableColumn<Section, String> endTime;
    @FXML
    private TableColumn<Section, Integer> instructor;
    
    private ObservableList<Course> courseTableContent = FXCollections.observableArrayList();
    private ObservableList<Course> courseFilterContent = FXCollections.observableArrayList();
    
    private ObservableList<Section> sectionTableContent = FXCollections.observableArrayList();
    private ObservableList<Section> sectionFilterContent = FXCollections.observableArrayList();

    /**
     * Constructor of CourseSearchController
     *
     * @throws SQLException
     */
    public ViewAllCoursesController() throws SQLException {
        CourseList.fetch();
        int courseSize = CourseList.getAll().size();
        //int courseCounter = 0;
        courseTableContent.clear();
        
        /*
         * This block has to be removed!
        while (courseSize - 1 != courseCounter) {
            courseTableContent.add(CourseList.get(courseCounter));
            courseCounter++;
        }*/
        //the above block is replaced by this block!!! - Karthik
        if(courseSize > 0){
        courseTableContent.addAll(CourseList.getAll());
        }
        
        courseFilterContent.addAll(courseTableContent);
        
        //event listener for course filter 
        
        courseTableContent.addListener(new ListChangeListener<Course>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Course> change) {
                courseFilterRefresh();
            }
        });
    }

    /**
     * Handle the order for each column
     */
    private void tableOrderAct() {
        ArrayList<TableColumn<Course, ?>> sortOrder = new ArrayList(courseTable.getSortOrder());
        courseTable.getSortOrder().clear();
        courseTable.getSortOrder().addAll(sortOrder);
    }

    /**
     * Refresh the content of the filter
     */
    private void courseFilterRefresh() {
        courseFilterContent.clear();
        for (Course course : courseTableContent) {
            if (filterChecker(course)) {
                courseFilterContent.add(course);
            }
        }
        tableOrderAct();
    }

    /**
     * Control the filter
     *
     * @param course the course obj
     */
    private boolean filterChecker(Course course) {
        String filterString = courseFilter.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }  
        
        String lowerCaseFilterString = filterString.toLowerCase();

        if (course.getCourseName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (course.getCourseDept().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (course.getCourseNum().toLowerCase().indexOf(lowerCaseFilterString) != -1){
            return true;
        } else if(course.getInfo().toLowerCase().indexOf(lowerCaseFilterString) != -1){
            return true;
        } else if(Integer.toString(course.getCredit()).trim().toLowerCase().indexOf(lowerCaseFilterString) != -1){
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
        
        /**
         * map the course table attributes
         */
        courseNum.setCellValueFactory(new PropertyValueFactory<Course, String>("courseNum"));
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        courseDept.setCellValueFactory(new PropertyValueFactory<Course, String>("courseDept"));
        info.setCellValueFactory(new PropertyValueFactory<Course, String>("info"));
        credit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));

        courseTable.setItems(courseFilterContent);
        
        /**
         * course filter event listener
         */
        courseFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                courseFilterRefresh();
            }
        });
        
        /*
         * Event Handler to capture the selected course
         */
         courseTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {

                    @Override
                    public void changed(ObservableValue<? extends Course> ov, Course t, Course t1) {
                        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
                        System.out.println("Index : "+selectedIndex);

                        try {
                            //ViewManager.changeView("/com/mss/tuess/views/Course.fxml");
                            String courseNum = courseFilterContent.get(selectedIndex).getCourseNum();
                            SectionList.fetch(courseNum);
                            sectionFilterContent.clear();
                            sectionTableContent.clear();
                            sectionTableContent.addAll(SectionList.getAll());
                            sectionFilterContent.addAll(SectionList.getAll());
                        } catch (Exception ex) {
                            Logger.getLogger(CourseSearchController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        
         /**
          * map the section table attributes
          */
        sectionID.setCellValueFactory(new PropertyValueFactory<Section, String>("sectionID"));
        type.setCellValueFactory(new PropertyValueFactory<Section, String>("type"));
        days.setCellValueFactory(new PropertyValueFactory<Section, String>("days"));
        startTime.setCellValueFactory(new PropertyValueFactory<Section, String>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<Section, String>("endTime"));
        instructor.setCellValueFactory(new PropertyValueFactory<Section, Integer>("instructor"));

        sectionTable.setItems(sectionFilterContent);
    }
}
