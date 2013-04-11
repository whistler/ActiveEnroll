package com.mss.tuess.entity;

import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.State;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SectionClass {
    private static ArrayList<SectionClass> sectionClassList = new ArrayList();
    private static ArrayList<SectionClass> registeredSectionClassList = new ArrayList();

    public static void fetch(String courseDept, String courseNum, String currentTerm) throws SQLException {
        String selectSectionClassListByCourse = "SELECT * FROM sectionClass" + " where" + " courseDept = '" + courseDept + "' and courseNum = '" + courseNum + "' and termID = '" + currentTerm + "'";
        executeFetch(selectSectionClassListByCourse);
    }

    /**
     * Loads all Section records from the database in to a list of Section
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        String selectAll = "SELECT * FROM sectionClass";
        executeFetch(selectAll);
    }

    /**
     * Returns the section stored at the given index
     *
     * @param index index of the section to return
     * @return Section object at position index
     */
    public static SectionClass get(int index) {
        return sectionClassList.get(index);
    }

    private static void executeFetch(String sql) throws SQLException {
        sectionClassList.clear();
        ResultSet rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            SectionClass sectionClass = new SectionClass();
            sectionClass.setSectionID(rs.getString("sectionID"));
            sectionClass.setCourseDept(rs.getString("courseDept"));
            sectionClass.setCourseNum(rs.getString("courseNum"));
            sectionClass.setTermID(rs.getString("termID"));
            sectionClass.setType(rs.getString("type"));
            sectionClass.setClassID(rs.getString("classID"));
            sectionClass.setDay(rs.getString("day"));
            sectionClass.setStartTime(rs.getTimestamp("startTime"));
            sectionClass.setEndTime(rs.getTimestamp("endTime"));
            sectionClass.setLocation(rs.getString("location"));
            sectionClassList.add(sectionClass);
        }
    }

    /**
     * Returns the sections List
     *
     * @return ArrayList<Section> sections ArrayList
     */
    public static ArrayList<SectionClass> getAll() {
        return sectionClassList;
    }

    public static void fetchregisteredSectionClassList() throws SQLException {
        registeredSectionClassList.clear();
        int currentID;
        Term currentTerm = State.getCurrentTerm();
        currentID = CurrentUser.getUser().getID();
        ResultSet rs;
        String sql = "select sc.sectionID,sc.courseDept,sc.courseNum,sc.termID,sc.type,sc.classID,sc.day,sc.startTime," + "sc.endTime, sc.location from sectionClass sc natural join enrollSection es " + "where es.studentID=" + currentID + " and es.termID='" + currentTerm.getTermID() + "'";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            SectionClass registeredSectionClass = new SectionClass();
            registeredSectionClass.setSectionID(rs.getString("sectionID"));
            registeredSectionClass.setCourseDept(rs.getString("courseDept"));
            registeredSectionClass.setCourseNum(rs.getString("courseNum"));
            registeredSectionClass.setTermID(rs.getString("termID"));
            registeredSectionClass.setType(rs.getString("type"));
            registeredSectionClass.setClassID(rs.getString("classID"));
            registeredSectionClass.setDay(rs.getString("day"));
            registeredSectionClass.setStartTime(rs.getTimestamp("startTime"));
            registeredSectionClass.setEndTime(rs.getTimestamp("endTime"));
            registeredSectionClass.setLocation(rs.getString("location"));
            registeredSectionClassList.add(registeredSectionClass);
        }
    }

    public static ArrayList<SectionClass> getAllregisteredSectionClassList() {
        return registeredSectionClassList;
    }
    
    private String sectionID;
    private String courseDept;
    private String courseNum;
    private String termID;
    
    private String type;
    private String classID;
    
    private String day;
    private Timestamp startTime;
    private String displayStartTime;
    private Timestamp endTime;
    private String displayEndTime;
    
    private String location;

    /**
     * @return the sectionID
     */
    public String getSectionID() {
        return sectionID;
    }

    /**
     * @param sectionID the sectionID to set
     */
    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    /**
     * @return the courseDept
     */
    public String getCourseDept() {
        return courseDept;
    }

    /**
     * @param courseDept the courseDept to set
     */
    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }

    /**
     * @return the courseNum
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * @param courseNum the courseNum to set
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    /**
     * @return the termID
     */
    public String getTermID() {
        return termID;
    }

    /**
     * @param termID the termID to set
     */
    public void setTermID(String termID) {
        this.termID = termID;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the classID
     */
    public String getClassID() {
        return classID;
    }

    /**
     * @param classID the classID to set
     */
    public void setClassID(String classID) {
        this.classID = classID;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the startTime
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        //set the start time to be displayed
        this.displayStartTime = this.startTime.toString().substring(11, 16);
    }

    /**
     * @return the endTime
     */
    public Timestamp getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        //set the end time to be displayed
        this.displayEndTime = this.endTime.toString().substring(11, 16);        
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * @param startTime the display start time to set
     */
    public void setDisplayStartTime(String startTime) {
        this.displayStartTime = startTime;
    }

    /**
     * @return the startTime
     */
    public String getDisplayStartTime() {
        return this.displayStartTime;
    }    
    
    /**
     * @param endTime the display start time to set
     */
    public void setDisplayEndTime(String endTime) {
        this.displayEndTime = endTime;
    }

    /**
     * @return the Display End Time
     */
    public String getDisplayEndTime() {
        return this.displayEndTime;
    }
    
    /*
    public static ArrayList<SectionClass> fetch(Section section) throws SQLException
    {
        ResultSet rs;
        
        String sql="SELECT * FROM sectionClass WHERE "
                + "sectionID = ? AND "
                + "courseDept = ? AND "
;
        
        rs = DatabaseConnector.returnQuery(sql);

        
        
        if (rs.next()) {
            this.setSectionID(rs.getString("sectionID"));
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setTermID(rs.getString("termID"));
            this.setType(rs.getString("type"));
            this.setClassID(rs.getString("classID"));
            this.setDay(rs.getString("day"));
            this.setStartTime(rs.getTimestamp("startTime"));
            this.setEndTime(rs.getTimestamp("endTime"));
            this.setLocation(rs.getString("location"));
        }
    }*/
}
