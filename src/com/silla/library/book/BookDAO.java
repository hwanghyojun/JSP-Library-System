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
import com.silla.library.book.BookDTO;

public class BookDAO {
	private DBConnectionInfo connInfo = null;
	private Connection dbConn = null;

	public BookDAO(DBConnectionInfo connInfo) {
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

	public ArrayList<BookDTO> isGetBookList() throws SQLException {// 책 정보를 모두 들고옴
		ArrayList<BookDTO> book = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = String.format("SELECT * fROM %s;", connInfo.getDbbook());
		try {
			connect();

			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {
				if (book == null)
					book = new ArrayList<BookDTO>();

				while (rs.next()) {
					BookDTO blist = new BookDTO();

					blist.setIsbn(rs.getString("isbn"));
					blist.setBname(rs.getString("bname"));
					blist.setAuthor(rs.getString("author"));
					blist.setPublisher(rs.getString("publisher"));
					blist.setGenre(rs.getString("genre"));
					blist.setCount(rs.getInt("count"));
					// BookDTO 객체에 검색 결과 값을 모두넣음
					book.add(blist);// BookDTO list 에 BookDTO 객체를 넣음

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

		return book;
	}

	public BookDTO isGetBookInfo(String isbn) throws SQLException {// 책 정보를 모두 들고옴
		BookDTO book = new BookDTO();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * fROM " + connInfo.getDbbook() + " WHERE isbn=" + isbn + ";";
		try {
			connect();

			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.isBeforeFirst()) {
				rs.next();
				book.setIsbn(rs.getString("isbn"));
				book.setBname(rs.getString("bname"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setGenre(rs.getString("genre"));
				book.setCount(rs.getInt("count"));
			}
			rs.close();
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (stmt != null)
				stmt.close();
			disconnect();
		}

		return book;
	}

	public ArrayList<BookDTO> isSearchBook(String category, String search) throws SQLException {// 책 검색 결과를 가져오는 메소드
		ArrayList<BookDTO> book = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		switch (category) {
		case "0":
			sql = "SELECT * FROM book WHERE bname like '%" + search + "%';";// 이름으로 검색시 관련 책을 모두 출력
			break;
		case "1":
			sql = "SELECT * FROM book WHERE author like '%" + search + "%';";// 저자으로 검색시 관련 책을 모두 출력
			break;
		case "2":
			sql = "SELECT * FROM book WHERE publisher like '%" + search + "%';";// 출판사으로 검색시 관련 책을 모두 출력
			break;
		case "3":
			sql = "SELECT * FROM book WHERE genre like '%" + search + "%';";// 장르으로 검색시 관련 책을 모두 출력
			break;
		default:
			break;
		}
		try {
			connect();

			stmt = dbConn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {
				if (book == null)
					book = new ArrayList<BookDTO>();

				while (rs.next()) {
					BookDTO blist = new BookDTO();

					blist.setIsbn(rs.getString("isbn"));
					blist.setBname(rs.getString("bname"));
					blist.setAuthor(rs.getString("author"));
					blist.setPublisher(rs.getString("publisher"));
					blist.setGenre(rs.getString("genre"));
					blist.setCount(rs.getInt("count"));
					// BookDTO 객체에 검색 결과 값을 모두넣음
					book.add(blist);// BookDTO list 에 BookDTO 객체를 넣음

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

		return book;
	}

	public void isBookRent(String isbn) throws SQLException {
		// 카운트 -1
		Statement stmt = null;
		// execute SQL statement
		String sql = "update " + connInfo.getDbbook() + " set count = count-1 where isbn='" + isbn + "';";
		
		try {
			connect();
			stmt = dbConn.createStatement();
			
			stmt.executeUpdate(sql);
			System.out.println("isbn: " + isbn + " count -1");
		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}

	public void isBookReturn(String isbn) throws SQLException {
		// 카운트 -1
		Statement stmt = null;
		// execute SQL statement
		String sql = "update " + connInfo.getDbbook() + " set count = count+1 where isbn='" + isbn + "';";
	
		try {
			connect();
			stmt = dbConn.createStatement();
			
			stmt.executeUpdate(sql);
			System.out.println("isbn: " + isbn + " count +1");
		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
	}

	public void isInsertBook(BookDTO book) throws SQLException {
		// �궗�슜�옄 �쉶�썝媛��엯.

		PreparedStatement stmt = null;
		try {

			connect();

			String sql = "INSERT into " + connInfo.getDbbook()
					+ " (isbn, bname, author, publisher, genre, count) values (?, ?, ?, ?, ?, ?);";
			// DB�뿉 �쉶�썝�젙蹂� �궫�엯.
			stmt = dbConn.prepareStatement(sql);

			stmt.setString(1, book.getIsbn());
			stmt.setString(2, book.getBname());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getPublisher());
			stmt.setString(5, book.getGenre());
			stmt.setInt(6, book.getCount());

			stmt.executeUpdate();
		}

		catch (SQLException ex) {
			throw ex;
		} finally {
			if (stmt != null)
				stmt.close();
			disconnect();
		}

	}

	public int isDeleteBook(String isbn) throws SQLException {
		// �쉶�썝 �궘�젣 �븯湲�.
		int result = 0;
		int mno = 0;
		Statement stmt = null;
		ResultSet rs = null;
		connect();
		String sql = null;
		// execute SQL statement

		// �븘�씠�뵒濡� �빐�떦 �븘�씠�뵒 �젙蹂� �궘�젣�븯湲�.

		try {
			stmt = dbConn.createStatement();
			sql = "select mno from " + connInfo.getDbrentlog() + " where isbn='" + isbn + "';";
			rs = stmt.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				rs.next();
				mno = rs.getInt("mno");
			}
			if (mno > 0) {
				result = 0;
			} else {
				sql = "delete from " + connInfo.getDbbook() + " where isbn = '" + isbn + "';";
				result = stmt.executeUpdate(sql);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}
		return result;
	}

	public void isUpdateBook(BookDTO book) throws SQLException {
		// �쉶�썝 �젙蹂� �닔�젙.

		connect();

		Statement stmt = null;

		// execute SQL statement

		String sql = "update " + connInfo.getDbbook() + " set " + "bname='" + book.getBname() + "', " + "author='"
				+ book.getAuthor() + "', " + "publisher='" + book.getPublisher() + "', " + "genre='" + book.getGenre()
				+ "'," + "count=" + book.getCount() + " " + "where isbn= " + book.getIsbn() + ";";
		// �븘�씠�뵒濡� �젙蹂� 李얠븘 �닔�젙�븯湲�.
		System.out.println(sql);
		try {
			stmt = dbConn.createStatement();

			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw e;
		} finally {
			disconnect();
		}

	}
}
