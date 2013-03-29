package com.mss.tuess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import static java.lang.System.out;

/**
 *
 *
 */
public class DatabaseConnector {

    private Connection conn;
    private String url = "jdbc:mysql://198.71.87.177:3306/TUESS";
    private String user = "user";
    private String password = "cheese";

    /**
     *
     * @throws ClassNotFoundException
     */
    public DatabaseConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            out.println("success conn =" + (conn).toString());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.println("fail...");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.println("fail...");
        }
    }

    /**
     * @param conn
     */
    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (Exception ex) {
            out.println("fail....");
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void exeSQL(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    public ResultSet exeResultSet(String sql) throws SQLException {
                    System.out.println(sql);
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        return rs;
    }
}