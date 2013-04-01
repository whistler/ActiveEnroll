package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends User{

    private int adminID;

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
     * Loads Administrator with the given adminID from the database
     * @param adminId adminID for the Administrator to fetch
     * @throws SQLException 
     */
    @Override
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
        }

    }

    /**
     * Updates the current record in the database
     * @throws SQLException 
     */
    @Override
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
                + "WHERE adminID=" + this.adminID);
    }

    @Override
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM adminstrator WHERE adminID=" + this.adminID);
    }

    /**
     * Creates a new record with the database with the properties of this Administrator
     * @throws SQLException 
     */
    @Override
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
}
