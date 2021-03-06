/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.Course;
import com.mss.tuess.entity.*;
import com.mss.tuess.entity.SectionClass;
import com.mss.tuess.util.InputType;
import com.mss.tuess.util.State;
import com.mss.tuess.util.Validator;
import com.mss.tuess.util.ViewManager;
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

/**
 * @SearchCoursesController
 * This method is the controller of searching course function for both students and instructors during registration
 */

public class SearchCoursesController implements Initializable {

    //advanced search fields
    @FXML
    private TextField searchCode;
    @FXML
    private TextField searchName;
    @FXML
    private TextField searchDepartment;
    @FXML
    private TextField searchInfo;
    @FXML
    private TextField searchCredit;
    //filter fields
    @FXML
    private TextField courseFilter;
    @FXML
    private TextField sectionFilter;
    //Course Table and fields
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
    //Section Table and fields
    @FXML
    private TableView<SectionClass> sectionClassTable;
    @FXML
    private TableColumn<SectionClass, String> sectionID;
    @FXML
    private TableColumn<SectionClass, String> type;
    @FXML
    private TableColumn<SectionClass, String> day;
    @FXML
    private TableColumn<SectionClass, String> displayStartTime;
    @FXML
    private TableColumn<SectionClass, String> displayEndTime;
    @FXML
    private TableColumn<SectionClass, String> location;
    //Course list
    private ObservableList<Course> courseTableContent = FXCollections.observableArrayList();
    private ObservableList<Course> courseFilterContent = FXCollections.observableArrayList();
    //Section list
    private ObservableList<SectionClass> sectionClassTableContent = FXCollections.observableArrayList();
    private ObservableList<SectionClass> sectionClassFilterContent = FXCollections.observableArrayList();

