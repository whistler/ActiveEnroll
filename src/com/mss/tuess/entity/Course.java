package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Course class. Course object stores information of courseDept, courseNum , courseName, info and credit of a course offered by the university.
 */
public class Course {
    private static ArrayList<Course> courses = new ArrayList();

    /**
     * Parameterized Search for a course.
     * @param searchParam to be searched with
     * @throws SQLException
     */
    public static void fetch(String searchParam) throws SQLException {
        String parameterizedSQL = "SELECT * FROM course where" + searchParam + "order by courseNum;";
        executeSQL(parameterizedSQL);
    }

    /**
     * Loads all Course records from the database into an arrayList.
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
     * Returns the course stored at the given index.
     * @param index index of the course to return
     * @return Course object at position index
     */
    public static Course get(int index) {
        return courses.get(index);
    }

    /**
     * Executs the writen SQL in the database.
     * @param sqlString the SQL to be executed
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
        return courses;
    }

    private String courseDept;
    private String courseNum;
    private String courseName;
    private String info;
    private int credit;

    /**
     * Returns the department of the course.
     * @return this.courseDept the department of the course
     */
    public String getCourseDept() {
        return this.courseDept;
    }

    /**
     * Sets the department of the course.
     * @param courseDept the department to set
     */
    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }

    /**
     * Returns the number of the course.
     * @return this.courseNum the number of the course
     */
    public String getCourseNum() {
        return this.courseNum;
    }

    /**
     * Sets the number of the course.
     * @param courseNum to set
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }
    
    /**
     * Returns the name of the course
     * @return this.courseName the name of the course
     */
    public String getCourseName() {
        return this.courseName;
    }

    /**
     * Sets the name of the course
     * @param courseName the name of the course to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the info of the course
     * @return this.info the info of the course
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * Sets the info of the course
     * @param info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Returns the credit of a course
     * @return this.credit the credit of the course
     */
    public int getCredit() {
        return this.credit;
    }

    /**
     * Sets the credit of a course
     * @param credit to 
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }

    /**
     * Loads Course with the given course department and course number from the database.
     *
     * @param courseDepartment department which the course belongs to
     * @param courseNumber number for the course to load
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
     * Updates the current course record in the database.
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
     * Deletes the course record from the database.
     * @throws SQLException 
     */
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM course "
                + "WHERE courseDept='" + this.courseDept 
                + "' AND courseNum = '" + this.courseNum + "'");
    }
    
    /**
     * Creates a new course record in the  database with the properties of this
     * Course.
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

        DatabaseConnector.updateQuery(sql);
    }
}
