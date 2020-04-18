package com.silla.library.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silla.library.dbconn.DBConnectionInfo;
import com.silla.library.member.MemberDAO;
import com.silla.library.member.MemberDTO;

/**
 * Servlet implementation class member_list
 */
//@WebServlet("/member_list")
public class Member_List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member_List() {
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
	    String update = request.getParameter("update");
		String id = request.getParameter("id");
		try {
			
			MemberDAO dao = new MemberDAO(connInfo);
			if("1".equals(update)) {
				MemberDTO member = new MemberDTO();
			    
			      member.setMname(request.getParameter("name"));
			      member.setMbirth(Integer.parseInt(request.getParameter("birth")));
			      member.setMtel(request.getParameter("tel"));
			      member.setMid(request.getParameter("id"));
			      member.setMpw(request.getParameter("pw"));
			      member.setMemail(request.getParameter("mail"));
			      
			      int result = dao.isUpdateMember(member);
			}
			ArrayList<MemberDTO> memberList = dao.isGetMemberList();

			request.setAttribute("memberList", memberList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		RequestDispatcher view = request.getRequestDispatcher("/member_list.jsp");
	      view.forward(request, response);
	}

}
