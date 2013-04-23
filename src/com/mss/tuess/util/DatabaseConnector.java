package com.mss.tuess.util;

import com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * DatabaseConnector class. JDBC driver to connect to mysql server.
 */
public class DatabaseConnector {

    private static Connection conn;
//    private static final String url = "jdbc:mysql://198.71.87.177:3306/TUESS1";
//    private static final String user = "user";
//    private static final String password = "cheese";
    private static final String url = "jdbc:mysql://ec2-54-244-163-174.us-west-2.compute.amazonaws.com:3306/TUESS?connectTimeout=0&socketTimeout=0&autoReconnect=true";
    private static final String user = "root";
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
        try (Statement stmt = conn.createStatement()) {
            try {
                stmt.execute(sql);
            } catch (MySQLNonTransientConnectionException se) {
                int n = JOptionPane.showConfirmDialog(null, "Connection Lost, click OK to reconnect", "OK", JOptionPane.OK_OPTION);
                if (n == JOptionPane.OK_OPTION) {
                    Connect();
                }
            }
        }
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
        stmt = conn.createStatement();
        try {
            stmt.execute(sql);
        } catch (MySQLNonTransientConnectionException se) {
            int n = JOptionPane.showConfirmDialog(null, "Connection Lost, click OK to reconnect", "OK", JOptionPane.OK_OPTION);
            if (n == JOptionPane.OK_OPTION) {
                Connect();
            }
        }
        ResultSet rs = stmt.getResultSet();
        return rs;
    }
}