package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends User{

    private int adminID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String zipcode;
    private String phone;
    private String email;
    private String password;

    /**
     * @return the adminID
     */
    public int getAdminID() {
        return adminID;
    }

    /**
     * @param adminID the adminID to set
     */
    public void setAdminID(int adminID) {
        this.adminID = adminID;
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
     * Loads Administrator with the given adminID from the database
     * @param adminId adminID for the Administrator to fetch
     * @throws SQLException 
     */
    public void fetch(int adminId) throws SQLException {
        ResultSet rs;
        rs = DatabaseConnector.returnQuery("SELECT * FROM administrator WHERE adminID = " + adminId);
        if (rs.next()) {
            this.adminID = rs.getInt("adminID");
            this.firstName = rs.getString("firstName");
            this.lastName = rs.getString("lastName");
            this.address = rs.getString("address");
            this.city = rs.getString("city");
            this.country = rs.getString("country");
            this.zipcode = rs.getString("zipcode");
            this.phone = rs.getString("phone");
            this.password = rs.getString("password");
            this.email = rs.getString("email");
        }

    }

    /**
     * Updates the current record in the database
     * @throws SQLException 
     */
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE administrator SET "
                + "firstName='" + this.firstName + "', " 
                + "lastName='" + this.lastName + "', " 
                + "address='" + this.address + "', "
                + "city='" + this.city + "', "
                + "country='" + this.country + "', "
                + "zipcode='" + this.zipcode + "', " 
                + "phone='" + this.phone + "', " 
                + "password='" + this.password + "'"
                + "email='" + this.email + "'"
                + "WHERE adminID=" + this.adminID);
    }

    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM adminstrator WHERE adminID=" + this.adminID);
    }

    /**
     * Creates a new record with the database with the properties of this Administrator
     * @throws SQLException 
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO administrator (studentID, firstName, lastName, address, city, country, "
                + "zipcode, phone, password) values " + "(" 
                + this.adminID + ", '" 
                + this.firstName + "', '" 
                + this.lastName + "', '" 
                + this.address + "', '" 
                + this.city + "', '" 
                + this.country + "', '" 
                + this.zipcode + "', '" 
                + this.phone + "', '" 
                + this.password
                + "')";
        
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
