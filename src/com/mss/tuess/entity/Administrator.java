package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Administrator class
 */
public class Administrator extends User{
    static ArrayList<Administrator> administrators = new ArrayList();

    /**
     * Loads all Administrator records from the database in to a list of Administrator
     * objects.
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM administrator";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Administrator administrator = new Administrator();
            administrator.setID(rs.getInt("adminID"));
            administrator.setFirstName(rs.getString("firstName"));
            administrator.setLastName(rs.getString("lastName"));
            administrator.setAddress(rs.getString("address"));
            administrator.setCity(rs.getString("city"));
            administrator.setCountry(rs.getString("country"));
            administrator.setZipcode(rs.getString("zipcode"));
            administrator.setPhone(rs.getString("phone"));
            administrator.setPassword(rs.getString("password"));
            administrator.setPassword(rs.getString("email"));
            administrators.add(administrator);
        }
    }

    /**
     * Returns the administrator stored at the given index.
     * @param index of the administrator
     * @return Administrator object at position index
     */
    public static Administrator get(int index) {
        return administrators.get(index);
    }
/**
 * ID of the administrator
 */
    private int adminID;

    /**
     * Get the administrator ID.
     * @return the adminID
     */
    @Override
    public int getID() {
        return adminID;
    }

    /**
     * Set the administrator ID.
     * @param adminID the adminID to set
     */
    @Override
    public void setID(int adminID) {
        this.adminID = adminID;
    }

    /**
     * Loads Administrator with the given adminID from the database.
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
     * Updates the current administrator record in the database.
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

    /**
     * Deletes the current administrator record in the database.
     * @throws SQLException 
     */
    @Override
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM adminstrator WHERE adminID=" + this.getID());
    }

    /**
     * Creates a new administrator record in the database with the properties of this Administrator
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
