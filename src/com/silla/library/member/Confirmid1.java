package com.silla.library.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.silla.library.dbconn.DBConnectionInfo;
import com.silla.library.member.MemberDAO;

/**
 * Servlet implementation class confirmid
 */
//@WebServlet("/confirmid")
public class Confirmid1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirmid1() {
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
	      String id = request.getParameter("id");
	      
			MemberDAO dao = new MemberDAO(connInfo);
			try {
				boolean result = dao.isConfirmId(id);
				
				request.setAttribute("result", Boolean.valueOf(result));
				
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			RequestDispatcher view = request.getRequestDispatcher("/confirmid1.jsp");
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
