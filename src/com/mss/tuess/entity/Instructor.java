package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Instructor extends User{

    private int instructorID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String zipcode;
    private String phone;
    private String deptID;
    private String password;
    private String email;

    /**
     * @return the instructorID
     */
    public int getInstructorID() {
        return instructorID;
    }

    /**
     * @param instructorID the instructorID to set
     */
    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
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
     * Loads the Instructor by the instructorID from the database and encapsulates
     * into this Instructor objects
     *
     * @throws SQLException
     */
   public void fetch(int InstructorID) throws SQLException {
        ResultSet rs;
        rs = DatabaseConnector.returnQuery("SELECT * FROM instructor WHERE instructorID = " + InstructorID);
        if (rs.next()) {
            this.setInstructorID(rs.getInt("instructorID"));
            this.setFirstName(rs.getString("firstName"));
            this.setLastName(rs.getString("lastName"));
            this.setAddress(rs.getString("address"));
            this.setCity(rs.getString("city"));      
            this.setZipcode(rs.getString("zipcode"));
            this.setCountry(rs.getString("country"));
            this.setPhone(rs.getString("phone"));
            this.setDeptID(rs.getString("deptID"));
            this.setEmail(rs.getString("email"));
            this.setPassword(rs.getString("password"));
        }

    }

    /**
     * Uses the information of this Instructor to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE instructor SET firstName=" + this.getFirstName()
                + ", lastName=" + this.getLastName() + ", address=" + this.getAddress()
                + ", city=" + this.getCity() + ", country=" + this.getCountry()
                + ", zipcode=" + this.getZipcode() + ", phone=" + this.getPhone()
                + ", deptID=" + this.getDeptID()
                + ", email=" + this.getEmail()
                + ", password=" + this.getPassword()
                + "WHERE instructorID=" + this.getInstructorID());
    }
    
    /**
     * Delete this Instructor record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM instructor WHERE instructorID=" + this.getInstructorID());
    }
    
    /**
     * Insert this Instructor into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql="INSERT INTO instructor (instructorID, firstName, lastName, address, city, country, "
                + "zipcode, phone, deptID, email, password) values "
                + "("+ this.getInstructorID()+", '"+ this.getFirstName()+"', '" + this.getLastName()+"', '" +  this.getAddress() +"', '"+ this.getCity()+"', '" + this.getCountry()
                +"', '"+ this.getZipcode()+"', '" + this.getPhone() +"', '"+ this.getDeptID()+"', '"+ this.getEmail()+"', '" + this.getPassword()
                +  "')";
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
