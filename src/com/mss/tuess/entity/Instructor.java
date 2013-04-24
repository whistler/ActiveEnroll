package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Instructor class. Concrete object of User abstract class. 
 * Instructor has additional attribute of the department he/she works in. 
 * Instructor can grade student of the course he/she taught and waive prerequisite for a student.
 */
public class Instructor extends User{
    private static ArrayList<Instructor> instructors = new ArrayList();

    /**
     * Loads all Instructor records from the database in to a list of Instructor
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM instructor";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Instructor instructor = new Instructor();
            instructor.setID(rs.getInt("instructorID"));
            instructor.setFirstName(rs.getString("firstName"));
            instructor.setLastName(rs.getString("lastName"));
            instructor.setAddress(rs.getString("address"));
            instructor.setCity(rs.getString("city"));
            instructor.setZipcode(rs.getString("zipcode"));
            instructor.setCountry(rs.getString("country"));
            instructor.setPhone(rs.getString("phone"));
            instructor.setDeptID(rs.getString("deptID"));
            instructor.setPassword(rs.getString("password"));
            instructors.add(instructor);
        }
    }

    /**
     * Returns the instructor stored at the given index
     *
     * @param index index of the instructor to return
     * @return Instructor object at position index
     */
    public static Instructor get(int index) {
        return instructors.get(index);
    }

    private int instructorID;
    private String deptID;

    /**
     * @return the instructorID
     */
    @Override
    public int getID() {
        return instructorID;
    }

    /**
     * @param instructorID the instructorID to set
     */
    @Override
    public void setID(int instructorID) {
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
     * @param InstructorID to fetch with
     * @throws SQLException
     */
    @Override
   public void fetch(int InstructorID) throws SQLException {
        ResultSet rs;
        rs = DatabaseConnector.returnQuery("SELECT * FROM instructor WHERE instructorID = " + InstructorID);
        if (rs.next()) {
            this.setID(rs.getInt("instructorID"));
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
    @Override
    public void update() throws SQLException {
        DatabaseConnector.updateQuery("UPDATE instructor SET firstName=" + this.getFirstName()
                + ", lastName=" + this.getLastName() + ", address=" + this.getAddress()
                + ", city=" + this.getCity() + ", country=" + this.getCountry()
                + ", zipcode=" + this.getZipcode() + ", phone=" + this.getPhone()
                + ", deptID=" + this.getDeptID()
                + ", email=" + this.getEmail()
                + ", password=" + this.getPassword()
                + "WHERE instructorID=" + this.getID());
    }
    
    /**
     * Delete this Instructor record in the database.
     *
     * @throws SQLException
     */
    @Override
    public void delete() throws SQLException {
        DatabaseConnector.updateQuery("DELETE FROM instructor WHERE instructorID=" + this.getID());
    }
    
    /**
     * Insert this Instructor into the database.
     *
     * @throws SQLException
     */
    @Override
    public void insert() throws SQLException {
        String sql="INSERT INTO instructor (instructorID, firstName, lastName, address, city, country, "
                + "zipcode, phone, deptID, email, password) values "
                + "("+ this.getID()+", '"+ this.getFirstName()+"', '" + this.getLastName()+"', '" +  this.getAddress() +"', '"+ this.getCity()+"', '" + this.getCountry()
                +"', '"+ this.getZipcode()+"', '" + this.getPhone() +"', '"+ this.getDeptID()+"', '"+ this.getEmail()+"', '" + this.getPassword()
                +  "')";
        DatabaseConnector.updateQuery(sql);
    }
}
