package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Administrator;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministratorList {
    
    private static ArrayList<Administrator> administrators = new ArrayList();

    /**
     * Loads all Administrator records from the database in to a list of Administrator
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM administrator";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Administrator administrator = new Administrator();
            administrator.setAdminID(rs.getInt("adminID"));
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
     * Returns the administrator stored at the given index
     *
     * @param index index of the administrator to return
     * @return Administrator object at position index
     */
    public static Administrator get(int index) {
        return administrators.get(index);
    }
}