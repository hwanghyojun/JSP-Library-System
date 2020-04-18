package com.silla.library.seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.silla.library.dbconn.DBConnectionInfo;

public class SeatDAO {

	private DBConnectionInfo connInfo = null;
	private Connection dbConn = null;

	public SeatDAO(DBConnectionInfo connInfo) {
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

	public ArrayList<SeatDTO> isGetSno(String rno) throws SQLException {// 도서실 호수를 입력받아 도서관 자리 출력
		ArrayList<SeatDTO> seat = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = String.format("SELECT * fROM %s WHERE rno = %s;", connInfo.getDbseat(), rno);
		try {
			connect();

			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {
				if (seat == null)
					seat = new ArrayList<SeatDTO>();

				while (rs.next()) {
					SeatDTO slist = new SeatDTO();

					slist.setRno(rs.getInt("rno"));
					slist.setSno(rs.getInt("sno"));
					slist.setMno(rs.getInt("mno"));
					slist.setAno(rs.getInt("ano"));
					slist.setSeatcheck(rs.getBoolean("seatcheck"));
					slist.setStarttime(rs.getTimestamp("starttime"));
					slist.setEndtime(rs.getTimestamp("endtime"));
					// SeatDTO 객체에 검색결과를 넣음
					seat.add(slist);// SeatDTO list에 SeatDTO 객체를 넣음

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

		return seat;
	}

	public String isGetFill(String rno) throws SQLException {// 도서관의 남은 자리 출력
		String fill = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = String.format("SELECT count(*)'count' FROM %s WHERE seatcheck=1 AND rno = %s;",
				connInfo.getDbseat(), rno);// seat 테이블에서 예약 가능한자리의 개수를 출력
		try {
			connect();

			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {
				rs.next();
				fill = rs.getString("count");
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
		return fill;
	}

	public void isReservation(String rno, String sno, String mno, int time) throws SQLException {
		// 자리 예약 기능
		

		Statement stmt = null;
		Calendar cal = Calendar.getInstance();// 현재시간 반환
		Timestamp starttime = new Timestamp(cal.getTimeInMillis());
		cal.add(cal.HOUR, time);// 현재시간 + time 시간
		Timestamp endtime = new Timestamp(cal.getTimeInMillis());

		// execute SQL statement
		String sql = "update " + connInfo.getDbseat() + " set " + "mno=" + mno + ", seatcheck= 0," + " starttime='"
				+ starttime + "', endtime='" + endtime + "' WHERE rno=" + rno + " AND sno=" + sno + ";";
		System.out.println(rno + "-" + sno + " reservation");
		try {
			connect();
			
			stmt = dbConn.createStatement();

			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}

	public void isTimeOut() throws SQLException {// 시간 초과시 자리를 다시 예약 가능한 상태로 바꿈
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		Calendar cal = Calendar.getInstance();// 현재시간 반환
		Timestamp now = new Timestamp(cal.getTimeInMillis());
		long l1 = now.getTime();
		long l2 = 0;
		String sql = "SELECT rno,sno,endtime FROM "+connInfo.getDbseat()+";";
		try {
			connect();

			pstmt = dbConn.prepareStatement(sql);
			stmt = dbConn.createStatement();
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SeatDTO slist = new SeatDTO();

				slist.setRno(rs.getInt("rno"));
				slist.setSno(rs.getInt("sno"));

				if (rs.getTimestamp("endtime") != null) {// endtime null이 아닐때
					l2 = rs.getTimestamp("endtime").getTime();

					if (l1 > l2) {// now 와 endtime 비교하여 now가 더 큰 경우
						String sql2 = "update " + connInfo.getDbseat() + " set " + "mno=null, seatcheck= 1,"
								+ " starttime=null, endtime=null WHERE rno=" + slist.getRno() + " AND sno="
								+ slist.getSno() + ";";
						System.out.println(slist.getRno() + "-" + slist.getSno() + " TimeOut");

						stmt.executeUpdate(sql2);

					}
				}
			}

			rs.close();
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
			disconnect();
		}

	}

	public int isOverlapCheck(String mno) throws SQLException {
		// 濡쒓렇�씤�떆 �븘�씠�뵒 鍮꾨�踰덊샇 �솗�씤.
		ResultSet rs = null;
		PreparedStatement stmt = null;
		connect();

		String checkmno = ""; // �엯�젰�븳 鍮꾨�踰덊샇 �꽔�쓣 蹂��닔.
		int x = 0;

		try {

			String sql = "select mno from " + connInfo.getDbseat() + " where mno =?";
			// �븘�씠�뵒 �엯�젰�떆 �빐�떦�븯�뒗 鍮꾨�踰덊샇瑜� 媛��졇�샂.

			stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, mno);
			rs = stmt.executeQuery();

			if (rs.next()) // �빐�떦�븯�뒗 媛믪씠 �엳�쓣寃쎌슦.
			{
				checkmno = rs.getString("mno"); // �엯�젰�븳 鍮꾨�踰덊샇瑜� 蹂��닔�뿉 �궫�엯.
				if (checkmno.equals(mno)) {
					x = 1; // 1�씠硫� �븘�씠�뵒 鍮꾨�踰덊샇 �씪移�.
				} else {
					x = 0; // 0�씠硫� 鍮꾨�踰덊샇 遺덉씪移�.
				}
			}

			return x;

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}
	public void isEndReservation(String rno, String sno, String mno) throws SQLException {
		// 자리 예약 기능
		connect();

		Statement stmt = null;

		// execute SQL statement
		String sql = "update " + connInfo.getDbseat() + " set " + "mno=null, seatcheck= 1, starttime=null,"
		+"endtime=null WHERE rno=" + rno + " AND sno=" + sno + "AND mno="+mno+";";
		try {
			stmt = dbConn.createStatement();

			stmt.executeUpdate(sql);
			System.out.println(rno + "-" + sno + " End Reservation");
		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}
	public void isRepair(String rno, String sno, String ano) throws SQLException {
		// 자리 예약 기능
		

		Statement stmt = null;

		// execute SQL statement
		String sql = "update " + connInfo.getDbseat() + " set " + "ano=" + ano + ", seatcheck= 0 "
		+"WHERE rno=" + rno + " AND sno=" + sno + ";";
		System.out.println(rno + "-" + sno + " Repair");
		try {
			connect();
			
			stmt = dbConn.createStatement();

			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}
	public void isEndRepair(String rno, String sno, String ano) throws SQLException {
		// 자리 예약 기능
		connect();

		Statement stmt = null;

		// execute SQL statement
		String sql = "update " + connInfo.getDbseat() + " set " + "ano=null, seatcheck= 1, starttime=null,"
		+"endtime=null WHERE rno=" + rno + " AND sno=" + sno + "AND ano="+ano+";";
		try {
			stmt = dbConn.createStatement();

			stmt.executeUpdate(sql);
			System.out.println(rno + "-" + sno + " End Repair");
		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}
}
