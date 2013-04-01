package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Instructor extends User{

    private int instructorID;
    private String deptID;

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
     * Loads the Instructor by the instructorID from the database and encapsulates
     * into this Instructor objects
     *
     * @throws SQLException
     */
    @Override
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
            this.setPassword(rs.getString("password"));
        }

    }

    /**
     * Uses the information of this Instructor to update the record in the
     * database.
     *
     * @throws SQLException
     */
    @Override
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE instructor SET firstName=" + this.getFirstName()
                + ", lastName=" + this.getLastName() + ", address=" + this.getAddress()
                + ", city=" + this.getCity() + ", country=" + this.getCountry()
                + ", zipcode=" + this.getZipcode() + ", phone=" + this.getPhone()
                + ", deptID=" + this.getDeptID()
                + ", password=" + this.getPassword()
                + "WHERE instructorID=" + this.getInstructorID());
    }
    
    /**
     * Delete this Instructor record in the database.
     *
     * @throws SQLException
     */
    @Override
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM instructor WHERE instructorID=" + this.getInstructorID());
    }
    
    /**
     * Insert this Instructor into the database.
     *
     * @throws SQLException
     */
    @Override
    public void insert() throws SQLException {
        String sql="INSERT INTO instructor (instructorID, firstName, lastName, address, city, country, "
                + "zipcode, phone, deptID, password) values "
                + "("+ this.getInstructorID()+", '"+ this.getFirstName()+"', '" + this.getLastName()+"', '" +  this.getAddress() +"', '"+ this.getCity()+"', '" + this.getCountry()
                +"', '"+ this.getZipcode()+"', '" + this.getPhone() +"', '"+ this.getDeptID()+"', '" + this.getPassword()
                +  "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
}
