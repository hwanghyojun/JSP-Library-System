package com.silla.library.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.silla.library.member.AdminDTO;
import com.silla.library.dbconn.DBConnectionInfo;

public class AdminDAO {
	private DBConnectionInfo connInfo = null;
	private Connection dbConn = null;

	public AdminDAO(DBConnectionInfo connInfo) {
		this.connInfo = connInfo;
	}

	public void connect() throws SQLException { // db占쏙옙占쏙옙
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
	public AdminDTO isGetAdminInfo(String id) throws SQLException {
		// 占쎌돳占쎌뜚占쎌젟癰귣떯占쏙옙議뉛옙�궎疫뀐옙.
		ResultSet rs = null;
		PreparedStatement stmt = null;
		AdminDTO dto = new AdminDTO();
		String sql = null;
		sql = String.format("select * from %s where aid='%s'", connInfo.getDbadmin(), id); // 占쎈툡占쎌뵠占쎈탵 占쎌뿯占쎌젾占쎈퉸 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎌젟癰귨옙 筌뤴뫀紐� 揶쏉옙占쎌죬占쎌궎疫뀐옙.
		connect();
		try {
			stmt = dbConn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				while (rs.next()) { //占쎈퉸占쎈뼣 占쎈릭占쎈뮉 占쎈툡占쎌뵠占쎈탵揶쏉옙 占쎌뿳占쎈뮉 野껋럩�뒭.
					dto.setAno(rs.getInt("ano"));
					dto.setAid(rs.getString("aid"));
					dto.setApw(rs.getString("apw"));
					dto.setAname(rs.getString("aname"));
					dto.setAtel(rs.getString("atel"));
					
				}
			}
			rs.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (stmt != null)
				stmt.close();
			disconnect();
		}
		return dto;
	}
	
	public int isAdminLoginCheck(String id, String pw) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		connect();

		String dbPW = ""; 
		int x = -1;

		try {
		

			String sql = "select apw from " + connInfo.getDbadmin() + " where aid =?";
			//愿�由ъ옄 濡쒓렇�씤�떆 �븘�씠�뵒�� 鍮꾨�踰덊샇媛� �씪移섑븯�뒗吏� �솗�씤.
			stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) 
			{
				dbPW = rs.getString("apw"); 
				if (dbPW.equals(pw))
					x = 1; // 1 �씠硫� 鍮꾨�踰덊샇 �씪移�.
				else
					x = 0; // 0 �씠硫� 遺덉씪移�.

			} else {
				x = -1; // -1 �씠硫� �븘�씠�뵒 �뾾�쓬.
			}

			return x;

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}

}
