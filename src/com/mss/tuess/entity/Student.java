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

    public int getStudentID() {;
        return this.studentID;
    }

    public void setStudentID(int studentID) {;
        this.studentID = studentID;
    }

    public String getFirstName() {;
        return this.firstName;
    }

    public void setFirstName(String firstName) {;
        this.firstName = firstName;
    }

    public String getLastName() {;
        return this.lastName;
    }

    public void setLastName(String lastName) {;
        this.lastName = lastName;
    }

    public String getAddress() {;
        return this.address;
    }

    public void setAddress(String address) {;
        this.address = address;
    }

    public String getCity() {;
        return this.city;
    }

    public void setCity(String city) {;
        this.city = city;
    }

    public String getCountry() {;
        return this.country;
    }

    public void setCountry(String country) {;
        this.country = country;
    }

    public String getZipcode() {;
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {;
        this.zipcode = zipcode;
    }

    public String getPhone() {;
        return this.phone;
    }

    public void setPhone(String phone) {;
        this.phone = phone;
    }

    public String getProgramID() {;
        return this.programID;
    }

    public void setProgramID(String programID) {;
        this.programID = programID;
    }

    public String getRegisteredSince() {;
        return this.registeredSince;
    }

    public void setRegisteredSince(String registeredSince) {;
        this.registeredSince = registeredSince;
    }

    public String getStatus() {;
        return this.status;
    }

    public void setStatus(String status) {;
        this.status = status;
    }

    public String getPassword() {;
        return this.password;
    }

    public void setPassword(String password) {;
        this.password = password;
    }

    public void fetch(int stuId) throws SQLException {
        ResultSet rs;
        rs = DatabaseConnector.returnQuery("SELECT * FROM student stu WHERE stu.studentID = " + stuId);
        if (rs.next()) {
            this.studentID = rs.getInt("studentID");
            this.firstName = rs.getString("firstName");
            this.lastName = rs.getString("lastName");
            this.address = rs.getString("address");
            this.city = rs.getString("city");
            this.country = rs.getString("country");
            this.zipcode = rs.getString("zipcode");
            this.phone = rs.getString("phone");
            this.programID = rs.getString("programID");
            this.registeredSince = rs.getString("registeredSince");
            this.status = rs.getString("status");
            this.password = rs.getString("password");
        }

    }

    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE student SET firstName=" + this.firstName
                + ", lastName=" + this.lastName + ", address=" + this.address
                + ", city=" + this.city + ", country=" + this.country
                + ", zipcode=" + this.zipcode + ", phone=" + this.phone
                + ", programID=" + this.programID + ", registeredSince=" + this.registeredSince
                + ", status=" + this.status + ", password=" + this.password
                + "WHERE studentID=" + this.studentID);
    }

    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM student WHERE studentID=" + this.studentID);
    }
    public void insert() throws SQLException {
        String sql="INSERT INTO student  (studentID, firstName, lastName, address, city, country, "
                + "zipcode, phone, programID, registeredSince, status, password) values "
                + "("+ this.studentID+", '"+ this.firstName+"', '" + this.lastName+"', '" +  this.address +"', '"+ this.city+"', '" + this.country
                +"', '"+ this.zipcode+"', '" + this.phone +"', '"+ this.programID +"', '"+ this.registeredSince+"', '" + this.status +"', '" + this.password
                +  "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
