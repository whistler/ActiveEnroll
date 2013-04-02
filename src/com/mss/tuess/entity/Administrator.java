package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends User{

    private int adminID;

    /**
     * @return the adminID
     */
    @Override
    public int getID() {
        return adminID;
    }

    /**
     * @param adminID the adminID to set
     */
    public void setID(int adminID) {
        this.adminID = adminID;
    }

    /**
     * Loads Administrator with the given adminID from the database
     * @param adminId adminID for the Administrator to fetch
     * @throws SQLException 
     */
    @Override
    public void fetch(int adminId) throws SQLException {
        ResultSet rs;
        rs = DatabaseConnector.returnQuery("SELECT * FROM administrator WHERE adminID = " + adminId);
        if (rs.next()) {
            this.setID( rs.getInt("adminID"));
            this.setFirstName(rs.getString("firstName"));
            this.setLastName(rs.getString("lastName"));
            this.setAddress(rs.getString("address"));
            this.setCity(rs.getString("city"));
            this.setCountry(rs.getString("country"));
            this.setZipcode(rs.getString("zipcode"));
            this.setPhone(rs.getString("phone"));
            this.setPassword(rs.getString("password"));
            this.setEmail (rs.getString("email"));
        }

    }

    /**
     * Updates the current record in the database
     * @throws SQLException 
     */
    @Override
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE administrator SET "
                + "firstName='" + this.getFirstName() + "', " 
                + "lastName='" + this.getLastName() + "', " 
                + "address='" + this.getAddress() + "', "
                + "city='" + this.getCity() + "', "
                + "country='" + this.getCountry() + "', "
                + "zipcode='" + this.getZipcode() + "', " 
                + "phone='" + this.getPhone() + "', " 
                + "password='" + this.getPassword() + "'"
                + "email='" + this.getEmail ()+ "'"
                + "WHERE adminID=" + this.getID());
    }

    @Override
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM adminstrator WHERE adminID=" + this.getID());
    }

    /**
     * Creates a new record with the database with the properties of this Administrator
     * @throws SQLException 
     */
    @Override
    public void insert() throws SQLException {
        String sql = "INSERT INTO administrator (studentID, firstName, lastName, address, city, country, "
                + "zipcode, phone, password) values " + "(" 
                + this.getID() + ", '" 
                + this.getFirstName() + "', '" 
                + this.getLastName() + "', '" 
                + this.getAddress() + "', '" 
                + this.getCity() + "', '" 
                + this.getCountry() + "', '" 
                + this.getZipcode() + "', '" 
                + this.getPhone() + "', '" 
                + this.getPassword()
                + "')";
        
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
