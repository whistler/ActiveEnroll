/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Course;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Xin
 */

public class CourseList {

    private static ArrayList<Course> courses = new ArrayList();

    /**
     * Loads all Course records from the database in to a list of Course
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM course";
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
}

