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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private Button searchCourseButton;
    @FXML
    private Label searchErrorLabel;
    
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
        if(State.getCurrentSearchView().equalsIgnoreCase("AllCourses")){
            CourseList.fetch();
            courseSize = CourseList.getAll().size();
            
            if(courseSize > 0){
                courseTableContent.addAll(CourseList.getAll());
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
    private void advancedCourseSearch() throws SQLException{
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
                        System.out.println("Index : "+selectedIndex);

                        try {
                            String courseNum = courseFilterContent.get(selectedIndex).getCourseNum();
                            String courseDept = courseFilterContent.get(selectedIndex).getCourseDept();
                            String currentTerm = State.getCurrentTerm().getTermID();
                            SearchSectionClassList.fetch(courseDept, courseNum, currentTerm);
                            
                            sectionClassTableContent.clear();
                            sectionClassTableContent.addAll(SearchSectionClassList.getAll());
                            sectionClassFilterContent.clear();
                            sectionClassFilterContent.addAll(sectionClassTableContent);
                        } catch (Exception ex) {
                            System.out.println("gotcha array out of bound 1");
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
                        
                        System.out.println("Index : "+selectedIndex);

                        try {
                            State.setCurrentSectionClass(sectionClassFilterContent.get(selectedIndex));
                            currentSection.fetch(sectionClassFilterContent.get(selectedIndex).getSectionID(),
                                sectionClassFilterContent.get(selectedIndex).getCourseDept(),
                                sectionClassFilterContent.get(selectedIndex).getCourseNum(),
                                sectionClassFilterContent.get(selectedIndex).getTermID());
                            State.setCurrentSection(currentSection);
                            ViewManager.changeView("/com/mss/tuess/views/Section.fxml");
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
    
    
    
    private void searchCourseByParam() throws SQLException{
            
            searchErrorLabel.setText("");
            String searchSQL = validateAndBuildSearchSQL();
            if(searchSQL != null){
                System.out.println("Search Sql :"+searchSQL);
                courseTableContent.clear();
                sectionClassTableContent.clear();

                CourseList.fetch(searchSQL);
                int courseSize = CourseList.getAll().size();

                if(courseSize > 0){
                    courseTableContent.addAll(CourseList.getAll());    
                }
                
                courseFilterContent.clear();
                courseFilterContent.addAll(courseTableContent);
            }else{
                searchErrorLabel.setText("Atleast one field should be entered!");
            }


    }
    
    private String validateAndBuildSearchSQL(){
        
        ArrayList<String> searchParamList = new ArrayList();
        String searchSQL="";
        String addParam;
        String searchField;
        
        searchField = searchCode.getText().trim();
        if(!searchField.isEmpty()){
            addParam = " courseNum like '%"+searchField+"%'";
            searchParamList.add(addParam);
        }

        searchField = searchDepartment.getText().trim();
        if(!searchField.isEmpty()){
            addParam = " courseDept like '%"+searchField+"%'";
            searchParamList.add(addParam);
        }
        
        searchField = searchName.getText().trim();
        if(!searchField.isEmpty()){
            addParam = " courseName like '%"+searchField+"%'";
            searchParamList.add(addParam);
        }
        
        searchField = searchInfo.getText().trim();
        if(!searchField.isEmpty()){
            addParam = " info like '%"+searchField+"%'";
            searchParamList.add(addParam);
        }
        
        searchField = searchCredit.getText().trim();
        if(!searchField.isEmpty()){
            addParam = " credit like '%"+searchField+"%'";
            searchParamList.add(addParam);
        }  
        
        if(searchParamList.size() > 0){
            for(int i=0;i<searchParamList.size();i++){
                if(i!=0){
                    searchSQL+=" and" + searchParamList.get(i);
                }else{
                    searchSQL+=searchParamList.get(i);
                }
            }   
        }else {
            searchSQL = null;
        }
        
        return searchSQL;
    }
    
}
