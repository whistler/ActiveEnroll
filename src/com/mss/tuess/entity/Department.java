package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Department {

    private String deptID;
    private String deptName;
    private String facultyName;

    /**
     * @return the deptID
     */
    public String getDeptID() {
        return deptID;
    }

    /**
     * @param deptID the deptID to set
     */
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    /**
     * @return the deptName
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName the deptName to set
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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
     * Loads Department with the given department number from the database
     *
     * @param deptID id of the department to load from
     * @throws SQLException
     */
    public void fetch(String deptID) {
        try {
            String query = "SELECT * FROM department WHERE deptID = '" + deptID + "'";

            ResultSet rs;
            rs = DatabaseConnector.returnQuery(query);

            if (rs.next()) {
                this.setDeptID(rs.getString("deptID"));
                this.setDeptName(rs.getString("deptName"));
                this.setFacultyName(rs.getString("facultyName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Updates the current record in the database
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE department SET "
                + "deptID='" + this.getDeptID() + "', "
                + "deptName='" + this.getDeptName() + "', "
                + "facultyName='" + this.getFacultyName() + "'");
    }

    /**
     * Deletes the record from the database
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM department "
                + "WHERE deptID='" + this.getDeptID());
    }

    /**
     * Creates a new record with the database with the properties of this
     * Department
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO department (deptID, deptName, facultyName) "
                + " values ('"
                + this.getDeptID() + "', '"
                + this.getDeptName() + "', '"
                + this.getFacultyName() + "')";

        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
