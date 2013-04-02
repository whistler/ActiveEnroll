package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author chen0910
 */
public class Term {
    private String termID;
    private Date start;
    private Timestamp registrationStart;
    private Timestamp registrationEnd;
    private Timestamp dropWithoutW;
    private Timestamp dropWithW;
    private Date end;

    /**
     * @return the termID
     */
    public String getTermID() {
        return termID;
    }

    /**
     * @param termID the termID to set
     */
    public void setTermID(String term) {
        this.termID = term;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the registrationStart
     */
    public Timestamp getRegistrationStart() {
        return registrationStart;
    }

    /**
     * @param registrationStart the registrationStart to set
     */
    public void setRegistrationStart(Timestamp registrationStart) {
        this.registrationStart = registrationStart;
    }

    /**
     * @return the registrationEnd
     */
    public Timestamp getRegistrationEnd() {
        return registrationEnd;
    }

    /**
     * @param registrationEnd the registrationEnd to set
     */
    public void setRegistrationEnd(Timestamp registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

    /**
     * @return the dropWithoutW
     */
    public Timestamp getDropWithoutW() {
        return dropWithoutW;
    }

    /**
     * @param dropWithoutW the dropWithoutW to set
     */
    public void setDropWithoutW(Timestamp dropWithoutW) {
        this.dropWithoutW = dropWithoutW;
    }

    /**
     * @return the dropWithW
     */
    public Timestamp getDropWithW() {
        return dropWithW;
    }

    /**
     * @param dropWithW the dropWithW to set
     */
    public void setDropWithW(Timestamp dropWithW) {
        this.dropWithW = dropWithW;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }
    
    /**
     * Loads Term with the given termID from the database
     *
     * @param termID termID which the course belongs to
     * @throws SQLException
     */
    public void fetch(String term) throws SQLException {
        String query = "SELECT * FROM term WHERE termID = " + term;
        
        ResultSet rs;
        rs = DatabaseConnector.returnQuery(query);
        
        if (rs.next()) {
            this.termID = rs.getString("termID");
            this.start = rs.getDate("start");
            this.registrationStart = rs.getTimestamp("registrationStart");
            this.registrationEnd = rs.getTimestamp("registrationEnd");
            this.dropWithoutW = rs.getTimestamp("dropWithoutW");
            this.dropWithW = rs.getTimestamp("dropWithW");
            this.end = rs.getDate("end");
        }

    }

    /**
     * Updates the current record in the database
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE term SET "
                + "termID='" + this.termID + "', "
                + "start='" + this.start + "', "
                + "registrationStart='" + this.registrationStart + "', "
                + "registrationEnd='" + this.registrationEnd + "', "
                + "dropWithoutW='" + this.dropWithoutW + "', "
                + "dropWithW='" + this.dropWithW + "', "
                + "end=" + this.end);
    }

    /**
     * Deletes the record from the database
     * @throws SQLException 
     */
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM term "
                + "WHERE termID='" + this.termID);
    }
    
    /**
     * Creates a new record with the database with the properties of this
     * termID
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO term (termID, start, registrationStart, registrationEnd, dropWithoutW,dropWithW,end) "
                + " values ('"
                + this.termID + "', '"
                + this.start + "', '"
                + this.registrationStart + "', "
                + this.registrationEnd + ", '"
                + this.dropWithoutW + ", '"
                + this.dropWithW + ", '"
                + this.end + "')";

        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
   
}