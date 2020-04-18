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
import javax.servlet.http.HttpSession;

import com.silla.library.dbconn.DBConnectionInfo;
import com.silla.library.member.MemberDTO;


/**
 * Servlet implementation class BookRental
 */
@WebServlet("/BookRental")
public class BookRental extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookRental() {
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
		String mno = request.getParameter("mno");
		String sfunc = request.getParameter("sfunc");
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		String reservation = request.getParameter("reservation");
		ArrayList<BookDTO> book = null;
		ArrayList<RentLogDTO> rentlog = null;
		String viewName = "/rent.jsp";

		try {

			BookDAO bdao = new BookDAO(connInfo);
			RentLogDAO rdao = new RentLogDAO(connInfo);
			
			if (mno != "" && mno != null && mno.length() != 0) {
				if ("0".equals(reservation)) {//대여
					
					rdao.isRentInfo(isbn, mno);//대여 정보 rentdate 테이블에 저장
					bdao.isBookRent(isbn);//count -1
					
				} else if ("1".equals(reservation)) {//반납
					
					rdao.isReturnInfo(isbn, mno);//대여 정보 rentdate 테이블에 삭제
					bdao.isBookReturn(isbn);//count +1
					
				}
				if ("1".equals(sfunc)) {
					book = bdao.isSearchBook(category, search);
				} else {
					book = bdao.isGetBookList();
				}
				rentlog = rdao.isGetRentLogInfo(mno);
			} else {
				viewName = "/login_fail.jsp";
			}
			request.setAttribute("book", book);
			request.setAttribute("rentlog", rentlog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher view = request.getRequestDispatcher("/rent_fail.jsp");
			view.forward(request, response);
		}

		RequestDispatcher view = request.getRequestDispatcher(viewName);
		view.forward(request, response);
	}

}
