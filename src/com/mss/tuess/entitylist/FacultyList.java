/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Faculty;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Prabal
 */
public class FacultyList {

    private static ArrayList<Faculty> faculties = new ArrayList();

    /**
     * Loads all Faculty records from the database in to a list of Faculty
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM section";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Faculty faculty = new Faculty();
            faculty.setFacultyName(rs.getString("facultyName"));

            faculties.add(faculty);
        }
    }

    /**
     * Returns the faculty stored at the given index
     *
     * @param index index of the section to return
     * @return Faculty object at position index
     */
    public static Faculty get(int index) {
        return faculties.get(index);
    }
}
