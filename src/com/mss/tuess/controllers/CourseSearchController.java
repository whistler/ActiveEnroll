/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.Course;
import com.mss.tuess.entitylist.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
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

public class CourseSearchController implements Initializable {

    @FXML
    private TextField filterText;
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
    private ObservableList<Course> tableContent = FXCollections.observableArrayList();
    private ObservableList<Course> filterContent = FXCollections.observableArrayList();

    public CourseSearchController() throws SQLException {
        CourseList.fetch();
        int courseSize = CourseList.getAll().size();
        int courseCounter = 0;
        while (courseSize != courseCounter) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa-------" + CourseList.get(courseCounter).getCourseName());
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

    private void filterRefresh() {
        filterContent.clear();

        for (Course course : tableContent) {
            if (filterChecker(course)) {
                filterContent.add(course);
            }
        }
        tableOrderAct();
    }

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
        }
        return false;
    }

    private void tableOrderAct() {
        ArrayList<TableColumn<Course, ?>> sortOrder = new ArrayList(courseTable.getSortOrder());
        courseTable.getSortOrder().clear();
        courseTable.getSortOrder().addAll(sortOrder);
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
    }
}
