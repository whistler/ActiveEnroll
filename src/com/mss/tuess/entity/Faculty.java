package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Faculty class
 */
public class Faculty {
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

    private String facultyName;

    /**
     * @return the facultyName
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * @param facultyName the facultyName to set
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * Loads the Faculty by the facultyName from the database and encapsulates
     * into this Faculty objects
     * @param facultyName the faculty used to query
     * @throws SQLException
     */
    public void fetch(String facultyName) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM faculty WHERE facultyName = " + facultyName;
        rs = DatabaseConnector.returnQuery(sql);
        if (rs.next()) {
            this.setFacultyName(rs.getString("facultyName"));

        }

    }

    /**
     * Uses the information of this Faculty to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE faculty SET facultyName=" + this.getFacultyName()
                + "WHERE facultyName=" + this.getFacultyName();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this Faculty record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM faculty WHERE facultyName=" + this.getFacultyName();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Faculty into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO faculty  (facultyName, "
                + "(" + this.getFacultyName()
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}