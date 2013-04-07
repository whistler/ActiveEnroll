package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student extends User {

    private int studentID;
    private String programID;
    private String registeredSince;
    private String status;

    /**
     * @return the studentID
     */
    @Override
    public int getID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    @Override
    public void setID(int studentID) {
        this.studentID = studentID;
    }

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
     * @return the registeredSince
     */
    public String getRegisteredSince() {
        return registeredSince;
    }

    /**
     * @param registeredSince the registeredSince to set
     */
    public void setRegisteredSince(String registeredSince) {
        this.registeredSince = registeredSince;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     *
     * @throws SQLException
     */
    @Override
    public void fetch(int stuId){
        try {
            ResultSet rs;
            String sql = "SELECT * FROM student stu WHERE stu.studentID = " + stuId;
            rs = DatabaseConnector.returnQuery(sql);
            if (rs.next()) {
                this.setID(rs.getInt("studentID"));
                this.setFirstName(rs.getString("firstName"));
                this.setLastName(rs.getString("lastName"));
                this.setAddress(rs.getString("address"));
                this.setCity(rs.getString("city"));
                this.setCountry(rs.getString("country"));
                this.setZipcode(rs.getString("zipcode"));
                this.setPhone(rs.getString("phone"));
                this.setProgramID(rs.getString("programID"));
                this.setRegisteredSince(rs.getString("registeredSince"));
                this.setStatus(rs.getString("status"));
                this.setPassword(rs.getString("password"));
                this.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Uses the information of this Student to update the record in the
     * database.
     *
     * @throws SQLException
     */
    @Override
    public void update() throws SQLException {
        String sql = "UPDATE student SET "
                + "firstName='" + this.getFirstName() + "', "
                + "lastName='" + this.getLastName() + "', "
                + "address='" + this.getAddress() + "', "
                + "city='" + this.getCity() + "', "
                + "country='" + this.getCountry() + "', "
                + "zipcode='" + this.getZipcode() + "', "
                + "phone='" + this.getPhone() + "', "
                + "programID='" + this.getProgramID() + "', "
                + "registeredSince='" + this.getRegisteredSince() + "', "
                + "status='" + this.getStatus() + "', "
                + "password='" + this.getPassword() + "', "
                + "email='" + this.getEmail() + "' "
                + "WHERE studentID=" + this.getID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this Student record in the database.
     *
     * @throws SQLException
     */
    @Override
    public void delete() throws SQLException {
        String sql = "DELETE FROM student WHERE studentID=" + this.getID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Student into the database.
     *
     * @throws SQLException
     */
    @Override
    public void insert() throws SQLException {
        String sql = "INSERT INTO student  (studentID, firstName, lastName, address, city, country, "
                + "zipcode, phone, programID, registeredSince, status, password, email) values "
                + "(" + this.getID() + ", '" + this.getFirstName() + "', '" + this.getLastName() + "', '" + this.getAddress() + "', '" + this.getCity() + "', '" + this.getCountry()
                + "', '" + this.getZipcode() + "', '" + this.getPhone() + "', '" + this.getProgramID() + "', '" + this.getRegisteredSince() + "', '" + this.getStatus() + "', '" + this.getPassword() + "', '" + this.getEmail()
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
    
    /**
     * Returns number of credits completed by the Student
     * @return number of credits completed
     */
    public int getCreditsCompleted()
    {
        try {
            ResultSet rs;
            String sql = "SELECT SUM(credit) AS credits FROM " +
                            "(SELECT DISTINCT c.courseDept, c.courseNum, c.credit " +
                            "FROM enrollSection es, section s, course c " +
                            "WHERE c.courseDept = s.courseDept AND " +
                            "c.courseNum = s.courseNum AND " +
                            "es.courseDept = s.courseDept AND " +
                            "es.courseNum = s.courseNum AND " +
                            "es.termID = s.termID AND " +
                            "grade NOT IN ('W','F') AND " +
                            "es.studentID = '" + this.studentID + "'" +
                         ") AS T;";
            rs = DatabaseConnector.returnQuery(sql);
            if (rs.next()) {
                return rs.getInt("credits");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
