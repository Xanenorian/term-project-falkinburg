import java.sql.*;

public class DBConnectionFalkinburg {
	private static Connection connection = null;
	static String url = "jdbc:mysql://ec2-3-18-221-52.us-east-2.compute.amazonaws.com:3306/MyDatabase0315";
	static String user = "bfalkinburg_remote";
	static String password = "G@mer24712";
	
	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}


}
