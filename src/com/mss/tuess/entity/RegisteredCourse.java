/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;


/**
 *
 * @author chenliang
 */
public class RegisteredCourse {
    
    private String courseDept;
    private String courseNum;
    private String sectionID;
    private String courseName;
    private String termID;
    private String day;
    private String type;
    private int credit;

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
     * @return the section
     */
    public String getSectionID() {
        return sectionID;
    }

    /**
     * @param section the section to set
     */
    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
     * @return the days
     */
    public String getDay() {
        return day;
    }

    /**
     * @param days the days to set
     */
    public void setDay(String day) {
        this.day = day;
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
     * @return the credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }
}
