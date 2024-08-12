package com.comcast.crm.generic.databaseutility;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	java.sql.Connection connection;

	public void getDbConnection(String url, String username, String password) throws SQLException {
		try {
			Driver dr = new Driver();
			DriverManager.registerDriver(dr);
			java.sql.Connection connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

		}
	}

	public void getDbConnection() throws SQLException {
		try {
			Driver dr = new Driver();
			DriverManager.registerDriver(dr);
			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root",
					"root");
		} catch (Exception e) {
		}
	}

	public void closeDbConnection() throws SQLException {
		try {
			connection.close();
		} catch (Exception e) {

		}

	}

	public ResultSet executeNonSelectQuery(String query) throws Throwable {
		ResultSet result = null;
		try {

			java.sql.Statement stat = connection.createStatement();
			result = stat.executeQuery(query);

		} catch (Exception e) {
		}
		return result;
	}

	public int excuteNonSelectQuery(String Query) {
		int result = 0;
		try {

			java.sql.Statement stat = connection.createStatement();
			result = stat.executeUpdate(Query);

		} catch (Exception e) {
		}
		return result;

	}

}
