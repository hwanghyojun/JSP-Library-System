package com.silla.library.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silla.library.member.MemberDAO;
import com.silla.library.member.MemberDTO;
import com.silla.library.dbconn.DBConnectionInfo;

/**
 * Servlet implementation class member_update
 */
// @WebServlet("/member_update")
public class Member_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Member_Update() {
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
		request.setCharacterEncoding("UTF-8");
		DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("connection");

		String viewName = null;

		MemberDTO member = new MemberDTO();

		member.setMname(request.getParameter("name"));
		member.setMbirth(Integer.parseInt(request.getParameter("birth")));
		member.setMtel(request.getParameter("tel"));
		member.setMid(request.getParameter("id"));
		member.setMpw(request.getParameter("pw"));
		member.setMemail(request.getParameter("mail"));

		try {
			// data processing
			MemberDAO dao = new MemberDAO(connInfo);
			int result = dao.isUpdateMember(member);
			// out results

			request.setAttribute("result", String.valueOf(result));
			viewName = "/member_update_result.jsp";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher(viewName);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
