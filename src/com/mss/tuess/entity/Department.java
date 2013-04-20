package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Department class
 */
public class Department {
    private static ArrayList<Department> departments = new ArrayList();

    /**
     * Loads all Department records from the database in to a list of Department.
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM department";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Department department = new Department();
            department.setDeptID(rs.getString("deptID"));
            department.setDeptName(rs.getString("courseName"));
            department.setFacultyName(rs.getString("facultyName"));
            departments.add(department);
        }
    }

    /**
     * Returns the department stored at the given index
     *
     * @param index index of the department to return
     * @return Department object at position index
     */
    public static Department get(int index) {
        return departments.get(index);
    }

    private String deptID;
    private String deptName;
    private String facultyName;

    /**
     * Returns the department ID.
     * @return the deptID the ID of the department
     */
    public String getDeptID() {
        return deptID;
    }

    /**
     * Sets the department ID.
     * @param deptID the deptID to set
     */
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    /**
     * Returns the department name.
     * @return the deptName the name of the department
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * Sets the name of the department.
     * @param deptName the deptName to set
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * Returns the name of the faculty.
     * @return the facultyName the name of the faculty
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * Sets the name of the faculty
     * @param facultyName the facultyName to set
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * Loads Department with the given department number from the database.
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
     * Updates the current record in the database.
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
     * Deletes the record from the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM department "
                + "WHERE deptID='" + this.getDeptID());
    }

    /**
     * Creates a new record with the database with the properties of this
     * Department.
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
