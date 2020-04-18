package com.silla.library.seat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.silla.library.dbconn.DBConnectionInfo;

/**
 * Servlet implementation class gettotal
 */
// @WebServlet("/gettotal")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reservation() {
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
		HttpSession session = request.getSession();
		String rno = request.getParameter("rno");
		String sno = request.getParameter("sno");
		String mno = request.getParameter("mno");
		String ano = request.getParameter("ano");
		
		String reservation = request.getParameter("reservation");
		String viewName = "/reservation.jsp";
		int overlap = 0;
		try {

			// data processing
			RoomDAO rdao = new RoomDAO(connInfo);
			
			String total = rdao.isGetTotal(rno);
			request.setAttribute("total", total);
			
			SeatDAO sdao = new SeatDAO(connInfo);

			if (mno == "" && ano == "") {
				viewName = "/login_fail.jsp";
			} else {
				int admin = (int) session.getAttribute("admin");
				if (admin == 1) {
					mno = null;
				}
				if ("1".equals(reservation)) {
					
					if (admin == 1) {
						sdao.isRepair(rno, sno, ano);
					} else {
						int time = Integer.parseInt(request.getParameter("time"));
						sdao.isReservation(rno, sno, mno, time);
					}
				}
				if ("2".equals(reservation)) {
					if (admin == 1) {
						sdao.isEndRepair(rno, sno, ano);
					} else {
						sdao.isEndReservation(rno, sno, mno);
					}
				}
				if(mno != null) {
					overlap = sdao.isOverlapCheck(mno);
				}
				

				sdao.isTimeOut();
				String fill = sdao.isGetFill(rno);
				ArrayList<SeatDTO> seat = sdao.isGetSno(rno);

				request.setAttribute("seat", seat);
				request.setAttribute("overlap", overlap);
				request.setAttribute("fill", fill);
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher view = request.getRequestDispatcher(viewName);
		view.forward(request, response);

	}

}
