/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Program;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Xin
 */
public class ProgramList {
    
    private static ArrayList<Program> programs = new ArrayList();

    /**
     * Loads all Program records from the database in to a list of Program
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM program";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Program program = new Program();
            program.setProgramID(rs.getString("programID"));
            program.setDeptID(rs.getString("deptID"));
            program.setDegree(rs.getString("degree"));
            program.setMinCredit(rs.getInt("minCredit"));
            program.setMaxLength(rs.getInt("maxLength"));

            programs.add(program);
        }
    }

    /**
     * Returns the program stored at the given index
     *
     * @param index index of the program to return
     * @return Program object at position index
     */
    public static Program get(int index) {
        return programs.get(index);
    }
    
}
