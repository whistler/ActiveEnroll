package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {
    private static ArrayList<Course> courses = new ArrayList();

    /**
     * Parameterized Search
     * objects
     *
     * @throws SQLException
     */
    public static void fetch(String searchParam) throws SQLException {
        String parameterizedSQL = "SELECT * FROM course where" + searchParam + "order by courseNum;";
        executeSQL(parameterizedSQL);
    }

    /**
     * Loads all Course records from the database in to a list of Course
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        courses.clear();
        ResultSet rs;
        String sql = "SELECT * FROM course order by courseNum";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Course course = new Course();
            course.setCourseDept(rs.getString("courseDept"));
            course.setCourseNum(rs.getString("courseNum"));
            course.setCourseName(rs.getString("courseName"));
            course.setInfo(rs.getString("info"));
            course.setCredit(rs.getInt("credit"));
            courses.add(course);
        }
    }

    /**
     * Returns the course stored at the given index
     *
     * @param index index of the course to return
     * @return Course object at position index
     */
    public static Course get(int index) {
        return courses.get(index);
    }

    /**
     *
     * @param sqlString
     * @throws SQLException
     */
    private static void executeSQL(String sqlString) throws SQLException {
        courses.clear();
        ResultSet rs;
        rs = DatabaseConnector.returnQuery(sqlString);
        while (rs.next()) {
            Course course = new Course();
            course.setCourseDept(rs.getString("courseDept"));
            course.setCourseNum(rs.getString("courseNum"));
            course.setCourseName(rs.getString("courseName"));
            course.setInfo(rs.getString("info"));
            course.setCredit(rs.getInt("credit"));
            courses.add(course);
        }
    }

    /**
     * Returns the course List
     *
     * @return ArrayList<Course> Course ArrayList
     */
    public static ArrayList<Course> getAll() {
        //System.out.println(courses.get(1).getCourseName());
        return courses;
    }

    private String courseDept;
    private String courseNum;
    private String courseName;
    private String info;
    private int credit;

    public String getCourseDept() {
        return this.courseDept;
    }

    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }

    public String getCourseNum() {
        return this.courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCredit() {
        return this.credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    /**
     * Loads Course with the given course department and course number from the database
     *
     * @param department department which the course belongs to
     * @param courseNumer number for the course to load
     * @throws SQLException
     */
    public void fetch(String courseDepartment, String courseNumber) throws SQLException {
        String query = "SELECT * FROM course WHERE courseDept = '" 
                + courseDepartment + "' AND courseNum = '" + courseNumber + "'";
        
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
