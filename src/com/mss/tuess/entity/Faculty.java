package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Faculty {

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
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     *
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
     * Uses the information of this Student to update the record in the
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
     * Delete this Student record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM faculty WHERE facultyName=" + this.getFacultyName();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Student into the database.
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
