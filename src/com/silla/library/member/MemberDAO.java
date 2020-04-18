package com.silla.library.member;

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

import com.silla.library.member.MemberDTO;
import com.silla.library.dbconn.DBConnectionInfo;

public class MemberDAO {

	private DBConnectionInfo connInfo = null;
	private Connection dbConn = null;

	public MemberDAO(DBConnectionInfo connInfo) {
		this.connInfo = connInfo;
	}

	public void connect() throws SQLException { // db占쎈염野껓옙.
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

	public int isInsertUser(MemberDTO member) throws SQLException {
		// 占쎄텢占쎌뒠占쎌쁽 占쎌돳占쎌뜚揶쏉옙占쎌뿯.

		int result = 0;
		if (member == null)
			return 0;
		PreparedStatement stmt = null;
		try {

			connect();

			String sql = "INSERT into " + connInfo.getDbmember()
					+ " (mid, mpw, mname, mbirth, memail, mtel) values (?, ?, ?, ?, ?, ?);";
			// DB占쎈퓠 占쎌돳占쎌뜚占쎌젟癰귨옙 占쎄땜占쎌뿯.
			stmt = dbConn.prepareStatement(sql);

			stmt.setString(1, member.getMid());
			stmt.setString(2, member.getMpw());
			stmt.setString(3, member.getMname());
			stmt.setInt(4, member.getMbirth());
			stmt.setString(5, member.getMemail());
			stmt.setString(6, member.getMtel());

			result = stmt.executeUpdate();
		}

		catch (SQLException ex) {
			throw ex;
		} finally {
			if (stmt != null)
				stmt.close();
			disconnect();
		}
		return result;
	}

	public boolean isConfirmId(String id) throws ClassNotFoundException, SQLException {
		// 占쎈툡占쎌뵠占쎈탵 餓λ쵎�궗筌ｋ똾寃�.
		ResultSet rs = null;
		PreparedStatement stmt = null;
		connect();

		boolean result = false;
		String sql = "select mid from member where mid=?";
		// 占쎌뵬燁살꼹釉�占쎈뮉 占쎈툡占쎌뵠占쎈탵 筌≪뼐由�.

		try {
			stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, id);
			System.out.println("1");
			rs = stmt.executeQuery();
			System.out.println("2");

			if (rs.next()) {
				result = true; // 揶쏆늿占� 占쎈툡占쎌뵠占쎈탵揶쏉옙 占쎌뿳占쎈뮉 野껋럩�뒭 true.
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();

		}
		return result;

	}

	public MemberDTO isGetMemberInfo(String id) throws SQLException {// 회원 정보를 모두 들고옴
		MemberDTO member = new MemberDTO();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * fROM " + connInfo.getDbmember() + " WHERE mid='" + id + "';";
		try {
			connect();
			System.out.println(sql);
			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.isBeforeFirst()) {
				rs.next();
				member.setMname(rs.getString("mname"));
				member.setMid(rs.getString("mid"));
				member.setMpw(rs.getString("mpw"));
				member.setMbirth(rs.getInt("mbirth"));
				member.setMemail(rs.getString("memail"));
				member.setMtel(rs.getString("mtel"));
			}
			rs.close();
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (stmt != null)
				stmt.close();
			disconnect();
		}

		return member;
	}

