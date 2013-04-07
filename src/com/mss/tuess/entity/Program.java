package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {

    private String programID;
    private String deptID;
    private String degreeTitle;
    private int minCredit;
    private int maxLength;

    /**
     * @return the programID
     */
    public String getProgramID() {
        return programID;
    }

    /**
     * @param programID the programID to set
     */
    public void setProgramID(String programID) {
        this.programID = programID;
    }

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
     * @return the degree
     */
    public String getDegreeTitle() {
        return degreeTitle;
    }

    /**
     * @param degree the degree to set
     */
    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    /**
     * @return the minCredit
     */
    public int getMinCredit() {
        return minCredit;
    }

    /**
     * @param minCredit the minCredit to set
     */
    public void setMinCredit(int minCredit) {
        this.minCredit = minCredit;
    }

    /**
     * @return the maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * @param maxLength the maxLength to set
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }


  /**
     * Loads the Program by the programID from the database and encapsulates
     * into this Program objects
     *
     * @throws SQLException
     */
    public void fetch(String programID) {
        try {
            ResultSet rs;
            String sql = "SELECT * FROM program WHERE programID = '" + programID + "'";
            rs = DatabaseConnector.returnQuery(sql);
            if (rs.next()) {
                this.setProgramID(rs.getString("programID"));
                this.setDeptID(rs.getString("deptID"));
                this.setDegreeTitle(rs.getString("degreeTitle"));
                this.setMinCredit(rs.getInt("minCredit"));
                this.setMaxLength(rs.getInt("maxLength"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Uses the information of this Program to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE program SET programID=" + this.getProgramID()
                + ", deptID=" + this.getDeptID() 
                + ", degreeTitle=" + this.getDegreeTitle()
                + ", minCredit=" + this.getMinCredit()
                + ", maxLength=" + this.getMaxLength()
                + "WHERE programID=" + this.getProgramID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this Program record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM program WHERE programID=" + this.getProgramID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Program into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO student  (programID,deptID,degreeTitle,minCredit,maxLength) values "
                + "(" + this.getProgramID() + ", " + this.getDeptID()+ ", " + this.getDegreeTitle()+","
                + this.getMinCredit() + ", " + this.getMaxLength()
                + ")";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }

}