/**
 *
 * @author wwh
 */
package com.mss.tuess.entity;

import com.mss.tuess.start.TUESS;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {

    private int studentID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String zipcode;
    private String phone;
    private String programID;
    private String registeredSince;
    private String status;
    private String password;

    /**
     * @return the studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     *
     * @throws SQLException
     */
    public void fetch(int stuId) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM student stu WHERE stu.studentID = " + stuId;
        rs = DatabaseConnector.returnQuery(sql);
        if (rs.next()) {
            this.setStudentID(rs.getInt("studentID"));
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
        }

    }

    /**
     * Uses the information of this Student to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE student SET firstName=" + this.getFirstName()
                + ", lastName=" + this.getLastName() + ", address=" + this.getAddress()
                + ", city=" + this.getCity() + ", country=" + this.getCountry()
                + ", zipcode=" + this.getZipcode() + ", phone=" + this.getPhone()
                + ", programID=" + this.getProgramID() + ", registeredSince=" + this.getRegisteredSince()
                + ", status=" + this.getStatus() + ", password=" + this.getPassword()
                + "WHERE studentID=" + this.getStudentID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this Student record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM student WHERE studentID=" + this.getStudentID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Student into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO student  (studentID, firstName, lastName, address, city, country, "
                + "zipcode, phone, programID, registeredSince, status, password) values "
                + "(" + this.getStudentID() + ", '" + this.getFirstName() + "', '" + this.getLastName() + "', '" + this.getAddress() + "', '" + this.getCity() + "', '" + this.getCountry()
                + "', '" + this.getZipcode() + "', '" + this.getPhone() + "', '" + this.getProgramID() + "', '" + this.getRegisteredSince() + "', '" + this.getStatus() + "', '" + this.getPassword()
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
