package com.Junglee.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBManager {
	String RunPlatform = "Jenkins";

	public Connection createConnectionForHowzat() throws Exception {
		String port = ":5432/";
		String localIp = "jdbc:postgresql://10.90.25.50";
		String username = "funny";
		String password = "funny";

		// if(RunPlatform.equals("Jenkins"))
		// {
		// username = "<UserName>";
		// password = "<Pwd>";
		// }

		// String Schema = "play_fantasy";
		String Schema = "play_fantasy";
		Connection con = null;

		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(localIp + port + Schema, username, password);
		if (con == null) {
			System.out.println("No Connection");
		}

		return con;
	}

	public Map<String, String> executeQuerySRow(String query, Connection c) {
		System.out.println("Executing DB Query");
		System.out.println(query);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
		}
		// c = createConnectionForHowzat();
		Statement stm = null;
		ResultSet rs = null;
		ResultSetMetaData rowData;
		Map<String, String> map = null;

		try {
			stm = c.createStatement();
			rs = stm.executeQuery(query + " limit 1");
			rowData = rs.getMetaData();
			int column = rowData.getColumnCount();
			while (rs.next()) {
				map = new LinkedHashMap<String, String>();
				for (int i = 1; i < column; i++) {
					System.out.println(rowData.getColumnName(i) + ":" + rs.getString(rowData.getColumnName(i)));
					map.put(rowData.getColumnName(i), rs.getString(rowData.getColumnName(i)));
				}
			}
			closeConnection(c, rs, stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}

	public List<LinkedHashMap<String, String>> executeQueryAllRow(String query, Connection c) {
		// c = createConnectionForHowzat();
		Statement stm = null;
		ResultSet rs = null;
		ResultSetMetaData rowData;
		List<LinkedHashMap<String, String>> list = new LinkedList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> map = null;
		try {
			System.out.println("Query : " + query);
			stm = c.createStatement();
			rs = stm.executeQuery(query);
			rowData = rs.getMetaData();
			int column = rowData.getColumnCount();
			System.out.println("Column Count:" + column);
			while (rs.next()) {
				map = new LinkedHashMap<String, String>();
				for (int i = 1; i < column; i++) {
					map.put(rowData.getColumnName(i), rs.getString(rowData.getColumnName(i)));
					System.out.println(rowData.getColumnName(i) + ":" + rs.getString(rowData.getColumnName(i)));
				}
				list.add(map);
				System.out.println("List size: " + list.size());
			}
			closeConnection(c, rs, stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public void closeConnection(Connection con, Statement st) {
		try {
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
		System.out.println("Connection Closed");

	}

	public void closeConnection(Connection con, ResultSet rs, Statement st) {
		try {
			con.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
		System.out.println("Connection Closed");
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * DBManager db = new DBManager(); String Query
	 * ="select * from tpsbm order by createdate desc";
	 * db.executeQuerySRow(Query); }
	 */

}
