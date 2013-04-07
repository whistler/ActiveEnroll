/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

import java.sql.Timestamp;

/**
 *
 * @author chenliang
 */
public class SectionClass {
    private String sectionID;
    private String courseDept;
    private String courseNum;
    private String termID;
    
    private String type;
    private String classID;
    
    private String day;
    private Timestamp startTime;
    private Timestamp endTime;
    
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
}
