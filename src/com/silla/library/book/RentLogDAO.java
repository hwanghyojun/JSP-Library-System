package com.silla.library.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.silla.library.dbconn.DBConnectionInfo;

public class RentLogDAO {
	private DBConnectionInfo connInfo = null;
	private Connection dbConn = null;

	public RentLogDAO(DBConnectionInfo connInfo) {
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

	public void isRentInfo(String isbn, String mno) throws SQLException {


		PreparedStatement stmt = null;
		try {

			connect();

			String sql = "INSERT into " + connInfo.getDbrentlog() + " (isbn, mno) values (?, ?);";
			// DB�뿉 �쉶�썝�젙蹂� �궫�엯.
			stmt = dbConn.prepareStatement(sql);

			stmt.setString(1, isbn);
			stmt.setString(2, mno);
			stmt.executeUpdate();
			System.out.println("isbn: " + isbn + " mno: " + mno + " Rent");
		}

		catch (SQLException ex) {
			throw ex;
		} finally {
			if (stmt != null)
				stmt.close();
			disconnect();
		}
	}

	public void isReturnInfo(String isbn, String mno) throws SQLException {
		
		Statement stmt = null;
		connect();
		
		// execute SQL statement
		String sql = "delete from " + connInfo.getDbrentlog() + " where isbn = "+isbn+" AND mno= "+mno+" ;";
		//�븘�씠�뵒濡� �빐�떦 �븘�씠�뵒 �젙蹂� �궘�젣�븯湲�.
		
		try {
			stmt = dbConn.createStatement();

			stmt.executeUpdate(sql);
			System.out.println("isbn: " + isbn + " mno: " + mno + " Return");
		}
		catch(SQLException e) {
			throw e;
		}
		finally {
			disconnect();
		}
	}
	public ArrayList<RentLogDTO> isGetRentLogInfo(String mno) throws SQLException {//책대여 정보를 모두 들고옴
		ArrayList<RentLogDTO> rentlog = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = String.format("SELECT isbn fROM %s WHERE mno=%s;", connInfo.getDbrentlog(),mno);
		try {
			connect();

			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {
				if (rentlog == null)
					rentlog = new ArrayList<RentLogDTO>();

				while (rs.next()) {
					RentLogDTO rlist = new RentLogDTO();
					
					rlist.setIsbn(rs.getString("isbn"));
					
					// RentLogDTO 객체에 검색 결과 값을 모두넣음
					rentlog.add(rlist);

				}

			}
			rs.close();
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (stmt != null)
				stmt.close();
			disconnect();
		}

		return rentlog;
	}
}
