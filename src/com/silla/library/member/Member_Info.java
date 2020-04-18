package com.silla.library.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.silla.library.member.MemberDAO;
import com.silla.library.member.MemberDTO;
import com.silla.library.dbconn.DBConnectionInfo;

/**
 * Servlet implementation class member_info
 */
//@WebServlet("/member_info")
public class Member_Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member_Info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("UTF-8");
	      DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("connection");
	      
	      HttpSession session = request.getSession();

	      MemberDTO mdto = (MemberDTO) session.getAttribute("mdto");

			try {
				MemberDAO dao = new MemberDAO(connInfo);
				MemberDTO member = dao.isGetUserInfo(mdto.getMid());

				request.setAttribute("member", member);


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      RequestDispatcher view = request.getRequestDispatcher("/member_info.jsp");
		      view.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
