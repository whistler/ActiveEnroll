package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {

    private String deptID;
    private String deptName;
    private String facultyName;

    public String getDeptID() {;
        return this.deptID;
    }

    public void setDeptID(String deptID) {;
        this.deptID = deptID;
    }

    public String getDeptName() {;
        return this.deptName;
    }

    public void setDeptName(String deptName) {;
        this.deptName = deptName;
    }

    public String getFacultyName() {;
        return this.facultyName;
    }

    public void setFacultyName(String facultyName) {;
        this.facultyName = facultyName;
    }
    
    /**
     * Loads Department with the given department number from the database
     *
     * @param deptID id of the department to load from 
     * @throws SQLException
     */
    public void fetch(int deptID) throws SQLException {
        String query = "SELECT * FROM department WHERE deptID = " + deptID;
        
        ResultSet rs;
        rs = DatabaseConnector.returnQuery(query);
        
        if (rs.next()) {
            this.deptID = rs.getString("deptID");
            this.deptName = rs.getString("courseName");
            this.facultyName = rs.getString("facultyName");
        }

    }

    /**
     * Updates the current record in the database
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE department SET "
                + "deptID='" + this.deptID + "', "
                + "deptName='" + this.deptName + "', "
                + "facultyName='" + this.facultyName + "'");
    }

    /**
     * Deletes the record from the database
     * @throws SQLException 
     */
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM department "
                + "WHERE deptID='" + this.deptID);
    }
    
    /**
     * Creates a new record with the database with the properties of this
     * Department
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO department (deptID, deptName, facultyName) "
                + " values ('"
                + this.deptID + "', '"
                + this.deptName + "', '"
                + this.facultyName + "')";

        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
