/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import javafx.scene.layout.Pane;
import com.mss.tuess.entity.Course;
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

public class CourseSearchController_unused implements Initializable {

    @FXML private TextField filterText;
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> courseNum;
    @FXML private TableColumn<Course, String> courseName;
    @FXML private TableColumn<Course, String> courseDept;
    @FXML private TableColumn<Course, String> info;
    @FXML private TableColumn<Course, Integer> credit;
    
    private ObservableList<Course> tableContent = FXCollections.observableArrayList();
    private ObservableList<Course> filterContent = FXCollections.observableArrayList();

    /**
     * Constructor of CourseSearchController_unused
     *
     * @throws SQLException
     */
    public CourseSearchController_unused() throws SQLException {
        CourseList.fetch();
        int courseSize = CourseList.getAll().size();
        int courseCounter = 0;
        tableContent.clear();
        while (courseSize - 1 != courseCounter) {
            tableContent.add(CourseList.get(courseCounter));
            courseCounter++;
        }
        filterContent.addAll(tableContent);

        tableContent.addListener(new ListChangeListener<Course>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Course> change) {
                filterRefresh();
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
    private void filterRefresh() {
        filterContent.clear();
        for (Course course : tableContent) {
            if (filterChecker(course)) {
                filterContent.add(course);
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
        String filterString = filterText.getText();
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
        }
        return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseNum.setCellValueFactory(new PropertyValueFactory<Course, String>("courseNum"));
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        courseDept.setCellValueFactory(new PropertyValueFactory<Course, String>("courseDept"));
        info.setCellValueFactory(new PropertyValueFactory<Course, String>("info"));
        credit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));

        courseTable.setItems(filterContent);
        
        filterText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                filterRefresh();
            }
        });
        
        /*
         * Event Handler to capture the selected row
         * 
         */
         courseTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {

                    @Override
                    public void changed(ObservableValue<? extends Course> ov, Course t, Course t1) {
                        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
                        System.out.println("Index : "+selectedIndex);

                        try {
                            ViewManager.changeView("/com/mss/tuess/views/Section.fxml");
                        } catch (Exception ex) {
                            Logger.getLogger(CourseSearchController_unused.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
}
