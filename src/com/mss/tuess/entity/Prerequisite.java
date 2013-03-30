package com.mss.entity;

public class Prerequisite {

    private String courseDept;
    private String courseNum;
    private String prereqDept;
    private String prereqNum;

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
     * @return the prereqDept
     */
    public String getPrereqDept() {
        return prereqDept;
    }

    /**
     * @param prereqDept the prereqDept to set
     */
    public void setPrereqDept(String prereqDept) {
        this.prereqDept = prereqDept;
    }

    /**
     * @return the prereqNum
     */
    public String getPrereqNum() {
        return prereqNum;
    }

    /**
     * @param prereqNum the prereqNum to set
     */
    public void setPrereqNum(String prereqNum) {
        this.prereqNum = prereqNum;
    }

   
}
