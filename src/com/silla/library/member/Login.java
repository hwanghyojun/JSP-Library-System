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
 * Servlet implementation class login
 */
//@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	      int admin=0;
	      String viewName = null;
	      HttpSession session = request.getSession();

			// ���̵�� ��й�ȣ�� �����´�.
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			// DB���� ���̵�, ��й�ȣ Ȯ��
			try {
				MemberDAO dao = new MemberDAO(connInfo);
				int check = dao.isLoginCheck(id, pw);

				if (check == 0) // ��й�ȣ Ʋ����� -> �ٽ� �α��� ȭ������ �̵�
				{
					// �α��� ���н� �޽����� request�� ��´�.
					System.out.println(check);
					request.setAttribute("fail", "0");
					viewName = "/login.jsp";
				} else if (check == -1) // ���̵� ���� ��� -> �ٽ� �α��� ȭ������ �̵�
				{
					System.out.println(check);
					request.setAttribute("fail", "-1");
					viewName = "/login.jsp";
				} else {
					// �α��� ���� -> ���ǿ� ���̵� ����
					System.out.println(check);
					MemberDTO dto = dao.isGetUserInfo(id);
					session.setAttribute("mdto", dto);
					
					
					if (session.getAttribute("mdto") != null) {
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
