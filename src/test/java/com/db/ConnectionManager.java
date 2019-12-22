package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.seleniumExceptionHandling.CustomExceptionHandler;

public class ConnectionManager {

	private static final String FOR_NAME = "com.mysql.jdbc.Driver";
	private static final String HOST = "localhost";
	private static final String PORT = "3306";
	private static final String DB = "db_ocs";
	private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static Connection con = null;
	private ResultSet rs;

	/**
	 * FOR TESTING PURPOSE ONLY
	 * */
	public static void main(String args[]) {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			/*
			 * Connection con =
			 * DriverManager.getConnection("jdbc:mysql://3.106.103.74:3306/db_ocs", "admin",
			 * "Ydt@2019");
			 */

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ocs", "root", "");

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery("select * from tbl_activity");

			/*
			 * rs.last();
			 * 
			 * System.out.println(rs.getRow());
			 */

			int count = 1;
			while (rs.next())
				System.out.println(count++ + " : " + rs.getString(3));

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Most Important! - Run this method when your test does not want to get any data
	 * from db It will close the connection object, also will set null to
	 * {@link ResultSet} object 
	 * @Precondition  
	 * @Postcondition will close the connection object, also will set null to {@link ResultSet} object
	 * 
	 * @author shailendra Oct 25, 2019
	 */
	public synchronized void closeConnection() {
		if (con != null) {
			try {
				con.close();
				con = null;
				CustomReporter.report(STATUS.INFO, "Connection closed");
			} catch (SQLException e) {
				new CustomExceptionHandler(e);
			}
		}
	}

	/**
	 * It will initialise the connection object, so that we can fire
	 * queries 
	 * @Precondition : Just make sure your username and password is functional
	 * @Postcondition : Will init the connection object for firing up the queries
	 * 
	 * @author shailendra Oct 25, 2019
	 */
	private static synchronized void createConnection() {
		if (con == null) {
			try {
				CustomReporter.createNode("Connecting with DataBase");
				Class.forName(FOR_NAME);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				CustomReporter.report(STATUS.INFO, "Connection successfull, make sure to close the connection");
			} catch (Exception e) {
				new CustomExceptionHandler(e);
				Assert.fail(e.getMessage());
			}
		}
	}

	/**
	 * It will fire the passed queryString on db and initialise the result set
	 * object, also will
	 * 
	 * @return {@link ResultSet} object if you want to create your code Precondition
	 *         : Call ConnectionManager.createConnection(); method prior to run this
	 *         Postcondition : Will init the result set object for further use.
	 */
	public synchronized ResultSet executeQuery(String queryString) {
		if (con == null) {
			createConnection();
		}

		try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(queryString.trim());
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}finally {
			// If we close the connection here, then result set will not work
			//closeConnection();
		}
		
		return rs;
	}

	/**
	 * It will print the result of query, in console so that you can copy it in
	 * excel for further analysis Precondition : Call
	 * ConnectionManager.createConnection(); and ConnectionManager.executeQuery();
	 * methods prior to run this Postcondition : Will print the content of result
	 * set object on console
	 * 
	 * @author shailendra Oct 25, 2019
	 * 
	 */
	public synchronized void printAllText() {
		if (rs != null) {
			try {
				int count = 1;
				ResultSetMetaData metaData = rs.getMetaData();
				int columnsNumber = metaData.getColumnCount();

				String columnHeaders = "";
				for (int i = 1; i <= columnsNumber; i++) {
					columnHeaders = columnHeaders + "	" + metaData.getColumnLabel(i);
				}
				System.out.println("0" + columnHeaders);

				while (rs.next()) {
					String rowWiseData = "";
					for (int i = 1; i <= columnsNumber; i++) {
						rowWiseData = rowWiseData + "	" + rs.getString(i);
					}
					rowWiseData = count++ + rowWiseData;
					System.out.println(rowWiseData);
				}
			} catch (Exception e) {
				new CustomExceptionHandler(e);
			}
		}

	}

	/**
	 * Returns the all values of passed column, which matches the passed query
	 * 
	 * Usage : 
	 * con.getCellText("Emp_Name","PERIOD_START_DATE == 6/1/2018 0:0:0","CITY == Mumbai"); 
	 * 
	 * @param colName String column name
	 * @param query String array of query "col1 == data1", "col2 == data2" ...etc
	 * 
	 * @author shailendra Oct 25, 2019
	 */
	public synchronized List<String> getColumnValues(String colName, String... query) {
		List<String> result = new ArrayList<String>();
		if (rs != null) {
			try {
				rs.beforeFirst();
				while (rs.next()) {

					// Matching whether row has all matching column data
					boolean[] flagArr = new boolean[query.length];
					for (int i = 0; i < query.length; i++) {
						String searchColNameVal = query[i];
						String expectedVal = searchColNameVal.split("==")[1];
						String col = searchColNameVal.split("==")[0];
						String actualVal = rs.getObject(col).toString();
						if (expectedVal.trim().equalsIgnoreCase(actualVal.trim())) {
							flagArr[i] = true;
						}
					}

					// getting the required column data
					boolean mayRun = true;
					for (int i = 0; i < flagArr.length; i++) {
						if (!flagArr[i]) {
							mayRun = false;
						}
					}
					if (mayRun) {
						result.add(rs.getObject(colName).toString());
					}
				}
			} catch (Exception e) {
				new CustomExceptionHandler(e);
			}
		}
		return result;
	}

	/**
	 * Returns all values of passed column in a string type list
	 * @param colName
	 * @return list of column values
	 * 
	 * @author shailendra Oct 25, 2019
	 */
	public List<String> getColumnValues(String colName) {
		List<String> list = new ArrayList<String>();
		if (rs != null) {
			try {
				rs.beforeFirst();
				while (rs.next()) {
					list.add(rs.getObject(colName).toString());
				}
			} catch (Exception e) {
				new CustomExceptionHandler(e, "Passed Column "+colName);
			}
		}
		return list;
	}

}