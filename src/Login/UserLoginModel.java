
package Login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLoginModel {
 
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mysql";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "karthik";
 
	public String getPassword(String userId){
		
		String password = "";
		try {
			password = selectRecordsFromDbUserTable(userId);
			//System.out.println("password after ret : "+password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return password;
	}
 
	private String selectRecordsFromDbUserTable(String userId) throws SQLException {
 
		Connection dbConnection = null;
		Statement statement = null;
		String retPassword="";
 
		String selectTableSQL = "SELECT idlogin, password from test.login where idlogin='"+userId+"';";
 
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			// execute select SQL statement
			ResultSet rs = statement.executeQuery(selectTableSQL);
 
			while (rs.next()) {
				String userid = rs.getString("idlogin");
				retPassword = rs.getString("password");
 
				System.out.println("\nuserid : " + userid);
				System.out.println("password : " + retPassword);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
 
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}	
		}
		return retPassword;
	}
 
	private Connection getDBConnection() {
 
		Connection dbConnection = null;
 
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
 
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}