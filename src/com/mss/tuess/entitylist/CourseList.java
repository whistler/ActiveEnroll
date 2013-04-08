package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Course;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseList {

    private static ArrayList<Course> courses = new ArrayList();

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
     * Parameterized Search
     * objects
     *
     * @throws SQLException
     */
    public static void fetch(String searchParam) throws SQLException {
        
        String parameterizedSQL = "SELECT * FROM course where"
                                        +searchParam
                                        +"order by courseNum;";
        executeSQL(parameterizedSQL);
    }   
    
    /**
     * 
     * @param sqlString
     * @throws SQLException 
     */
    private static void executeSQL(String sqlString) throws SQLException{
        
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
     * Returns the course stored at the given index
     *
     * @param index index of the course to return
     * @return Course object at position index
     */
    public static Course get(int index) {
        return courses.get(index);
    } 
    /**
     * Returns the course List
     *
     * @return ArrayList<Course> Course ArrayList
     */
    public static ArrayList<Course> getAll() {
        System.out.println(courses.get(1).getCourseName());
        return courses;
    }
}

