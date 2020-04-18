package com.silla.library.member;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class Admin_Member_Update
 */
//@WebServlet("/Admin_Member_Update")
public class Admin_Member_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Member_Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setCharacterEncoding("UTF-8");
	    DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("connection");
	    HttpSession session = request.getSession();

	    AdminDTO adto = (AdminDTO) session.getAttribute("adto");
	    session.setAttribute("adto", adto);
	    
		String id = request.getParameter("id");
		
		System.out.println(id);
		try {
			MemberDAO dao = new MemberDAO(connInfo);
			MemberDTO member = dao.isGetMemberInfo(id);

			request.setAttribute("member", member);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("/member_info.jsp");
	      view.forward(request, response);

	}
}


