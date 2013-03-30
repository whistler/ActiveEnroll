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

    private static Connection conn;
    private static final String url = "jdbc:mysql://198.71.87.177:3306/TUESS";
    private static final String user = "user";
    private static final String password = "cheese";

    /**
     *
     * @throws ClassNotFoundException
     */
    public static void Connect() {
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
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (Exception ex) {
            out.println("fail....");
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void updateQuery(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    public static ResultSet returnQuery(String sql) throws SQLException {
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        return rs;
    }
}