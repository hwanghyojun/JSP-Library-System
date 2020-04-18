package com.silla.library.seat;

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
import com.silla.library.member.MemberDTO;

public class RoomDAO {

	private DBConnectionInfo connInfo = null;
	private Connection dbConn = null;

	public RoomDAO(DBConnectionInfo connInfo) {
		this.connInfo = connInfo;
	}
	
	public void connect() throws SQLException { // db연결
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

	public String isGetTotal(String rno) throws SQLException {
		String total = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
        String sql = String.format("SELECT total FROM %s WHERE rno = %s;",connInfo.getDbroom(),rno);
		try {
			connect();

			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {
				rs.next();
				total = rs.getString("total");
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
		return total;
	}
	
}
