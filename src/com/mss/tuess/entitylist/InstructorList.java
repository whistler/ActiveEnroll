package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Instructor;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorList {
    
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
}
