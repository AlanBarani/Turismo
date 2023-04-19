package persistencia.genericos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProveedorDeConeccion {

	//private static String url = "jdbc:Sqlite://localhost:8080/TierraMediaBD";


	private static String url = "jdbc:Sqlite:E://TierraMedia2.db";



	private static Connection connection;
	
		
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		}catch (Exception e) {
			throw new SQLException(e);
			
		}
		if (connection == null) {
			connection = DriverManager.getConnection(url);			
		}
		return connection;
	}


	public static void closeConnection() {
		try {
		connection.close();
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
