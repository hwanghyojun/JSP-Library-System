package com.silla.library.book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.silla.library.dbconn.DBConnectionInfo;
import com.silla.library.book.BookDAO;
import com.silla.library.book.BookDTO;

/**
 * Servlet implementation class Book_Update
 */
@WebServlet("/Book_Update")
public class Book_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Book_Update() {
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

		String isbn = request.getParameter("isbn");

		try {
			BookDAO dao = new BookDAO(connInfo);
		
			BookDTO book = dao.isGetBookInfo(isbn);
			
			request.setAttribute("book", book);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("/book_update.jsp");
		view.forward(request, response);
	}

}
