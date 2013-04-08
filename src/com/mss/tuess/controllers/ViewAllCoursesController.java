/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import javafx.scene.layout.Pane;
import com.mss.tuess.entity.Course;
import com.mss.tuess.entity.Section;
import com.mss.tuess.entity.SectionClass;
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
    
    //filter fields
    @FXML private TextField courseFilter;
    @FXML private TextField sectionFilter;
    
    //Course Table and fields
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> courseNum;
    @FXML private TableColumn<Course, String> courseName;
    @FXML private TableColumn<Course, String> courseDept;
    @FXML private TableColumn<Course, String> info;
    @FXML private TableColumn<Course, Integer> credit;
    
    //Section Table and fields
    @FXML private TableView<SectionClass> sectionClassTable;
    @FXML private TableColumn<SectionClass, String> sectionID;
    @FXML private TableColumn<SectionClass, String> type;
    @FXML private TableColumn<SectionClass, String> day;
    @FXML private TableColumn<SectionClass, String> displayStartTime;
    @FXML private TableColumn<SectionClass, String> displayEndTime;
    @FXML private TableColumn<SectionClass, String> location;
    
    //Course list
    private ObservableList<Course> courseTableContent = FXCollections.observableArrayList();
    private ObservableList<Course> courseFilterContent = FXCollections.observableArrayList();
    
    //Section list
    private ObservableList<SectionClass> sectionClassTableContent = FXCollections.observableArrayList();
    private ObservableList<SectionClass> sectionClassFilterContent = FXCollections.observableArrayList();

    /**
     * Constructor of CourseSearchController
     *
     * @throws SQLException
     */
    public ViewAllCoursesController() throws SQLException {
        CourseList.fetch();
        int courseSize = CourseList.getAll().size();
        
        courseTableContent.clear();
        sectionClassTableContent.clear();
        
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
        
        //event listener for course filter 
        sectionClassTableContent.addListener(new ListChangeListener<SectionClass>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends SectionClass> change) {
                sectionClassFilterRefresh();
            }
        });
    }

    /**
     * Handle the order for each course column
     */
    private void tableOrderAct() {
        ArrayList<TableColumn<Course, ?>> sortOrder = new ArrayList(courseTable.getSortOrder());
        courseTable.getSortOrder().clear();
        courseTable.getSortOrder().addAll(sortOrder);
    }
    
    /**
     * Handle the order for each section column
     */
    private void sectionClassTableOrderAct() {
        ArrayList<TableColumn<SectionClass, ?>> sectionSortOrder = new ArrayList(sectionClassTable.getSortOrder());
        sectionClassTable.getSortOrder().clear();
        sectionClassTable.getSortOrder().addAll(sectionSortOrder);
    }

    /**
     * Refresh the content of the filter
     */
    private void courseFilterRefresh() {
        courseFilterContent.clear();
        for (Course course : courseTableContent) {
            if (courseFilterChecker(course)) {
                courseFilterContent.add(course);
            }
        }
        tableOrderAct();
    }
    
    /**
     * Refresh the content of the filter
     */
    private void sectionClassFilterRefresh() {
        sectionClassFilterContent.clear();
        for (SectionClass sectionClass : sectionClassTableContent) {
            if (sectionClassFilterChecker(sectionClass)) {
                sectionClassFilterContent.add(sectionClass);
            }
        }
        sectionClassTableOrderAct();
    }    

    /**
     * Control the course filter
     *
     * @param course the course object
     */
    private boolean courseFilterChecker(Course course) {
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
     * Control the section class filter
     *
     * @param SectionClass the course object
     */
    private boolean sectionClassFilterChecker(SectionClass sectionClass) {
        String filterString = sectionFilter.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }  
        
        String lowerCaseFilterString = filterString.toLowerCase();

        if (sectionClass.getSectionID().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (sectionClass.getType().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (sectionClass.getDay().toLowerCase().indexOf(lowerCaseFilterString) != -1){
            return true;
        } else if(sectionClass.getLocation().toLowerCase().indexOf(lowerCaseFilterString) != -1){
            return true;
        } else if (sectionClass.getDisplayStartTime().toLowerCase().indexOf(lowerCaseFilterString) != -1){
            return true;
        } else if(sectionClass.getDisplayEndTime().toLowerCase().indexOf(lowerCaseFilterString) != -1){
            return true;
        }  
        return false;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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

        
        /**
         * section filter event listener
         */
        sectionFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                sectionClassFilterRefresh();
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
                            String courseDept = courseFilterContent.get(selectedIndex).getCourseDept();
                            String currentTerm = State.getCurrentTerm().getTermID();
                            SearchSectionClassList.fetch(courseDept, courseNum, currentTerm);
                            
                            //sectionClassFilterContent.clear();
                            sectionClassTableContent.clear();
                            sectionClassTableContent.addAll(SearchSectionClassList.getAll());
                            sectionClassFilterContent.clear();
                            sectionClassFilterContent.addAll(sectionClassTableContent);
                        } catch (Exception ex) {
                            Logger.getLogger(CourseSearchController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
         
        /*
         * Event Handler to capture the selected course
         */
         sectionClassTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SectionClass>() {

                    @Override
                    public void changed(ObservableValue<? extends SectionClass> ov, SectionClass t, SectionClass t1) {
                        int selectedIndex = sectionClassTable.getSelectionModel().getSelectedIndex();
                        System.out.println("Index : "+selectedIndex);

                        try {
                            State.setCurrentSectionClass(sectionClassFilterContent.get(selectedIndex));
                            Section currentSection = new Section();
                            State.setCurrentSection(currentSection);
                            ViewManager.changeView("/com/mss/tuess/views/Section.fxml");
                        } catch (Exception ex) {
                            Logger.getLogger(CourseSearchController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
         
        
         /**
          * map the section table attributes
          */
        sectionID.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("sectionID"));
        type.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("type"));
        day.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("day"));
        displayStartTime.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("displayStartTime"));
        displayEndTime.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("displayEndTime"));
        location.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("location"));

        sectionClassTable.setItems(sectionClassFilterContent);
    }
}
