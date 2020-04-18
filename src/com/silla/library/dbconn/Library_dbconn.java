package com.silla.library.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.silla.library.dbconn.DBConnectionInfo;

public class Library_dbconn {

		private DBConnectionInfo connInfo = null;
		private Connection dbConn = null;

		public Library_dbconn(DBConnectionInfo connInfo) {
			this.connInfo = connInfo;
		}
		
		public void connect() throws SQLException { // db�뿰寃�
			try {
				Context context = new InitialContext();
				DataSource ds = (DataSource) context.lookup("java:comp/env/" + connInfo.getDsLink());
				dbConn = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void disconnect() throws SQLException {
			if (dbConn != null) { // dbConnection
				dbConn.close();
				dbConn = null;
			}
		}

		public Statement createStatement() {
			Statement stmt = null;
			try {
				stmt = dbConn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return stmt;
		}
		public String abc() throws SQLException {
			String abc = "abc";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT abc from library;";
			try {
				connect();

				stmt = dbConn.prepareStatement(sql);

				rs = stmt.executeQuery();

				if (rs.isBeforeFirst()) {
					rs.next();
					abc = rs.getString("abc");
					System.out.println(abc);
				}

				rs.close();

			} catch (

			SQLException ex) {
				throw ex;
			} finally {
				if (stmt != null)
					stmt.close();
				disconnect();
			}
			return abc;
		}
		
}
