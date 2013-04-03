package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

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

       /**
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     *
     * @throws SQLException
     */
    
    public void fetch(String courseDept, String courseNum,String prereqNum, String prereqDept) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM prerequisite "
                + "WHERE  courseDept = " + courseDept + "AND courseNum = " + courseNum + "AND prereqNum = " + prereqNum + "AND prereqDept = " + prereqDept;
        rs = DatabaseConnector.returnQuery(sql);
        if (rs.next()) {
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setPrereqNum(rs.getString("prereqNum"));
            this.setPrereqDept(rs.getString("prereqDept"));
        }

    }



    /**
     * Delete this Prerequisite record in the database.
     *
     * @throws SQLException
     */
   
    public void delete() throws SQLException {
        String sql = "DELETE FROM prerequisite WHERE courseDept=" + this.getCourseDept()+ ", courseNum=" + this.getCourseNum()+ ", prereqDept=" + this.getPrereqDept()+ ", prereqNum=" + this.getPrereqNum();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Prerequisite into the database.
     *
     * @throws SQLException
     */
    
    public void insert() throws SQLException {
        String sql = "INSERT INTO prereqisite  (courseDept, courseNum, prereqDept, prereqNum) values "
                + "(" + this.getCourseDept() + ", '" + this.getCourseNum() + "', '" + this.getPrereqDept() + "', '" + this.getPrereqNum() 
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
