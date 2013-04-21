package com.mss.tuess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {

    private static Connection conn;
    private static final String url = "jdbc:mysql://127.0.0.1/TUESS1";
    private static final String user = "user";
    private static final String password = "cheese";
    

    /**
     * Creates a connection to the database
     *
     * @throws ClassNotFoundException
     */
    public static void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            out.println("success conn =" + (conn).toString());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Closes the database connection
     */
    public static void close() {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (Exception ex) {
            out.println("fail....");
        }
    }

    /**
     * Returns the database connection
     *
     * @return database connection
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * Runs a database query that does not return any records
     *
     * @param sql query to be run
     * @throws SQLException
     */
    public static void updateQuery(String sql) throws SQLException {
        System.out.println(sql);

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    /**
     * Runs a database query and returns results in a ResultSet
     *
     * @param sql query to be run
     * @return ResultSet returned by the query
     * @throws SQLException
     */
    public static ResultSet returnQuery(String sql) throws SQLException {
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        return rs;
    }
}