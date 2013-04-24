package com.mss.tuess.util;

import com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * DatabaseConnector class. JDBC driver to connect to mysql server.
 */
public class DatabaseConnector {

    private static Connection conn;
//    private static final String url = "jdbc:mysql://198.71.87.177:3306/TUESS1";
//    private static final String user = "user";
//    private static final String password = "cheese";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/TUESS?connectTimeout=0&socketTimeout=0&autoReconnect=true";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * Creates a connection to the database
     *
     * @throws ClassNotFoundException
     */
    public static void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("success conn =" + (conn).toString());
        } catch (Exception ex) {
            ViewManager.showError("Network Errorr", "Unable to connect to database server");
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
            System.out.println("Unable to disconnect");
        }
    }

    /**
     * Returns the database connection
     *
     * @return database connection
     */
    public static Connection getConn() {
        try {
            if (!conn.isValid(100)) {
                Connect();
            }
        } catch (SQLException ex) {
            ViewManager.showError("Connection Lost", "Make sure that you are connected to the internet");
        } finally {
            return conn;
        }
    }

    /**
     * Runs a database query that does not return any records
     *
     * @param sql query to be run
     * @throws SQLException
     */
    public static void updateQuery(String sql) throws SQLException {
        System.out.println(sql);
        Statement stmt = getConn().createStatement();
        try {
            stmt.execute(sql);
        } catch (MySQLNonTransientConnectionException se) {
            ViewManager.showError("Connection Lost", "Make sure that you are connected to the internet");
        }
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
        Statement stmt;
        ResultSet rs = null;
        try {
            stmt = getConn().createStatement();
            stmt.execute(sql);
            rs = stmt.getResultSet();
        } catch (Exception ex) {
            ViewManager.showError("Connection Lost", "Make sure that you are connected to the internet");
        }
        return rs;
    }
}