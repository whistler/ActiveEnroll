package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Section {

    private String sectionID;
    private String courseDept;
    private String courseNum;
    private int instructorID;
    private String type;
    private String textbook;
    private String term;
    private Date startTime;
    private Date endTime;
    private String day;
    private int capacity;
    private int registered;
    private String location;
    private String status;

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
     * @return the instructorID
     */
    public int getInstructorID() {
        return instructorID;
    }

    /**
     * @param instructorID the instructorID to set
     */
    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
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
     * @return the textbook
     */
    public String getTextbook() {
        return textbook;
    }

    /**
     * @param textbook the textbook to set
     */
    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param time the time to set
     */
    public void setStartTime(Date time) {
        this.startTime = time;
    }

    /**
     * @return the time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param time the time to set
     */
    public void setEndTime(Date time) {
        this.endTime = time;
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
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the registered
     */
    public int getRegistered() {
        return registered;
    }

    /**
     * @param registered the registered to set
     */
    public void setRegistered(int registered) {
        this.registered = registered;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Loads the Section by the sectionID from the database and encapsulates
     * into this Section objects
     *
     * @throws SQLException
     */
    public void fetch(String sectionID) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM section  WHERE sectionID = " + sectionID;
        rs = DatabaseConnector.returnQuery(sql);
        if (rs.next()) {
            this.setSectionID(rs.getString("sectionID"));
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setInstructorID(rs.getInt("instructorID"));
            this.setType(rs.getString("type"));
            this.setTextbook(rs.getString("textbook"));
            this.setTerm(rs.getString("term"));
            this.setStartTime(rs.getTime("time"));
            this.setDay(rs.getString("day"));
            this.setCapacity(rs.getInt("capacity"));
            this.setRegistered(rs.getInt("registered"));
            this.setLocation(rs.getString("location"));
            this.setStatus(rs.getString("status"));
        }

    }

    /**
     * Uses the information of this Section to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE section SET "
                + "courseDept=" + this.getCourseDept() + ", "
                + "courseNum=" + this.getCourseNum() + ", "
                + "instructorId=" + this.getInstructorID() + ", "
                + "type=" + this.getType() + ", "
                + "textbook=" + this.getTextbook() + ", "
                + "term=" + this.getTerm() + ", "
                + "startTime=" + this.getStartTime() + ", "
                + "endTime=" + this.getEndTime() + ", "
                + "day=" + this.getDay() + ", "
                + "capacity=" + this.getCapacity() + ", "
                + "registered=" + this.getRegistered() + ", "
                + "location=" + this.getLocation() + ", "
                + "status=" + this.getStatus()
                + "WHERE sectionID=" + this.getSectionID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this Section record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM section WHERE sectionID=" + this.getSectionID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Section into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO section  (sectionID, courseDept, courseNum, instructorID, type, textbook, "
                + "term, startTime, endTime, day, capacity, registered, location, status) values "
                + "(" + this.getSectionID() + ", '"
                + this.getCourseDept() + "', '"
                + this.getCourseNum() + "', '"
                + this.getInstructorID() + "', '"
                + this.getType() + "', '"
                + this.getTextbook() + "', '"
                + this.getTerm() + "', '"
                + this.getStartTime() + "', '"
                + this.getEndTime() + "', '"
                + this.getDay() + "', '"
                + this.getCapacity() + "', '"
                + this.getRegistered() + "', '"
                + this.getLocation() + "', '"
                + this.getStatus()
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
