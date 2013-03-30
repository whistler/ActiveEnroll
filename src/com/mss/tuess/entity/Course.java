package com.mss.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {

    private String courseDept;
    private String courseNum;
    private String courseName;
    private String info;
    private int credit;

    public String getCourseDept() {;
        return this.courseDept;
    }

    public void setCourseDept(String courseDept) {;
        this.courseDept = courseDept;
    }

    public String getCourseNum() {;
        return this.courseNum;
    }

    public void setCourseNum(String courseNum) {;
        this.courseNum = courseNum;
    }

    public String getCourseName() {;
        return this.courseName;
    }

    public void setCourseName(String courseName) {;
        this.courseName = courseName;
    }

    public String getInfo() {;
        return this.info;
    }

    public void setInfo(String info) {;
        this.info = info;
    }

    public int getCredit() {;
        return this.credit;
    }

    public void setCredit(int credit) {;
        this.credit = credit;
    }

    /**
     * Loads Course with the given course department and course number from the database
     *
     * @param department department which the course belongs to
     * @param courseNumer number for the course to load
     * @throws SQLException
     */
    public void fetch(String department, String courseNumber) throws SQLException {
        String query = "SELECT * FROM course WHERE courseDept = '" 
                + department + "' AND courseNum = '" + courseNumber + "'";
        
        ResultSet rs;
        rs = DatabaseConnector.returnQuery(query);
        
        if (rs.next()) {
            this.courseDept = rs.getString("courseDept");
            this.courseName = rs.getString("courseName");
            this.courseNum = rs.getString("courseNum");
            this.credit = rs.getInt("credit");
            this.info = rs.getString("info");
        }

    }

    /**
     * Updates the current record in the database
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE course SET "
                + "courseDept='" + this.courseDept + "', "
                + "courseNum='" + this.courseNum + "', "
                + "courseName='" + this.courseName + "', "
                + "info='" + this.info + "', "
                + "credit=" + this.credit);
    }

    /**
     * Deletes the record from the database
     * @throws SQLException 
     */
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM course "
                + "WHERE courseDept='" + this.courseDept 
                + "' AND courseNum = '" + this.courseNum + "'");
    }
    
    /**
     * Creates a new record with the database with the properties of this
     * Course
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO course (courseDept, courseNum, courseName, credit, info) "
                + " values ('"
                + this.courseDept + "', '"
                + this.courseNum + "', '"
                + this.courseName + "', "
                + this.credit + ", '"
                + this.info + "')";

        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