	public ArrayList<MemberDTO> isGetMemberList() throws SQLException {
		// 占쎌돳占쎌뜚 占쎌젟癰귨옙 �뵳�딅뮞占쎈뱜.

		String sql = null;

		sql = String.format("select * from %s", connInfo.getDbmember()); // 占쎈툡占쎌뵠占쎈탵揶쏉옙 占쎈씨占쎈뮉 野껋럩�뒭.

		connect();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		ArrayList<MemberDTO> list = null;
		try {

			stmt = dbConn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.isBeforeFirst()) {
				list = new ArrayList<MemberDTO>();
				while (rs.next()) {
					MemberDTO dto = new MemberDTO();
					dto.setMno(rs.getInt("mno"));
					dto.setMid(rs.getString("mid"));
					dto.setMpw(rs.getString("mpw"));
					dto.setMname(rs.getString("mname"));
					dto.setMbirth(rs.getInt("mbirth"));
					dto.setMemail(rs.getString("memail"));
					dto.setMtel(rs.getString("mtel"));
					list.add(dto);
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
		return list;
	}

	public int isLoginCheck(String id, String pw) throws SQLException, ClassNotFoundException {
		// 嚥≪뮄�젃占쎌뵥占쎈뻻 占쎈툡占쎌뵠占쎈탵 �뜮袁⑨옙甕곕뜇�깈 占쎌넇占쎌뵥.
		ResultSet rs = null;
		PreparedStatement stmt = null;
		connect();

		String dbPW = ""; // 占쎌뿯占쎌젾占쎈립 �뜮袁⑨옙甕곕뜇�깈 占쎄퐫占쎌뱽 癰귨옙占쎈땾.
		int x = -1;

		try {

			String sql = "select mpw from " + connInfo.getDbmember() + " where mid =?";
			// 占쎈툡占쎌뵠占쎈탵 占쎌뿯占쎌젾占쎈뻻 占쎈퉸占쎈뼣占쎈릭占쎈뮉 �뜮袁⑨옙甕곕뜇�깈�몴占� 揶쏉옙占쎌죬占쎌긾.

			stmt = dbConn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) // 占쎈퉸占쎈뼣占쎈릭占쎈뮉 揶쏅�れ뵠 占쎌뿳占쎌뱽野껋럩�뒭.
			{
				dbPW = rs.getString("mpw"); // 占쎌뿯占쎌젾占쎈립 �뜮袁⑨옙甕곕뜇�깈�몴占� 癰귨옙占쎈땾占쎈퓠 占쎄땜占쎌뿯.
				if (dbPW.equals(pw))
					x = 1; // 1占쎌뵠筌롳옙 占쎈툡占쎌뵠占쎈탵 �뜮袁⑨옙甕곕뜇�깈 占쎌뵬燁삼옙.
				else
					x = 0; // 0占쎌뵠筌롳옙 �뜮袁⑨옙甕곕뜇�깈 �겫�뜆�뵬燁삼옙.

			} else {
				x = -1; // -1占쎌뵠筌롳옙 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎈툡占쎌뵠占쎈탵 占쎈씨占쎌벉.
			}

			return x;

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}

	public MemberDTO isGetUserInfo(String id) throws SQLException {
		// 占쎌돳占쎌뜚占쎌젟癰귣떯占쏙옙議뉛옙�궎疫뀐옙.
		ResultSet rs = null;
		PreparedStatement stmt = null;
		MemberDTO dto = new MemberDTO();
		String sql = null;
		sql = String.format("select * from %s where mid='%s'", connInfo.getDbmember(), id); // 占쎈툡占쎌뵠占쎈탵 占쎌뿯占쎌젾占쎈퉸
																							// 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎌젟癰귨옙 筌뤴뫀紐�
																							// 揶쏉옙占쎌죬占쎌궎疫뀐옙.
		connect();
		try {

			stmt = dbConn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {

				while (rs.next()) { // 占쎈퉸占쎈뼣 占쎈릭占쎈뮉 占쎈툡占쎌뵠占쎈탵揶쏉옙 占쎌뿳占쎈뮉 野껋럩�뒭.
					dto.setMno(rs.getInt("mno"));
					dto.setMid(rs.getString("mid"));
					dto.setMpw(rs.getString("mpw"));
					dto.setMname(rs.getString("mname"));
					dto.setMbirth(rs.getInt("mbirth"));
					dto.setMemail(rs.getString("memail"));
					dto.setMtel(rs.getString("mtel"));

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

	public int isUpdateMember(MemberDTO member) throws SQLException {
		// 占쎌돳占쎌뜚 占쎌젟癰귨옙 占쎈땾占쎌젟.
		int result = 0;

		connect();

		Statement stmt = null;

		// execute SQL statement

		String sql = "update " + connInfo.getDbmember() + " set " + "mpw='" + member.getMpw() + "', " + "mname='"
				+ member.getMname() + "', " + "mbirth=" + member.getMbirth() + ", " + "mtel=" + member.getMtel() + ","
				+ "memail='" + member.getMemail() + "' " + "where mid= '" + member.getMid() + "';";
		// 占쎈툡占쎌뵠占쎈탵嚥∽옙 占쎌젟癰귨옙 筌≪뼚釉� 占쎈땾占쎌젟占쎈릭疫뀐옙.

		try {
			stmt = dbConn.createStatement();

			result = stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}

		return result;
	}

	public int isDeleteMember(String id) throws SQLException {
		// 占쎌돳占쎌뜚 占쎄텣占쎌젫 占쎈릭疫뀐옙.
		int result = 0;
		int mno = 0;
		int reservation = 0;
		String rent = null;
		if (id == null || id.length() == 0)
			return result;
		ResultSet rs = null;
		Statement stmt = null;
		connect();
		String sql = null;

		// execute SQL statement

		// 占쎈툡占쎌뵠占쎈탵嚥∽옙 占쎈퉸占쎈뼣 占쎈툡占쎌뵠占쎈탵 占쎌젟癰귨옙 占쎄텣占쎌젫占쎈릭疫뀐옙.

		try {
			stmt = dbConn.createStatement();
			
			sql = "select mno from " + connInfo.getDbmember() + " where mid = '" + id + "';";
			rs = stmt.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				rs.next();
				mno = rs.getInt("mno");
			}//id 값을 이용해 mno를 찾음
			
			sql = "select sno from " + connInfo.getDbseat() + " where mno=" + mno + ";";
			rs = stmt.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				rs.next();
				reservation = rs.getInt("sno");
			}// mno를 이용해 예약된 자리가 있는지 확인함
			
			sql = "select isbn from " + connInfo.getDbrentlog() + " where mno=" + mno + ";";
			rs = stmt.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				rs.next();
				rent = rs.getString("isbn");
			}// mno를 이용해 대여한 도서가 있는지 확인함
			
			if (reservation > 0 || rent != null) {
				result = 0;
				//예약된 자리가 있거나 대여한 도서가 있으면 회원탈퇴 실패
			} else {
				sql = "delete from " + connInfo.getDbmember() + " where mid = '" + id + "';";
				result = stmt.executeUpdate(sql);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}

		return result;
	}

	public int isGetMno(String id) throws ClassNotFoundException, SQLException {
		// 占쎌돳占쎌뜚 占쎄텣占쎌젫 占쎈릭疫뀐옙.
		int result = 0;

		Statement stmt = null;
		connect();

		// execute SQL statement
		String sql = "select mno from " + connInfo.getDbmember() + " where mid = '" + id + "';";
		// 占쎈툡占쎌뵠占쎈탵嚥∽옙 占쎈퉸占쎈뼣 占쎈툡占쎌뵠占쎈탵 占쎌젟癰귨옙 占쎄텣占쎌젫占쎈릭疫뀐옙.

		try {
			stmt = dbConn.createStatement();

			result = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}

		return result;
	}

}