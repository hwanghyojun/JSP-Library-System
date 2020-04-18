package com.silla.library.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silla.library.dbconn.DBConnectionInfo;


/**
 * Servlet implementation class join
 */
public class Join extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
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
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      // TODO Auto-generated method stub
      request.setCharacterEncoding("UTF-8");
      DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("connection");
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
         int result = dao.isInsertUser(member);

         // out results
         request.setAttribute("result", String.valueOf(result));
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      RequestDispatcher view = request.getRequestDispatcher("/join_result.jsp");
      view.forward(request, response);
   }

}