    /**
     * Constructor of CourseSearchController_unused
     *
     * @throws SQLException
     */
    public SearchCoursesController() throws SQLException {
        int courseSize;

        courseTableContent.clear();
        sectionClassTableContent.clear();

        //if flag is search all courses
        if (State.getCurrentSearchView().equalsIgnoreCase("AllCourses")) {
            Course.fetch();
            courseSize = Course.getAll().size();

            if (courseSize > 0) {
                courseTableContent.addAll(Course.getAll());
            }
            courseFilterContent.addAll(courseTableContent);
        }


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

    @FXML
    private void advancedCourseSearch() throws SQLException {
        searchCourseByParam();
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
        if (areFilterFieldsValid()) {
            courseFilterContent.clear();
            for (Course course : courseTableContent) {
                if (courseFilterChecker(course)) {
                    courseFilterContent.add(course);
                }
            }
            tableOrderAct();
        }
    }

    /**
     * Refresh the content of the filter
     */
    private void sectionClassFilterRefresh() {
        if (areFilterFieldsValid()) {
            sectionClassFilterContent.clear();
            for (SectionClass sectionClass : sectionClassTableContent) {
                if (sectionClassFilterChecker(sectionClass)) {
                    sectionClassFilterContent.add(sectionClass);
                }
            }
            sectionClassTableOrderAct();
        }
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
        } else if (course.getCourseNum().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (course.getInfo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (Integer.toString(course.getCredit()).trim().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
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
        } else if (sectionClass.getDay().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (sectionClass.getLocation().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (sectionClass.getDisplayStartTime().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (sectionClass.getDisplayEndTime().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }
        return false;
    }

    /**
     * Initializes the controller class.
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //searchErrorLabel.setText("");
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

                try {
                    if (selectedIndex >= 0) {
                        String courseNum = courseFilterContent.get(selectedIndex).getCourseNum();
                        String courseDept = courseFilterContent.get(selectedIndex).getCourseDept();
                        String currentTerm = State.getCurrentTerm().getTermID();
                        SectionClass.fetch(courseDept, courseNum, currentTerm);

                        sectionClassTableContent.clear();
                        sectionClassTableContent.addAll(SectionClass.getAll());
                        sectionClassFilterContent.clear();
                        sectionClassFilterContent.addAll(sectionClassTableContent);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
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
                Section currentSection = new Section();

                try {
                    if (selectedIndex >= 0) {
                        State.setCurrentSectionClass(sectionClassFilterContent.get(selectedIndex));
                        currentSection.fetch(sectionClassFilterContent.get(selectedIndex).getSectionID(),
                                sectionClassFilterContent.get(selectedIndex).getCourseDept(),
                                sectionClassFilterContent.get(selectedIndex).getCourseNum(),
                                sectionClassFilterContent.get(selectedIndex).getTermID());
                        State.setCurrentSection(currentSection);
                        ViewManager.changeView("Section");
                    }

                } catch (Exception ex) {
                    Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * This method performs advanced course search
     *
     * @throws SQLException
     */
    private void searchCourseByParam() throws SQLException {

        ViewManager.setStatus("");
        if (areFieldsValid()) {
            String searchSQL = validateAndBuildSearchSQL();

            if (searchSQL != null) {
                courseTableContent.clear();
                sectionClassTableContent.clear();

                //fetch the results
                Course.fetch(searchSQL);
                int courseSize = Course.getAll().size();

                if (courseSize > 0) {
                    courseTableContent.addAll(Course.getAll());
                } else {
                    ViewManager.setStatus("There are no results to be displayed!");
                }
                courseFilterContent.clear();
                courseFilterContent.addAll(courseTableContent);
            } else {
                ViewManager.setStatus("Atleast one field should be entered!");
            }
        }
    }

    /**
     * This method validates and builds the advanced search Sql
     *
     * @return
     */
    private String validateAndBuildSearchSQL() {

        ArrayList<String> searchParamList = new ArrayList();
        String searchSQL = "";
        String addParam;
        String searchField;

        searchField = searchCode.getText().trim();
        if (!searchField.isEmpty()) {
            addParam = " courseNum like '%" + searchField + "%'";
            searchParamList.add(addParam);
        }

        searchField = searchDepartment.getText().trim();
        if (!searchField.isEmpty()) {
            addParam = " courseDept like '%" + searchField + "%'";
            searchParamList.add(addParam);
        }

        searchField = searchName.getText().trim();
        if (!searchField.isEmpty()) {
            addParam = " courseName like '%" + searchField + "%'";
            searchParamList.add(addParam);
        }

        searchField = searchInfo.getText().trim();
        if (!searchField.isEmpty()) {
            addParam = " info like '%" + searchField + "%'";
            searchParamList.add(addParam);
        }

        searchField = searchCredit.getText().trim();
        if (!searchField.isEmpty()) {
            addParam = " credit like '%" + searchField + "%'";
            searchParamList.add(addParam);
        }

        if (searchParamList.size() > 0) {
            for (int i = 0; i < searchParamList.size(); i++) {
                if (i != 0) {
                    searchSQL += " and" + searchParamList.get(i);
                } else {
                    searchSQL += searchParamList.get(i);
                }
            }
        } else {
            searchSQL = null;
        }

        return searchSQL;
    }

    /**
     * Validates search fields
     * @return whether all fields have valid input
     */
    private boolean areFieldsValid() {
        Validator validator = new Validator();

        searchCode.setText(validator.validate("Course Code", searchCode.getText(), false, 0, 999, InputType.POSITIVE_INTEGER));
        searchName.setText(validator.validate("Course Name", searchName.getText(), false, 0, 30, InputType.STRING));
        searchDepartment.setText(validator.validate("Department", searchDepartment.getText(), false, 0, 100, InputType.STRING));
        searchInfo.setText(validator.validate("Information", searchInfo.getText(), false, 0, 200, InputType.STRING));
        searchCredit.setText(validator.validate("Credits", searchCredit.getText(), false, 0, 6, InputType.POSITIVE_INTEGER));
        
                
        if (validator.hasErrors()) {
            ViewManager.setStatus(validator.getErrors().get(0).toString());
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Validates course filters
     * @return whether filters have valid input
     */
    private boolean areFilterFieldsValid()
    {
        Validator validator = new Validator();
        courseFilter.setText(validator.validate("Filter", courseFilter.getText(), false, 0, 40, InputType.STRING));
        sectionFilter.setText(validator.validate("Filter", sectionFilter.getText(), false, 0, 40, InputType.STRING));
        
        if (validator.hasErrors()) {
            ViewManager.setStatus(validator.getErrors().get(0).toString());
            return false;
        } else {
            return true;
        }
    }
}
