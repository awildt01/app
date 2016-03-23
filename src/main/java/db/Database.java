package db;

import java.sql.*;

/**
 * Die Klasse Datebase ermöglicht durch JDBC (Java Database Connetivity) eine Verbindung zur Datenbank aufzubauen 
 * und SQL-Anweisungen auszuführen.
 * 
 * @author wildt
 * @version 1.0
 * 
 */


public class Database {
	
	/**
	 * Die Methode getConnection gibt der verbindung zur Datenbank zurück  .
	 * 
	 */

	public static Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Danger_test";
			con = DriverManager.getConnection(url, "postgres", "awildt");
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

}
