package com.silla.library.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silla.library.dbconn.DBConnectionInfo;

/**
 * Servlet implementation class BookManagement
 */
@WebServlet("/BookManagement")
public class BookManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("connection");
		//추가
		String insert = request.getParameter("insert");
		//수정
		String update = request.getParameter("update");
		//검색
		String sfunc = request.getParameter("sfunc");
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		
		BookDTO book = new BookDTO();
		ArrayList<BookDTO> booklist = null;
		String viewName = "/book_management.jsp";

		try {

			BookDAO bdao = new BookDAO(connInfo);
			if("1".equals(insert)) {
				book.setIsbn(request.getParameter("isbn"));
				book.setBname(request.getParameter("bname"));
				book.setAuthor(request.getParameter("author"));
				book.setPublisher(request.getParameter("publisher"));
				book.setGenre(request.getParameter("genre"));
				book.setCount(Integer.parseInt(request.getParameter("count")));
				bdao.isInsertBook(book);
			}
			
			if("1".equals(update)) {
				book.setIsbn(request.getParameter("isbn"));
				book.setBname(request.getParameter("bname"));
				book.setAuthor(request.getParameter("author"));
				book.setPublisher(request.getParameter("publisher"));
				book.setGenre(request.getParameter("genre"));
				book.setCount(Integer.parseInt(request.getParameter("count")));
				bdao.isUpdateBook(book);
			}

			if ("1".equals(sfunc)) {
				booklist = bdao.isSearchBook(category, search);
			
			} else {
				booklist = bdao.isGetBookList();
				
			}

			request.setAttribute("book", booklist);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher view = request.getRequestDispatcher(viewName);
		view.forward(request, response);
	}
}
