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

/**
 * Servlet implementation class adminlogin
 */
//@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
	      
	      String viewName = null;
	      HttpSession session = request.getSession();

			// 占쏙옙占싱듸옙占� 占쏙옙橘占싫ｏ옙占� 占쏙옙占쏙옙占승댐옙.
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			int admin = 1;
			// DB占쏙옙占쏙옙 占쏙옙占싱듸옙, 占쏙옙橘占싫� 확占쏙옙
			try {
				AdminDAO dao = new AdminDAO(connInfo);
				int check = dao.isAdminLoginCheck(id, pw);

				if (check == 0) // 占쏙옙橘占싫� 틀占쏙옙占쏙옙占� -> 占쌕쏙옙 占싸깍옙占쏙옙 화占쏙옙占쏙옙占쏙옙 占싱듸옙
				{
					// 占싸깍옙占쏙옙 占쏙옙占싻쏙옙 占쌨쏙옙占쏙옙占쏙옙 request占쏙옙 占쏙옙쨈占�.
					System.out.println(check);
					request.setAttribute("fail", "0");
					viewName = "/adminlogin.jsp";
				} else if (check == -1) // 占쏙옙占싱듸옙 占쏙옙占쏙옙 占쏙옙占� -> 占쌕쏙옙 占싸깍옙占쏙옙 화占쏙옙占쏙옙占쏙옙 占싱듸옙
				{
					System.out.println(check);
					request.setAttribute("fail", "-1");
					viewName = "/adminlogin.jsp";
				} else {
					// 占싸깍옙占쏙옙 占쏙옙占쏙옙 -> 占쏙옙占실울옙 占쏙옙占싱듸옙 占쏙옙占쏙옙
					System.out.println(check);
					AdminDTO dto = dao.isGetAdminInfo(id);
					
					session.setAttribute("adto", dto);
				
					if (session.getAttribute("adto") != null) {
						
						viewName = "/main.jsp";
						session.setAttribute("admin", admin);
						
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		      RequestDispatcher view = request.getRequestDispatcher(viewName);
		      view.forward(request, response);
	}

}

